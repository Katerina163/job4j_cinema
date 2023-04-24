package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.User;

import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oUserRepositoryTest {
        private static UserRepository repository;
        private static User user;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oFileRepositoryTest.class.getClassLoader()
                .getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");
        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);
        repository = new Sql2oUserRepository(sql2o);
        user = new User(1, "name", "email", "password");
    }

    @AfterAll
    public static void deleteFile() {
        repository.deleteById(user.getId());
    }

    @Test
    public void whenFind() {
        var result = repository.save(user);
        assertThat(result).isEqualTo(user);
    }

    @Test
    public void whenDelete() {
        var resultId = repository.save(user).getId();
        assertThat(repository.deleteById(resultId)).isEqualTo(true);
    }
}