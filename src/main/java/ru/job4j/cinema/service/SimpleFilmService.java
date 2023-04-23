package ru.job4j.cinema.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.repository.FilmRepository;

import java.util.Collection;
import java.util.Optional;

@ThreadSafe
@Service
public class SimpleFilmService implements FilmService {
    private final GenreService simpleGenreService;
    private final FilmRepository sql2oFilmRepository;

    public SimpleFilmService(GenreService simpleGenreService,
                             FilmRepository sql2oFilmRepository) {
        this.simpleGenreService = simpleGenreService;
        this.sql2oFilmRepository = sql2oFilmRepository;
    }

    @Override
    public String getNameFilmById(int id) {
        return sql2oFilmRepository.findById(id).map(Film::getName).orElse("");
    }

    @Override
    public Optional<FilmDto> getFilmById(int id) {
        return sql2oFilmRepository.findById(id).map(this::build);
    }

    private FilmDto build(Film film) {
        var filmDto = new FilmDto();
        filmDto.setFileId(film.getFileId());
        filmDto.setGenreName(simpleGenreService.getNameGenreById(film.getGenreId()));
        filmDto.setNameFilm(film.getName());
        filmDto.setDescription(film.getDescription());
        filmDto.setMinimalAge(film.getMinimalAge());
        return filmDto;
    }

    @Override
    public Collection<FilmDto> findAll() {
        return sql2oFilmRepository
                .findAll().stream().map(this::build).toList();
    }
}
