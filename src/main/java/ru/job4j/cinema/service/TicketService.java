package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Ticket;

import java.util.Optional;

public interface TicketService {
    Optional<Ticket> findById(int id);

    Optional<Ticket> buying(Ticket ticket);
}
