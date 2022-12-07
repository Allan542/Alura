package br.com.alura.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    
    @GetMapping("/hello")
    public String hello(Model model) { // Serve para não descer uma camada abaixo, que é a de servlets
        model.addAttribute("nome", "Mundo");
        return "hello";
    }
}
