package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Ticket;

import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oTicketRepositoryTest {

    private static TicketRepository repository;
    private static Ticket ticket;

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
        repository = new Sql2oTicketRepository(sql2o);
        ticket = new Ticket(1, 1, 1, 1, 1);
    }

    @AfterAll
    public static void delete() {
        repository.deleteById(ticket.getId());
    }

    @Test
    public void whenFind() {
        var result = repository.save(ticket).get();
        assertThat(result).isEqualTo(ticket);
    }

    @Test
    public void whenDelete() {
        var resultId = repository.save(ticket).get().getId();
        assertThat(repository.deleteById(resultId)).isEqualTo(true);
    }
}