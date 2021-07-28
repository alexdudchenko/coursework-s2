package com.example.Coursework.validator;

import com.example.Coursework.model.CinemaSession;
import com.example.Coursework.services.ICinemaSessionService;

public interface IValidator {
    void validatePlaces(String[] places, ICinemaSessionService cinemaSessionService, CinemaSession cinemaSession);

    void validateAdminForm(String idString, String durationString, String priceString, String[] places);

    void validateAdminFormCreate(String idString, String durationString, String priceString);

    void validateLogin(String login);

}
