package com.example.Coursework.services;

import com.example.Coursework.model.CinemaSession;
import com.example.Coursework.sorter.SessionSortCriteria;

import java.util.Collection;

public interface ICinemaSessionService {

    Collection<CinemaSession> getAllCinemaSessions();

    Collection<CinemaSession> getAllCinemaSessions(SessionSortCriteria sessionSortCriteria);

    CinemaSession getCinemaSessionById(int id);

    void book(CinemaSession cinemaSession, int place);

    void cancelPlace(CinemaSession cinemaSession, int place);

    Collection<CinemaSession.Place> getPlaces(CinemaSession session);

    void removeSession(int id);

    void createSession(CinemaSession cinemaSession);

    void update(CinemaSession cinemaSession);

    void checkBooking(String[] places, CinemaSession cinemaSession);

    void bookAll(String[] places, CinemaSession cinemaSession);

    void cancelAll(String[] places, CinemaSession cinemaSession);
}
