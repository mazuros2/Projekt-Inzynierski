package dev.projekt_inzynierski.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home(){
        return "Test czy działa użytkownik";
    }

}
