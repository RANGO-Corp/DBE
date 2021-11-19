package com.rango.alere.controllers;

import com.rango.alere.entities.Doacao;
import com.rango.alere.entities.Usuario;
import com.rango.alere.facades.UserFacade;
import com.rango.alere.services.impl.DoacaoService;
import com.rango.alere.services.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doacoes")
public class DoacaoController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DoacaoService doacaoService;


    @ModelAttribute("currentUser")
    public Usuario getCurrentUser() {
        return userFacade.getCurrentUser();
    }


    @GetMapping("/{id}")
    public String getDoacao(@PathVariable Long id, Model model) {
        Doacao doacao = null;
        try {
            doacao = doacaoService.getById(id);
            Usuario currentUser = userFacade.getCurrentUser();
            if (currentUser != doacao.getDoador() && currentUser != doacao.getReceptor()) {
                throw new Exception();
            }
            model.addAttribute("doacao", doacao);
        } catch (Exception e) {
            return "errors/404";
        }
        return "doacoes/doacao";
    }

}
