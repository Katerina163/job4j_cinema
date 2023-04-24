package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.dto.HallDto;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.HallService;
import ru.job4j.cinema.service.TicketService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TicketControllerTest {
    private TicketService ticketService;
    private FilmSessionService filmSessionService;
    private HallService hallService;
    private TicketController controller;

    @BeforeEach
    public void init() {
        ticketService = mock(TicketService.class);
        filmSessionService = mock(FilmSessionService.class);
        hallService = mock(HallService.class);
        controller = new TicketController(ticketService, filmSessionService, hallService);
    }

    @Test
    public void whenGetLibrarySuccess() {
        var filmSession = new FilmSessionDto(1, "name", "name", "data", "time", 1);
        when(filmSessionService.getFilmSessionById(1)).thenReturn(Optional.of(filmSession));
        var hall = new HallDto(2, 2);
        when(hallService.getHallByName("name")).thenReturn(Optional.of(hall));
        var model = new ConcurrentModel();
        var response = new MockHttpServletResponse();
        var view = controller.getFilmLibrary(1, model, response);
        var film = model.getAttribute("film");
        var maxRow = model.getAttribute("maxRow");
        var maxPlace = model.getAttribute("maxPlace");
        var cookie = response.getCookie("session");
        assertThat(view).isEqualTo("ticket/buying");
        assertThat(film).isEqualTo(filmSession);
        assertThat(maxRow).isEqualTo(List.of("1", "2"));
        assertThat(maxPlace).isEqualTo(List.of("1", "2"));
        assertThat(cookie.getValue()).isEqualTo("1");
    }

    @Test
    public void whenGetLibraryFailed() {
        when(filmSessionService.getFilmSessionById(1)).thenReturn(Optional.empty());
        var model = new ConcurrentModel();
        var response = new MockHttpServletResponse();
        var view = controller.getFilmLibrary(1, model, response);
        var message = model.getAttribute("message");
        assertThat(view).isEqualTo("error");
        assertThat(message).isEqualTo("Сеанс с указанным идентификатором не найден");
    }

    @Test
    public void whenGetTicketBuyingPageSuccess() {
        var user = new User(1, "name", "email", "password");
        String sessionString = "1";
        var ticket = new Ticket(1, 1, 1, 1, 1);
        when(ticketService.buying(ticket)).thenReturn(Optional.of(ticket));
        var request = new MockHttpServletRequest();
        var session = new MockHttpSession();
        request.setSession(session);
        session.setAttribute("user", user);
        var response = new MockHttpServletResponse();
        var model = new ConcurrentModel();
        var view = controller.getTicketBuyingPage(ticket, sessionString, request, response, model);
        var cookieRow = response.getCookie("row");
        var cookiePlace = response.getCookie("place");
        assertThat(view).isEqualTo("redirect:/ticket/success");
        assertThat(cookieRow.getValue()).isEqualTo("1");
        assertThat(cookiePlace.getValue()).isEqualTo("1");
    }

    @Test
    public void whenGetTicketBuyingPageFailed() {
        var user = new User(1, "name", "email", "password");
        String sessionString = "1";
        var ticket = new Ticket(1, 1, 1, 1, 1);
        when(ticketService.buying(ticket)).thenReturn(Optional.empty());
        var request = new MockHttpServletRequest();
        var session = new MockHttpSession();
        request.setSession(session);
        session.setAttribute("user", user);
        var response = new MockHttpServletResponse();
        var model = new ConcurrentModel();
        var view = controller.getTicketBuyingPage(ticket, sessionString, request, response, model);
        var message = model.getAttribute("message");
        assertThat(view).isEqualTo("error");
        assertThat(message).isEqualTo("Билет на данное место уже куплен");
    }

    @Test
    public void whenGetSchedule() {
        var row = "1";
        var place = "1";
        var session = "1";
        var filmSession = new FilmSessionDto(1, "film", "hall", "data", "time", 1);
        when(filmSessionService.getFilmSessionById(1)).thenReturn(Optional.of(filmSession));
        var model = new ConcurrentModel();
        var view = controller.getSchedule(row, place, session, model);
        var film = model.getAttribute("film");
        var modelRow = model.getAttribute("row");
        var modelPlace = model.getAttribute("place");
        assertThat(view).isEqualTo("ticket/success");
        assertThat(film).isEqualTo(filmSession);
        assertThat(modelRow).isEqualTo("1");
        assertThat(modelPlace).isEqualTo("1");
    }
}