package org.example.todolist.api.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    static final String HOME = "/";

    @GetMapping(value = HOME)
    public String homePage() {
        return "home";
    }
    //lol
}
