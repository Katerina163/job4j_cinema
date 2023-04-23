package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(String email);

    Optional<User> findById(int id);
}
