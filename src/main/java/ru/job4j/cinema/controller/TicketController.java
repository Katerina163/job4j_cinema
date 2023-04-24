package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.HallService;
import ru.job4j.cinema.service.TicketService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.IntStream;

@ThreadSafe
@Service
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final FilmSessionService filmSessionService;
    private final HallService hallService;

    public TicketController(TicketService ticketService,
                            FilmSessionService filmSessionService,
                            HallService hallService) {
        this.ticketService = ticketService;
        this.filmSessionService = filmSessionService;
        this.hallService = hallService;
    }

    @GetMapping("/{id}")
    public String getFilmLibrary(@PathVariable int id, Model model,
                                 HttpServletResponse response) {
        var filmSessionOptional = filmSessionService.getFilmSessionById(id);
        if (filmSessionOptional.isEmpty()) {
            model.addAttribute("message", "Сеанс с указанным идентификатором не найден");
            return "error";
        }
        var hall = hallService.getHallByName(filmSessionOptional.get().getNameHall()).get();
        model.addAttribute("film", filmSessionOptional.get())
                .addAttribute("maxRow", getListNumber(hall.getMaxRow()))
                .addAttribute("maxPlace", getListNumber(hall.getMaxPlace()))
                .addAttribute("ticket", new Ticket());
        response.addCookie(new Cookie("session", Integer.toString(id)));
        return "ticket/buying";
    }

    private List<String> getListNumber(int number) {
        return IntStream.rangeClosed(1, number).mapToObj(String::valueOf).toList();
    }

    @PostMapping("/buying")
    public String getTicketBuyingPage(@ModelAttribute Ticket ticket,
                                      @CookieValue(value = "session") String session,
                                      HttpServletRequest request,
                                      HttpServletResponse response, Model model) {
        ticket.setSessionId(Integer.parseInt(session));
        User user = (User) request.getSession().getAttribute("user");
        ticket.setUserId(user.getId());
        var tryBuying = ticketService.buying(ticket);
        if (tryBuying.isEmpty()) {
            model.addAttribute("message", "Билет на данное место уже куплен");
            return "error";
        }
        response.addCookie(new Cookie("row", Integer.toString(ticket.getRowNumber())));
        response.addCookie(new Cookie("place", Integer.toString(ticket.getPlaceNumber())));
        return "redirect:/ticket/success";
    }

    @GetMapping("/success")
    public String getSchedule(@CookieValue(value = "row") String row, @CookieValue(value = "place") String place,
                              @CookieValue(value = "session") String session, Model model) {
        var filmSession = filmSessionService.getFilmSessionById(Integer.parseInt(session));
        model.addAttribute("film", filmSession.get());
        model.addAttribute("row", row);
        model.addAttribute("place", place);
        return "ticket/success";
    }
}
