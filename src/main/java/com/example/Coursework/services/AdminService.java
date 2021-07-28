package com.example.Coursework.services;

import com.example.Coursework.dao.DaoFactory;
import com.example.Coursework.model.Admin;

import java.util.function.UnaryOperator;

public class AdminService implements IAdminService{
    DaoFactory daoFactory;
    UnaryOperator<String> passwordHasher;

    public AdminService(DaoFactory daoFactory, UnaryOperator<String> passwordHasher) {
        this.daoFactory = daoFactory;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public Admin getByLogin(String login) {
        return daoFactory.getAdminDao().getByLogin(login);
    }

    @Override
    public boolean checkPassword(Admin admin, String password) {
        return admin.getPasswordHash().equals(passwordHasher.apply(password));
    }
}
