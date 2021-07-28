package com.example.Coursework.services;

import com.example.Coursework.model.Admin;

public interface IAdminService {

    Admin getByLogin(String login);

    boolean checkPassword(Admin admin, String password);
}
