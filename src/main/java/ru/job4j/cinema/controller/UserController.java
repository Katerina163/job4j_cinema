package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegistrationPage() {
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        var savedUser = userService.save(user);
        if (savedUser == null) {
            model.addAttribute("message", "Пользователь с такой почтой уже существует");
            return "error";
        }
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpServletRequest request) {
        var userOptional = userService.findByEmail(user.getEmail());
        if (userOptional.isEmpty()) {
            model.addAttribute("error", "Почта или пароль введены неверно");
            return "user/login";
        }
        var session = request.getSession();
        session.setAttribute("user", userOptional.get());
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }
}
