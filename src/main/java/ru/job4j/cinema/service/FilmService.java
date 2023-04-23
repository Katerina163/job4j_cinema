package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.FilmDto;

import java.util.Collection;
import java.util.Optional;

public interface FilmService {
    String getNameFilmById(int id);

    Optional<FilmDto> getFilmById(int id);

    Collection<FilmDto> findAll();
}