package springdictionary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("title", "Main");
        return "main";
    }
    @GetMapping("/dict")
    public String dict(Model model){
        model.addAttribute("title", "Main");
        return "main";
    }
}
