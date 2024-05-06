package com.learn.keycloak.controller;

import com.learn.keycloak.model.User;
import com.learn.keycloak.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/")
    public String index() {
        return "external";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }

    @GetMapping(path = "/users")
    public String customers(Principal principal, Model model) {
        addUsers();
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("username", principal.getName());
        return "users";
    }

    // TODO: add users from database
    public void addUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setPassword("user11");
        user1.setUsername("user11");
        userRepository.save(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setPassword("user22");
        user2.setUsername("user22");
        userRepository.save(user2);
    }
}
