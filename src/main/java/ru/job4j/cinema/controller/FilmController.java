package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.FilmSessionService;

@ThreadSafe
@Controller
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;
    private final FilmSessionService filmSessionService;

    public FilmController(FilmService filmService, FilmSessionService filmSessionService) {
        this.filmService = filmService;
        this.filmSessionService = filmSessionService;
    }

    @GetMapping("/library")
    public String getFilmsLibrary(Model model) {
        model.addAttribute("films", filmService.findAll());
        return "films/library";
    }

    @GetMapping("/schedule")
    public String getSchedule(Model model) {
        model.addAttribute("films", filmSessionService.findAll());
        return "films/schedule";
    }
}
