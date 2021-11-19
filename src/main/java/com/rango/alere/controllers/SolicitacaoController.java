package com.rango.alere.controllers;

import com.rango.alere.controllers.helpers.RegisterHelper;
import com.rango.alere.entities.Solicitacao;
import com.rango.alere.entities.Usuario;
import com.rango.alere.entities.enums.Status;
import com.rango.alere.facades.UserFacade;
import com.rango.alere.services.impl.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private SolicitacaoService solicitacaoService;

    @ModelAttribute("currentUser")
    public Usuario getCurrentUser() {
        return userFacade.getCurrentUser();
    }

    @GetMapping("/{id}")
    public String getSolicitacao(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Solicitacao solicitacao = null;
        try {
            solicitacao = solicitacaoService.getById(id);
            Usuario currentUser = userFacade.getCurrentUser();
            if (currentUser != solicitacao.getDe() && currentUser != solicitacao.getPara()) {
                throw new Exception();
            }
        } catch (Exception e) {
            return "errors/404";
        }
        model.addAttribute("solicitacao", solicitacao);
        model.addAttribute("status", RegisterHelper.getStatus());
        return "solicitacoes/solicitacao";
    }

    @PostMapping("/{id}/responder")
    public String responderSolicitacao(@PathVariable Long id, @RequestParam("status") Status status, Model model, RedirectAttributes redirectAttributes) {
        Solicitacao solicitacao = null;
        try {
            solicitacao = solicitacaoService.getById(id);
            Usuario currentUser = userFacade.getCurrentUser();
            if (currentUser != solicitacao.getDe() && currentUser != solicitacao.getPara()) {
                throw new Exception();
            }
        } catch (Exception e) {
            return "errors/404";
        }
        try {
            userFacade.responderSolicitacao(solicitacao, status);
            return "redirect:/solicitacoes/" + id + "?resp";
        } catch (Exception e) {
            return "redirect:/solicitacoes/" + id + "?error";
        }
    }

}
