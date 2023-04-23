package ru.job4j.cinema.dto;

import java.util.Objects;

public class FilmSessionDto {
    private int sessionId;
    private String nameFilm;
    private String nameHall;
    private String data;
    private String time;
    private int price;

    public FilmSessionDto() {
    }

    public FilmSessionDto(int sessionId, String nameFilm, String nameHall,
                          String data, String time, int price) {
        this.sessionId = sessionId;
        this.nameFilm = nameFilm;
        this.nameHall = nameHall;
        this.data = data;
        this.time = time;
        this.price = price;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getNameFilm() {
        return nameFilm;
    }

    public void setNameFilm(String nameFilm) {
        this.nameFilm = nameFilm;
    }

    public String getNameHall() {
        return nameHall;
    }

    public void setNameHall(String nameHall) {
        this.nameHall = nameHall;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilmSessionDto that = (FilmSessionDto) o;
        if (price != that.price) {
            return false;
        }
        if (!Objects.equals(nameFilm, that.nameFilm)) {
            return false;
        }
        return Objects.equals(nameHall, that.nameHall);
    }

    @Override
    public int hashCode() {
        int result = sessionId;
        result = 31 * result + (nameFilm != null ? nameFilm.hashCode() : 0);
        result = 31 * result + (nameHall != null ? nameHall.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}
