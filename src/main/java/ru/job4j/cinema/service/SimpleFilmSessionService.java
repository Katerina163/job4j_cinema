package ru.job4j.cinema.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.repository.FilmSessionRepository;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

import static java.time.format.FormatStyle.SHORT;

@ThreadSafe
@Service
public class SimpleFilmSessionService implements FilmSessionService {
    private final HallService simpleHallService;
    private final FilmService simpleFilmService;
    private final FilmSessionRepository sql2oFilmSessionRepository;

    public SimpleFilmSessionService(HallService simpleHallService,
                                    FilmService simpleFilmService,
                                    FilmSessionRepository sql2oFilmSessionRepository) {
        this.simpleHallService = simpleHallService;
        this.simpleFilmService = simpleFilmService;
        this.sql2oFilmSessionRepository = sql2oFilmSessionRepository;
    }

    @Override
    public Optional<FilmSessionDto> getFilmSessionById(int id) {
        return sql2oFilmSessionRepository.findById(id).map(this::build);
    }

    private FilmSessionDto build(FilmSession filmSession) {
        var filmSessionDto = new FilmSessionDto();
        filmSessionDto.setSessionId(filmSession.getId());
        filmSessionDto.setNameFilm(simpleFilmService.getNameFilmById(filmSession.getFilmId()));
        filmSessionDto.setNameHall(simpleHallService.getNameHallById(filmSession.getHallId()));
        filmSessionDto.setData(filmSession.getStartTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        String startTime = filmSession.getStartTime().format(DateTimeFormatter.ofLocalizedTime(SHORT));
        String endTime = filmSession.getEndTime().format(DateTimeFormatter.ofLocalizedTime(SHORT));
        filmSessionDto.setTime(startTime + " - " + endTime);
        filmSessionDto.setPrice(filmSession.getPrice());
        return filmSessionDto;
    }

    @Override
    public Collection<FilmSessionDto> findAll() {
        return sql2oFilmSessionRepository
                .findAll().stream().map(this::build).toList();
    }
}
