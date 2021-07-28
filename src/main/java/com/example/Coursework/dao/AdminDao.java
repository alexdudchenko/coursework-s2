package com.example.Coursework.dao;

import com.example.Coursework.model.Admin;

public interface AdminDao extends AbstractDao<Admin> {

    Admin getByLogin(String login);

}
