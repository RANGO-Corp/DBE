package com.rango.alere.controllers;

import com.rango.alere.controllers.dtos.AlimentoInsertDTO;
import com.rango.alere.controllers.helpers.RegisterHelper;
import com.rango.alere.entities.Alimento;
import com.rango.alere.entities.Solicitacao;
import com.rango.alere.entities.Usuario;
import com.rango.alere.facades.AlimentoFacade;
import com.rango.alere.facades.UserFacade;
import com.rango.alere.services.impl.AlimentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
@RequestMapping("/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoFacade alimentoFacade;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private AlimentoService alimentoService;

    @ModelAttribute("currentUser")
    public Usuario getCurrentUser(){
        return userFacade.getCurrentUser();
    }


    @GetMapping
    public String getAlimentos(Model model, @PageableDefault(size = 12) Pageable pageable) {
        final Page<Alimento> alimentos = alimentoFacade.getAlimentosPage(pageable);
        int totalPages = alimentos.getTotalPages();
        if (totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("alimentos", alimentos);
        return "alimentos/alimentos";
    }

    @GetMapping("/{id}")
    public String getAlimento(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("alimento", alimentoService.getById(id));
        } catch (Exception e) {
            return "errors/404";
        }
        return "alimentos/alimento";
    }

    @PostMapping("/{id}/solicitar")
    public String solicitarAlimento(@PathVariable Long id, @RequestParam("mensagem") String mensagem, Model model, RedirectAttributes redirectAttributes) {
        Alimento alimento = null;
        try {
            alimento = alimentoService.getById(id);
        } catch (Exception e) {
            return "errors/404";
        }
        Usuario currentUser = userFacade.getCurrentUser();
        if (currentUser == alimento.getCadastradoPor()) {
            return "redirect:/?error";
        }

        Solicitacao solicitacao = userFacade.createSolicitacao(currentUser, mensagem, alimento);
        if (Objects.isNull(solicitacao)) {
            return "redirect:/?error";
        }
        return "redirect:/solicitacoes/" + solicitacao.getId() + "?success";
    }

    @GetMapping("/new")
    public String createAlimento(Model model) {
        model.addAttribute("alimentoInsertDTO", RegisterHelper.getNewAlimentoRegisterForm());
        return prepareAlimentoRegisterPage(model);
    }

    @PostMapping("/new")
    public String doCreateAlimento(@Valid AlimentoInsertDTO alimentoInsertDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return prepareAlimentoRegisterPage(model);
        }

        final Alimento alimento = alimentoFacade.registerAlimento(alimentoInsertDTO, userFacade.getCurrentUser());
        if (Objects.isNull(alimento)) {
            return prepareAlimentoRegisterPage(model);
        }

        redirectAttributes.addFlashAttribute("message", "Alimento cadastrado com sucesso!");
        return "redirect:/alimentos";
    }


    private String prepareAlimentoRegisterPage(Model model) {
        model.addAttribute("tiposAlimentos", RegisterHelper.getTiposAlimentos());
        return "alimentos/new-alimento";
    }
}
