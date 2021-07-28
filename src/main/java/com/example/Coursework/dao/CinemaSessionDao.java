package com.example.Coursework.dao;

import com.example.Coursework.model.*;

import java.util.Collection;

public interface CinemaSessionDao extends AbstractDao<CinemaSession> {
    Collection<CinemaSession.Place> getPlaces(CinemaSession session);

    void bookPlace(CinemaSession cinemaSession, int number);

    void cancelPlace(CinemaSession cinemaSession, int number);

    void removeSession(int id);

    boolean checkTimeCollision(CinemaSession cinemaSession);
}
