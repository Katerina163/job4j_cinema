package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.File;

import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
class Sql2oFileRepositoryTest {
    private static Sql2oFileRepository repository;
    private static File file;

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
        repository = new Sql2oFileRepository(sql2o);
        file = new File("File.jpg", "src\\files\\File.jpg");
    }

    @AfterAll
    public static void deleteFile() {
        repository.deleteById(file.getId());
    }

    @Test
    public void whenFind() {
        File result = repository.save(file);
        int id = result.getId();
        assertThat(result).isEqualTo(repository.findById(id).get());
    }

    @Test
    public void whenDelete() {
        File result = repository.save(file);
        assertThat(repository.deleteById(result.getId())).isEqualTo(true);
    }
}