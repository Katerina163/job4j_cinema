package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.HallDto;

import java.util.Optional;

public interface HallService {
    String getNameHallById(int id);

    Optional<HallDto> getHallByName(String name);
}
