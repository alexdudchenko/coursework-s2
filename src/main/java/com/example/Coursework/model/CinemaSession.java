package com.example.Coursework.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class CinemaSession {
    private int sessionId;
    private String filmTitle;
    private int durationInMinutes;
    private ArrayList<Place> placeArrayList;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private int price;

    public CinemaSession(int sessionId, String filmTitle, int durationInMinutes, LocalDateTime startTime,  int price) {
        this.sessionId = sessionId;
        this.filmTitle = filmTitle;
        this.durationInMinutes = durationInMinutes;
        this.startTime = startTime;
        this.finishTime = startTime.plusMinutes(durationInMinutes);
        this.placeArrayList = new ArrayList<>();
        this.addPlaces();
        this.price = price;

    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public ArrayList<Place> getPlaceArrayList() {
        return placeArrayList;
    }

    public void setPlaceArrayList(ArrayList<Place> placeArrayList) {
        this.placeArrayList = placeArrayList;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaSession that = (CinemaSession) o;
        return durationInMinutes == that.durationInMinutes && filmTitle.equals(that.filmTitle)
                && startTime.equals(that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmTitle, startTime, durationInMinutes);
    }

    private void addPlaces() {
        for (int i = 1; i <= 20; i++) {
            this.placeArrayList.add(new Place(i));
        }

    }

    @Override
    public String toString() {
        return "CinemaSession{" +
                "sessionID=" + sessionId +
                ", filmTitle='" + filmTitle + '\'' +
                ", durationInMinutes=" + durationInMinutes +
                ", placeArrayList=" + placeArrayList +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                '}';
    }

    public static class Place {
        private int number;
        private boolean isFree;


        public Place(int number, boolean isFree) {
            this.number = number;
            this.isFree = isFree;
        }

        public Place(int number) {
            this.number = number;
            this.isFree = true;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public boolean isFree() {
            return isFree;
        }

        public void setFree(boolean free) {
            isFree = free;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Place place = (Place) o;
            return number == place.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }

        @Override
        public String toString() {
            return "Place{" +
                    "number=" + number +
                    ", isFree=" + isFree +
                    '}';
        }
    }
}
