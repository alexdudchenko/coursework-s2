package com.example.Coursework.validator;

import com.example.Coursework.exception.AdminFormException;
import com.example.Coursework.exception.BlankException;
import com.example.Coursework.exception.CreateSessionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void validateAdminForm1() {
        String[] places = {"1", "2"};
        Validator validator = new Validator();

        Exception exception1 = assertThrows(AdminFormException.class,
                () -> validator.validateAdminForm("1f", "f", "f", places));

        String expectedMessage1 = "You have entered not a number!";

        String actualMessage1 = exception1.getMessage();

        assertTrue(actualMessage1.contains(expectedMessage1));

    }

    @Test
    void validateAdminForm2() {
        String[] places = {"1", "2"};
        Validator validator = new Validator();

        assertDoesNotThrow(() -> validator.validateAdminForm("1", "2", "3", places));

    }

    @Test
    void validateAdminForm3() {
        String[] places = {"1", "21"};
        Validator validator = new Validator();

        Exception exception2 = assertThrows(AdminFormException.class,
                () -> validator.validateAdminForm("1", "2", "3", places));

        String expectedMessage2 = "There's no place with this number";

        String actualMessage2 = exception2.getMessage();

        assertTrue(actualMessage2.contains(expectedMessage2));

    }

    @Test
    void validateLogin() {
        Validator validator = new Validator();
        Exception exception = assertThrows(BlankException.class,
                () -> validator.validateLogin(""));

        assertTrue(exception.getMessage().contains("You have leaven blank entry/entries"));
    }

    @Test
    void validateAdminFormCreate() {
        Validator validator = new Validator();

        Exception exception = assertThrows(CreateSessionException.class,
                () -> validator.validateAdminFormCreate("1", "2", "aa"));

        assertTrue(exception.getMessage().contains("You have entered not a number!"));
    }
}