package com.example.Coursework.validator;

import com.example.Coursework.exception.AdminFormException;
import com.example.Coursework.exception.BlankException;
import com.example.Coursework.exception.CreateSessionException;
import com.example.Coursework.exception.UserSessionPageException;
import com.example.Coursework.model.*;
import com.example.Coursework.services.ICinemaSessionService;
import com.example.Coursework.validator.IValidator;

public class Validator implements IValidator {
    @Override
    public void validatePlaces(String[] places, ICinemaSessionService cinemaSessionService, CinemaSession cinemaSession) {

        for (String str : places) {
            if (!str.matches("[0-9]+")) {
                throw new UserSessionPageException("You have entered not a number!");
            }
        }

        for (String str : places) {
            if (Integer.parseInt(str) > 20 || Integer.parseInt(str) < 1) {
                throw new UserSessionPageException("There's no place with this number");
            }
        }
    }

    @Override
    public void validateAdminForm(String idString, String durationString, String priceString, String[] places) {
        if (!idString.matches("[0-9]+") || !durationString.matches("[0-9]+") || !priceString.matches("[0-9]+")) {
            throw new AdminFormException("You have entered not a number!");
        }
        if (!(places.length == 1 && places[0].equals(""))) {
            for (String str : places) {
                if (!str.matches("[0-9]+")) {
                    throw new AdminFormException("You have entered not a number!");
                }
            }

            for (String str : places) {
                if (Integer.parseInt(str) > 20 || Integer.parseInt(str) < 1) {
                    throw new AdminFormException("There's no place with this number");
                }
            }
        }
    }

    @Override
    public void validateLogin(String login) {
        if (login == null || "".equals(login)) {
            throw new BlankException("You have leaven blank entry/entries");
        }
    }

    @Override
    public void validateAdminFormCreate(String idString, String durationString, String priceString) {
        if (!idString.matches("[0-9]+") || !durationString.matches("[0-9]+") || !priceString.matches("[0-9]+")) {
            throw new CreateSessionException("You have entered not a number!");
        }
    }
}
