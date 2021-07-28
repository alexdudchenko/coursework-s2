package com.example.Coursework.services;

import com.example.Coursework.dao.DaoFactory;
import com.example.Coursework.exception.AdminFormException;
import com.example.Coursework.exception.CreateSessionException;
import com.example.Coursework.exception.UserSessionPageException;
import com.example.Coursework.model.*;
import com.example.Coursework.sorter.SessionSortCriteria;
import com.example.Coursework.sorter.SessionSorters;

import java.util.Collection;
import java.util.stream.Collectors;


public class CinemaSessionService implements ICinemaSessionService {

    DaoFactory daoFactory;

    public CinemaSessionService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    @Override
    public Collection<CinemaSession> getAllCinemaSessions() {
        return daoFactory.getCinemaSessionDao().findAll();
    }

    public Collection<CinemaSession> getAllCinemaSessions(SessionSortCriteria sessionSortCriteria) {
        Collection<CinemaSession> cinemaSessions = getAllCinemaSessions();
        return sort(cinemaSessions, sessionSortCriteria);
    }

    @Override
    public CinemaSession getCinemaSessionById(int id) {
        return daoFactory.getCinemaSessionDao().get(id);
    }

    @Override
    public void book(CinemaSession cinemaSession, int place) {
        daoFactory.getCinemaSessionDao().bookPlace(cinemaSession, place);
    }

    @Override
    public Collection<CinemaSession.Place> getPlaces(CinemaSession session) {
        return daoFactory.getCinemaSessionDao().getPlaces(session);
    }

    @Override
    public void removeSession(int id) {
        daoFactory.getCinemaSessionDao().removeSession(id);
    }

    @Override
    public void createSession(CinemaSession cinemaSession) {
        if (daoFactory.getCinemaSessionDao().checkTimeCollision(cinemaSession)){
            throw new CreateSessionException("You have time collision, please, pick up another time for this session");
        }
        daoFactory.getCinemaSessionDao().insert(cinemaSession);
    }

    @Override
    public void cancelPlace(CinemaSession cinemaSession, int place) {
        daoFactory.getCinemaSessionDao().cancelPlace(cinemaSession, place);
    }

    @Override
    public void update(CinemaSession cinemaSession) {
        daoFactory.getCinemaSessionDao().removeSession(cinemaSession.getSessionId());
        if (daoFactory.getCinemaSessionDao().checkTimeCollision(cinemaSession)){
            daoFactory.getCinemaSessionDao().insert(cinemaSession);
            throw new AdminFormException("You have time collision, please, pick up another time for this session");
        }
        daoFactory.getCinemaSessionDao().update(cinemaSession);
    }

    @Override
    public void checkBooking(String[] places, CinemaSession cinemaSession) {
        for (String str : places) {
            for (CinemaSession.Place place : getPlaces(cinemaSession)) {
                if (place.getNumber() == Integer.parseInt(str) && !place.isFree()) {
                    throw new UserSessionPageException("You have chosen booked place");
                }
            }
        }
    }

    @Override
    public void bookAll(String[] places, CinemaSession cinemaSession) {
        for (String str : places) {
            book(cinemaSession, Integer.parseInt(str));
        }
    }

    @Override
    public void cancelAll(String[] places, CinemaSession cinemaSession) {
        for (String str : places) {
            if (!"".equals(str)) {
                cancelPlace(cinemaSession, Integer.parseInt(str));
            }
        }
    }

    private Collection<CinemaSession> sort(Collection<CinemaSession> cinemaSessions,
                                           SessionSortCriteria sessionSortCriteria) {
        return cinemaSessions.stream()
                .sorted(SessionSorters.sorters.get(sessionSortCriteria))
                .collect(Collectors.toList());
    }
}
