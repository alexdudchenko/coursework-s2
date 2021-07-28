package com.example.Coursework.controller;


import com.example.Coursework.exception.AdminFormException;
import com.example.Coursework.exception.BlankException;
import com.example.Coursework.exception.CreateSessionException;
import com.example.Coursework.exception.UserSessionPageException;
import com.example.Coursework.model.*;
import com.example.Coursework.services.*;
import com.example.Coursework.sorter.SessionSortCriteria;
import com.example.Coursework.validator.Validator;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "frontController", urlPatterns = "/front-controller/*") //value = "/front-controller"
public class FrontController extends HttpServlet {
    private IAdminService adminService;
    private ICinemaSessionService cinemaSessionService;
    private Validator validator;

    public void init(ServletConfig config) {
        this.validator = (Validator) config.getServletContext().getAttribute("validator");

        this.adminService = (AdminService) config.getServletContext().getAttribute("adminService");

        this.cinemaSessionService = (CinemaSessionService) config.getServletContext()
                .getAttribute("cinemaSessionService");
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            pathInfo = "/";
        }

        try {
            switch (pathInfo) {
                case "/sessionPage":
                    session(request, response);
                    break;
                case "/book":
                    book(request, response);
                    break;
                case "/loginPage":
                    loginPage(request, response);
                    break;
                case "/login":
                    login(request, response);
                    break;
                case "/editPage":
                    checkAdmin(request, response);
                    editPage(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                case "/remove":
                    checkAdmin(request, response);
                    remove(request, response);
                    break;
                case "/createPage":
                    checkAdmin(request, response);
                    request.getRequestDispatcher("/WEB-INF/jsp/createSessionPage.jsp").forward(request, response);
                    break;
                case "/create":
                    checkAdmin(request, response);
                    createSession(request, response);
                    break;
                case "/edit":
                    checkAdmin(request, response);
                    editSession(request, response);
                    break;
                case "/adminPage":
                    checkAdmin(request, response);
                    toAdminPage(request, response);
                    break;
                case "/":
                default:
                    welcome(request, response);
                    break;
            }
        } catch (BlankException blankException) {
            request.setAttribute("blankException", true);
            request.setAttribute("message", blankException.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(request, response);
        } catch (AdminFormException adminFormException) {
            request.setAttribute("message", adminFormException.getMessage());
            request.setAttribute("adminFormException", true);
            request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(request, response);
        } catch (CreateSessionException createSessionException) {
            request.setAttribute("createSessionException", true);
            request.setAttribute("message", createSessionException.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(request, response);
        } catch (UserSessionPageException userSessionPageException) {
            request.setAttribute("userSessionPageException", true);
            request.setAttribute("message", userSessionPageException.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(request, response);
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            request.setAttribute("message", exception.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(request, response);
        }

    }

    protected void toAdminPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sorting = request.getParameter("sorting");
        SessionSortCriteria sessionSortCriteria;

        if (sorting == null || sorting.equals("")) {
            sessionSortCriteria = SessionSortCriteria.BY_TITLE;
        } else {
            sessionSortCriteria = SessionSortCriteria.valueOf(sorting);
        }

        request.setAttribute("cinemaSessions", cinemaSessionService.getAllCinemaSessions(sessionSortCriteria));
        request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp").forward(request, response);
    }

    protected void welcome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sorting = request.getParameter("sorting");
        SessionSortCriteria sessionSortCriteria;

        if (sorting == null || sorting.equals("")) {
            sessionSortCriteria = SessionSortCriteria.BY_TITLE;
        } else {
            sessionSortCriteria = SessionSortCriteria.valueOf(sorting);
        }

        Collection<CinemaSession> cinemaSessions = cinemaSessionService.getAllCinemaSessions(sessionSortCriteria);

        request.setAttribute("cinemaSessions", cinemaSessions);
        request.getRequestDispatcher("/WEB-INF/jsp/welcomePage.jsp").forward(request, response);
    }

    protected void session(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CinemaSession cinemaSession = cinemaSessionService.getCinemaSessionById(id);
        request.setAttribute("cinemaSession", cinemaSession);
        request.getRequestDispatcher("/WEB-INF/jsp/sessionPage.jsp").forward(request, response);
    }

    protected void book(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] places = request.getParameter("places").split("\\s+");
        int sessionId = Integer.parseInt(request.getParameter("sessionId"));

        CinemaSession cinemaSession = cinemaSessionService.getCinemaSessionById(sessionId);
        request.getSession().setAttribute("errorSessionActionId", sessionId);

        validator.validatePlaces(places, cinemaSessionService, cinemaSession);

        cinemaSessionService.checkBooking(places, cinemaSession);
        cinemaSessionService.bookAll(places, cinemaSession);

        request.setAttribute("cinemaSession", cinemaSession);
        request.getRequestDispatcher("/WEB-INF/jsp/sessionPage.jsp").forward(request, response);
    }

    protected void loginPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        validator.validateLogin(login);

        if (adminService.checkPassword(adminService.getByLogin(login), password)) {
            request.setAttribute("cinemaSessions", cinemaSessionService.getAllCinemaSessions());
            request.getSession().setAttribute("admin", adminService.getByLogin(login));
            request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp").forward(request, response);
        } else {
            request.setAttribute("loginMessage", "Try again. Wrong password");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }

    protected void editPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CinemaSession cinemaSession = cinemaSessionService.getCinemaSessionById(id);
        request.setAttribute("cinemaSession", cinemaSession);
        request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        Collection<CinemaSession> cinemaSessions = cinemaSessionService.getAllCinemaSessions();

        request.setAttribute("cinemaSessions", cinemaSessions);
        request.getRequestDispatcher("/WEB-INF/jsp/welcomePage.jsp").forward(request, response);
    }

    protected void remove(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        cinemaSessionService.removeSession(id);
        request.setAttribute("cinemaSessions", cinemaSessionService.getAllCinemaSessions());
        request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp").forward(request, response);
    }



    protected void createSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idString = request.getParameter("id");
        String durationString = request.getParameter("duration");
        String priceString = request.getParameter("price");
        String filmTitle = request.getParameter("film");

        validator.validateAdminFormCreate(idString, durationString, priceString);
        int id = Integer.parseInt(idString);

        if (cinemaSessionService.getCinemaSessionById(id) != null) {
            throw new CreateSessionException("Different sessions can't have the same ID, try another");
        }

        int duration = Integer.parseInt(durationString);
        int price = Integer.parseInt(priceString);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startTime;

        try {
            startTime = LocalDateTime.parse(request.getParameter("startTime"), formatter);
        } catch (DateTimeParseException exception) {
            throw new CreateSessionException("Wrong date-time format, try again");
        }

        CinemaSession cinemaSession = new CinemaSession(id, filmTitle, duration, startTime, price);
        cinemaSessionService.createSession(cinemaSession);

        request.setAttribute("cinemaSessions", cinemaSessionService.getAllCinemaSessions());
        request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp").forward(request, response);
    }


    protected void editSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idString = request.getParameter("id");

        String durationString = request.getParameter("duration");
        String priceString = request.getParameter("price");

        String[] places = request.getParameter("cancelBook").split("\\s+");

        int id = Integer.parseInt(idString);

        request.getSession().setAttribute("errorSessionActionId", id);

        validator.validateAdminForm(idString, durationString, priceString, places);

        CinemaSession cinemaSession_ = cinemaSessionService.getCinemaSessionById(id);

        int duration = Integer.parseInt(durationString);
        int price = Integer.parseInt(priceString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime startTime;

        try {
               startTime = LocalDateTime.parse(request.getParameter("startTime"), formatter);
        } catch (DateTimeParseException exception) {
            throw new AdminFormException("Wrong date-time format, try again");
        }

        String filmTitle = request.getParameter("film");

        CinemaSession cinemaSession = new CinemaSession(id, filmTitle, duration, startTime, price);
        cinemaSession.setPlaceArrayList(cinemaSession_.getPlaceArrayList());

        cinemaSessionService.cancelAll(places, cinemaSession);

        cinemaSessionService.update(cinemaSession);
        request.setAttribute("cinemaSessions", cinemaSessionService.getAllCinemaSessions());
        request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp").forward(request, response);
    }

    protected void checkAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("admin") == null) {
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}