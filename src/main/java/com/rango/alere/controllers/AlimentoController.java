package com.rango.alere.controllers;

import com.rango.alere.controllers.dtos.AlimentoInsertDTO;
import com.rango.alere.controllers.helpers.RegisterHelper;
import com.rango.alere.entities.Alimento;
import com.rango.alere.facades.AlimentoFacade;
import com.rango.alere.facades.UserFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return "alimentos";
    }

    @GetMapping("/{id}")
    public String getAlimento(@PathVariable Long id, Model model) {
        return "alimentos";
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
        return "new-alimento";
    }
}
