package es.daw.concesionariojdbc.controller;

import es.daw.concesionariojdbc.service.CocheService;
import es.daw.concesionariojdbc.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;
    private final CocheService cocheService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("marcas", marcaService.listar());
        return "marcas/lista";
    }

    @GetMapping("/{codigo}/coches")
    public String cochesDeLaMarca(@PathVariable("codigo") Integer codigoMarca, Model model) {

        model.addAttribute("marca", marcaService.buscarPorCodigo(codigoMarca) );
        model.addAttribute("coches", cocheService.listarPorMarca(codigoMarca));

        return "marcas/cochesDeLaMarca";
    }


}
