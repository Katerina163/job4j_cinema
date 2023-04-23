package ru.job4j.cinema.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.repository.TicketRepository;

import java.util.Optional;

@ThreadSafe
@Service
public class SimpleTicketService implements TicketService {
    private final TicketRepository sql2oTicketRepository;

    public SimpleTicketService(TicketRepository sql2oTicketRepository) {
        this.sql2oTicketRepository = sql2oTicketRepository;
    }

    @Override
    public Optional<Ticket> findById(int id) {
        return sql2oTicketRepository.findById(id);
    }

    @Override
    public Optional<Ticket> buying(Ticket ticket) {
        return sql2oTicketRepository.save(ticket);
    }
}
