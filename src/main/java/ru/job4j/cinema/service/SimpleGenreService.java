package ru.job4j.cinema.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.GenreRepository;

@ThreadSafe
@Service
public class SimpleGenreService implements GenreService {
    private final GenreRepository sql2oGenreRepository;

    public SimpleGenreService(GenreRepository sql2oGenreRepository) {
        this.sql2oGenreRepository = sql2oGenreRepository;
    }

    @Override
    public String getNameGenreById(int id) {
        var genreOptional = sql2oGenreRepository.findById(id);
        return genreOptional.map(Genre::getName).orElse("");
    }
}
