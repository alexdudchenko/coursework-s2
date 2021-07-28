package com.example.Coursework.inmemory;

import com.example.Coursework.dao.DaoFactory;
import com.example.Coursework.model.Admin;
import com.example.Coursework.model.CinemaSession;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public class InMemoryDatabase {
    Map<Integer, Admin> admins;
    Map<Integer, CinemaSession> cinemaSessions;
    Map<LocalDateTime, CinemaSession> cinemaSessionTimeMap;


    public InMemoryDatabase() {
        admins = new TreeMap<>();
        cinemaSessions = new TreeMap<>();
        cinemaSessionTimeMap = new TreeMap<>();
    }

    public DaoFactory getDaoFactory() {
        return new InMemoryDaoFactory(this);
    }


    public Map<Integer, CinemaSession> getCinemaSessions() {
        return cinemaSessions;
    }
}
