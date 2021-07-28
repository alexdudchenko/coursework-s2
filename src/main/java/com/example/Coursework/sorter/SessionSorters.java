package com.example.Coursework.sorter;

import com.example.Coursework.model.CinemaSession;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class SessionSorters {
    public static final Map<SessionSortCriteria, Comparator<CinemaSession>> sorters;

    static {
        sorters = new HashMap<>();
        sorters.put(SessionSortCriteria.BY_TITLE, Comparator.comparing(CinemaSession::getFilmTitle));
        sorters.put(SessionSortCriteria.NEW_FIRST, Comparator.comparing(CinemaSession::getStartTime,
                Comparator.reverseOrder()));
        sorters.put(SessionSortCriteria.BY_ID, Comparator.comparing(CinemaSession::getSessionId));
    }
}
