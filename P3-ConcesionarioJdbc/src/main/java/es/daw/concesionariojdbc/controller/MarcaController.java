package es.daw.concesionariojdbc.controller;

import es.daw.concesionariojdbc.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("marcas", marcaService.listar());
        return "marcas/lista";
    }
}
