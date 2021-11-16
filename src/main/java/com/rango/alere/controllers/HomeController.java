package com.rango.alere.controllers;

import com.rango.alere.facades.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("currentUser", userFacade.getCurrentUser());
        return "home";
    }

}
