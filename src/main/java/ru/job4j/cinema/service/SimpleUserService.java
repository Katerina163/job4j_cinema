package ru.job4j.cinema.service;

import ru.job4j.cinema.model.User;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.repository.Sql2oUserRepository;

import java.util.Optional;

@ThreadSafe
@Service
public class SimpleUserService implements UserService {
    private final Sql2oUserRepository sql2oUserRepository;

    public SimpleUserService(Sql2oUserRepository sql2oUserRepository) {
        this.sql2oUserRepository = sql2oUserRepository;
    }

    @Override
    public User save(User user) {
        return sql2oUserRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return sql2oUserRepository.findById(email);
    }

    @Override
    public Optional<User> findById(int id) {
        return sql2oUserRepository.findById(id);
    }
}
