package cr.ac.una.facturar.presentacion.controller;

import cr.ac.una.facturar.business.service.PersonaService;
import cr.ac.una.facturar.data.dto.PersonaDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final PersonaService personaService;

    public HomeController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public String getHomePage(@ModelAttribute("person") PersonaDto user, Model model){
        model.addAttribute("user", user);
        List<PersonaDto> persons = personaService.findAllRegisteredUsers();
        model.addAttribute("persons", persons);

        return "home";
    }
}
