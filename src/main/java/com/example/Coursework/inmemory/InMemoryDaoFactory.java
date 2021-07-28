package com.example.Coursework.inmemory;

import com.example.Coursework.dao.*;

public class InMemoryDaoFactory implements DaoFactory {
    InMemoryDatabase database;

    AdminDao adminDao;
    CinemaSessionDao cinemaSessionDao;

    public InMemoryDaoFactory(InMemoryDatabase database) {
        this.database = database;
        this.adminDao = new InMemoryAdminDao(database);
        this.cinemaSessionDao = new InMemoryCinemaSessionDao(database);
    }

    @Override
    public AdminDao getAdminDao() {
        return adminDao;
    }

    @Override
    public CinemaSessionDao getCinemaSessionDao() {
        return cinemaSessionDao;
    }
}
