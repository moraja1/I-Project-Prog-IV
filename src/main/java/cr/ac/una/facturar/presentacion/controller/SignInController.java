package cr.ac.una.facturar.presentacion.controller;

import cr.ac.una.facturar.business.service.PersonaService;
import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.presentacion.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class SignInController {
    private final PersonaService personaService;

    public SignInController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/")
    public String getSignInInfo(Model model) {
        Usuario user = new Usuario("","");
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/signin")
    public String getAccess(@ModelAttribute("user") Usuario user, RedirectAttributes model) {
        PersonaDto person = personaService.userHasAccess(user.email(), user.password());
        if(person != null){
            model.addFlashAttribute("person", person);
            return "redirect:/home";
        } else {
            return "redirect:/";
        }
    }
}
