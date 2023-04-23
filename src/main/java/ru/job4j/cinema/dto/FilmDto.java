package ru.job4j.cinema.dto;

import java.util.Objects;

public class FilmDto {
    private int fileId;
    private String genreName;
    private String nameFilm;
    private String description;
    private int minimalAge;

    public FilmDto() {
    }

    public FilmDto(int fileId, String genreName, String nameFilm, String description, int minimalAge) {
        this.fileId = fileId;
        this.genreName = genreName;
        this.nameFilm = nameFilm;
        this.description = description;
        this.minimalAge = minimalAge;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getNameFilm() {
        return nameFilm;
    }

    public void setNameFilm(String nameFilm) {
        this.nameFilm = nameFilm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinimalAge() {
        return minimalAge;
    }

    public void setMinimalAge(int minimalAge) {
        this.minimalAge = minimalAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilmDto filmDto = (FilmDto) o;
        if (minimalAge != filmDto.minimalAge) {
            return false;
        }
        if (!Objects.equals(genreName, filmDto.genreName)) {
            return false;
        }
        if (!Objects.equals(nameFilm, filmDto.nameFilm)) {
            return false;
        }
        return Objects.equals(description, filmDto.description);
    }

    @Override
    public int hashCode() {
        int result = genreName != null ? genreName.hashCode() : 0;
        result = 31 * result + (nameFilm != null ? nameFilm.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + minimalAge;
        return result;
    }
}
