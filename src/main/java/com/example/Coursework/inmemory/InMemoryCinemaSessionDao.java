package com.example.Coursework.inmemory;

import com.example.Coursework.dao.CinemaSessionDao;
import com.example.Coursework.model.CinemaSession;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;

public class InMemoryCinemaSessionDao extends InMemoryAbstractDao<CinemaSession> implements CinemaSessionDao {

    public InMemoryCinemaSessionDao(InMemoryDatabase database) {
        super(database.cinemaSessions, CinemaSession::getSessionId, CinemaSession::setSessionId, database);
    }

    @Override
    public Collection<CinemaSession.Place> getPlaces(CinemaSession session) {
        return session.getPlaceArrayList();
    }

    @Override
    public void bookPlace(CinemaSession cinemaSession, int number) {
        CinemaSession.Place place = cinemaSession.getPlaceArrayList().stream()
                .filter(place1 -> place1.getNumber() == number).findFirst().orElse(null);

        if (place != null && place.isFree()) {
            place.setFree(false);
        }
    }

    @Override
    public void cancelPlace(CinemaSession cinemaSession, int number) {
        CinemaSession.Place place = cinemaSession.getPlaceArrayList().stream()
                .filter(place1 -> place1.getNumber() == number).findFirst().orElse(null);

        if (place != null && !place.isFree()) {
            place.setFree(true);
        }
    }

    @Override
    public void removeSession(int id) {
        database.cinemaSessions.remove(id);
    }

    @Override
    public boolean checkTimeCollision(CinemaSession cinemaSession) {

        // creating special treeMap for checking collisions

        TreeMap<LocalDateTime, CinemaSession> treeMap= new TreeMap<>();

        for (CinemaSession cinemaSession_ : database.cinemaSessions.values()) {
            treeMap.put(cinemaSession_.getStartTime(), cinemaSession_);
        }

        // starting method

        LocalDateTime start = cinemaSession.getStartTime();
        LocalDateTime finish = cinemaSession.getFinishTime();

        TreeSet<LocalDateTime> set = new TreeSet<>(treeMap.keySet());
        TreeSet<LocalDateTime> mainSet = (TreeSet<LocalDateTime>) set.subSet(start, finish);

        for (LocalDateTime localDateTime : mainSet) {
            if (finish.compareTo(localDateTime) > 0) {
                System.out.println("Ситуация 1");
                return true;
            }
        }

        //с начала
        if (start.compareTo(set.first()) > 0) {
            TreeSet<LocalDateTime> anotherSet1 = (TreeSet<LocalDateTime>) set.subSet(set.first(), start);
            if (treeMap.get(anotherSet1.last()).getFinishTime().compareTo(start) > 0) {
                System.out.println("Ситуация 2");
                return true;
            }
        }

        return false;
    }
}
