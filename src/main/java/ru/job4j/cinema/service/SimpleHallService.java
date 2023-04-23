package ru.job4j.cinema.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.HallDto;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.HallRepository;

import java.util.Optional;

@ThreadSafe
@Service
public class SimpleHallService implements HallService {
    private final HallRepository sql2oHallRepository;

    public SimpleHallService(HallRepository sql2oHallRepository) {
        this.sql2oHallRepository = sql2oHallRepository;
    }

    @Override
    public String getNameHallById(int id) {
        var hallOptional = sql2oHallRepository.findById(id);
        return hallOptional.map(Hall::getName).orElse("");
    }

    @Override
    public Optional<HallDto> getHallByName(String name) {
        var hallOptional = sql2oHallRepository.findByName(name);
        if (hallOptional.isEmpty()) {
            return Optional.empty();
        }
        var hall = hallOptional.get();
        return Optional.of(new HallDto(hall.getRowCount(), hall.getPlaceCount()));
    }
}
