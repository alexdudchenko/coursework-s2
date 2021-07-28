package com.example.Coursework.controller;

import com.example.Coursework.dao.DaoFactory;
import com.example.Coursework.inmemory.InMemoryDatabase;
import com.example.Coursework.inmemory.InMemoryTestData;
import com.example.Coursework.services.*;
import com.example.Coursework.validator.IValidator;
import com.example.Coursework.validator.Validator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.function.UnaryOperator;

public class ApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        InMemoryDatabase database = new InMemoryDatabase();

        InMemoryTestData.generateTo(database);

        DaoFactory daoFactory = database.getDaoFactory();

        IAdminService adminService = new AdminService(daoFactory, UnaryOperator.identity());
        sce.getServletContext().setAttribute("adminService", adminService);

        ICinemaSessionService cinemaSessionService = new CinemaSessionService(daoFactory);
        sce.getServletContext().setAttribute("cinemaSessionService", cinemaSessionService);

        IValidator validator = new Validator();
        sce.getServletContext().setAttribute("validator", validator);
    }
}
