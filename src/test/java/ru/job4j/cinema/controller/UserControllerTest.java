package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {
    private UserController controller;
    private UserService userService;

    @BeforeEach
    public void init() {
        userService = mock(UserService.class);
        controller = new UserController(userService);
    }

    @Test
    public void whenRegisterSuccess() {
        var user = new User(1, "name", "email", "password");
        when(userService.save(user)).thenReturn(user);
        Model model = new ConcurrentModel();
        var view = controller.register(user, model);
        assertThat(view).isEqualTo("redirect:/index");
    }

    @Test
    public void whenRegisterFailed() {
        User user = new User(1, "name", "email", "password");
        when(userService.save(user)).thenReturn(null);
        Model model = new ConcurrentModel();
        var view = controller.register(user, model);
        var message = model.getAttribute("message");
        assertThat(view).isEqualTo("error");
        assertThat(message).isEqualTo("Пользователь с такой почтой уже существует");
    }

    @Test
    public void whenLoginSuccess() {
        User user = new User(1, "name", "email", "password");
        when(userService.findByEmail("email")).thenReturn(Optional.of(user));
        Model model = new ConcurrentModel();
        HttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute("user", user);
        var view = controller.loginUser(user, model, request);
        assertThat(view).isEqualTo("redirect:/index");
    }

    @Test
    public void whenLoginFailed() {
        User user = new User(1, "name", "email", "password");
        when(userService.findByEmail("email")).thenReturn(Optional.empty());
        Model model = new ConcurrentModel();
        HttpServletRequest request = new MockHttpServletRequest();
        var view = controller.loginUser(user, model, request);
        var message = model.getAttribute("error");
        assertThat(view).isEqualTo("user/login");
        assertThat(message).isEqualTo("Почта или пароль введены неверно");
    }
}