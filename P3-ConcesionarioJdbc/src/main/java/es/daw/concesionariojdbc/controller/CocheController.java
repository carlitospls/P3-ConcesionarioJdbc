package es.daw.concesionariojdbc.controller;

import es.daw.concesionariojdbc.dto.CocheForm;
import es.daw.concesionariojdbc.model.Coche;
import es.daw.concesionariojdbc.service.MarcaService;
import es.daw.concesionariojdbc.service.CocheService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// URLs: GET /coches | GET /coches/nuevo | GET /coches/editar/{codigo}
//       POST /coches/guardar | POST /coches/borrar/{codigo}
@Controller
@RequestMapping("/coches")
@RequiredArgsConstructor
public class CocheController {

    private final CocheService cocheService;
    private final MarcaService marcaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("coches", cocheService.listar());
        return "coches/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cocheForm", new CocheForm());
        model.addAttribute("marcas", marcaService.listar());
        return "coches/formulario";
    }

    @GetMapping("/editar/{codigo}")
    public String editar(@PathVariable Integer codigo, Model model) {
        Coche coche = cocheService.buscarPorCodigo(codigo);
        model.addAttribute("cocheForm", CocheForm.toDto(coche));
        model.addAttribute("marcas", marcaService.listar());
        return "coches/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("cocheForm") CocheForm cocheForm,
                          BindingResult result,
                          Model model,
                          RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("marcas", marcaService.listar());
            return "coches/formulario";
        }

        if (cocheForm.getCodigo() == null) {
            Coche creado = cocheService.crear(cocheForm.toModelEntity());
            redirectAttributes.addFlashAttribute("mensaje",
                    "Coche creado con código " + creado.getCodigo());
        } else {
            cocheService.actualizar(cocheForm.toModelEntity());
            redirectAttributes.addFlashAttribute("mensaje",
                    "Coche " + cocheForm.getCodigo() + " actualizado");
        }
        return "redirect:/coches";
    }

    @PostMapping("/borrar/{codigo}")
    public String borrar(@PathVariable Integer codigo, RedirectAttributes redirectAttributes) {
        cocheService.borrar(codigo);
        redirectAttributes.addFlashAttribute("mensaje", "Coche " + codigo + " borrado");
        return "redirect:/coches";
    }
}
