package com.rango.alere.controllers;

import com.rango.alere.controllers.dtos.UserRegisterDTO;
import com.rango.alere.controllers.helpers.RegisterHelper;
import com.rango.alere.entities.Usuario;
import com.rango.alere.facades.UserFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserFacade userFacade;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegisterDTO", RegisterHelper.getNewRegisterForm());
        return prepareRegisterPage(model);
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO userRegisterDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return prepareRegisterPage(model);
        }

        Usuario usuario = userFacade.registerUser(userRegisterDTO);
        if (Objects.isNull(usuario)) {
            log.info("User Register failed");
            model.addAttribute("message", "Erro ao registrar");
            return prepareRegisterPage(model);
        }
        redirectAttributes.addAttribute("message", "Usuario registrado com sucesso!");
        return "redirect:/";
    }



    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    private String prepareRegisterPage(Model model) {
        model.addAttribute("estados", RegisterHelper.getEstados());
        return "register";
    }
}
