package com.rango.alere.controllers;

import com.rango.alere.entities.Usuario;
import com.rango.alere.facades.UserFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @ModelAttribute("currentUser")
    private Usuario getCurrentUser() {
        return userFacade.getCurrentUser();
    }


    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }


    @GetMapping("/solicitacoes")
    public String getSolicitacoes(Model model) {
        return "solicitacoes/user-solicitacoes";
    }

    @GetMapping("/doacoes")
    public String getDoacoes(Model model) {
        return "doacoes/user-doacoes";
    }
}
