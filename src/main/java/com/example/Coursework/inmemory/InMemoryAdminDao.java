package com.example.Coursework.inmemory;

import com.example.Coursework.dao.AdminDao;
import com.example.Coursework.model.Admin;

public class InMemoryAdminDao extends InMemoryAbstractDao<Admin> implements AdminDao {

    public InMemoryAdminDao(InMemoryDatabase database) {
        super(database.admins, Admin::getAdminId, Admin::setAdminId , database);
    }

    @Override
    public Admin getByLogin(String login) {
        return database.admins.values().stream()
                .filter(admin -> admin.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }
}
