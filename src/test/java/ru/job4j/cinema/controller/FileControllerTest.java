package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.service.FileService;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FileControllerTest {
    private FileController controller;
    private FileService fileService;
    private MultipartFile testFile;

    @BeforeEach
    public void init() {
        fileService = mock(FileService.class);
        controller = new FileController(fileService);
        testFile = new MockMultipartFile(
                "testFile.img", new byte[] {1, 2, 3});
    }

    @Test
    public void whenCorrect() throws IOException {
        var file = new FileDto(testFile.getOriginalFilename(), testFile.getBytes());
        when(fileService.getFileById(1)).thenReturn(Optional.of(file));
        var view = controller.getById(1);
        assertThat(view).isEqualTo(ResponseEntity.ok(file.getContent()));
    }

    @Test
    public void whenEmpty() {
        when(fileService.getFileById(1)).thenReturn(Optional.empty());
        var view = controller.getById(1);
        assertThat(view).isEqualTo(ResponseEntity.notFound().build());
    }
}