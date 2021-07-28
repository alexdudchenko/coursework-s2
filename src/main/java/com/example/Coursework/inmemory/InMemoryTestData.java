package com.example.Coursework.inmemory;

import com.example.Coursework.model.Admin;
import com.example.Coursework.model.CinemaSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InMemoryTestData {

    public static void generateTo(InMemoryDatabase database) {
        database.admins.clear();
        database.cinemaSessions.clear();

        Admin alex = new Admin(1, "Alex", "Alex");
        Admin alexander = new Admin(2, "Alexander", "alexander");

        List<Admin> admins = Arrays.asList(alex, alexander);
        admins.forEach(admin -> database.admins.put(admin.getAdminId(), admin));


        CinemaSession cinemaSession1 = new CinemaSession(1, "Movie1", 80,
                LocalDateTime.parse("2021-04-10 14:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 150);
        CinemaSession cinemaSession2 = new CinemaSession(2, "Movie2", 90,
                LocalDateTime.parse("2021-04-10 16:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 150);
        CinemaSession cinemaSession3 = new CinemaSession(3, "Movie3", 80,
                LocalDateTime.parse("2021-04-10 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 150);
        CinemaSession cinemaSession4 = new CinemaSession(8, "Movie4", 60,
                LocalDateTime.parse("2021-04-10 19:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 150);
        CinemaSession cinemaSession5 = new CinemaSession(5, "Movie5", 120,
                LocalDateTime.parse("2021-04-10 21:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 150);
        CinemaSession cinemaSession6 = new CinemaSession(6, "Movie6", 150,
                LocalDateTime.parse("2021-04-10 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 150);


        List<CinemaSession> cinemaSessions = Arrays.asList(cinemaSession1, cinemaSession2,
                cinemaSession3, cinemaSession4, cinemaSession5, cinemaSession6);

        cinemaSessions.forEach(session -> database.cinemaSessions.put(session.getSessionId(), session));
    }
}

