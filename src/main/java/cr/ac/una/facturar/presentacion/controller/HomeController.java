package cr.ac.una.facturar.presentacion.controller;

import cr.ac.una.facturar.data.dto.PersonaDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping
    public String getHomePage(@ModelAttribute("person") PersonaDto person, Model model){
        model.addAttribute("person", person);
        return "home";
    }
}
