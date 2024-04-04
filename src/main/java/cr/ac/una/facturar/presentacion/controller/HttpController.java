package cr.ac.una.facturar.presentacion.controller;

import cr.ac.una.facturar.business.service.PersonaService;
import cr.ac.una.facturar.data.dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HttpController {
    private final PersonaService personaService;

    @Autowired
    private MessageSource messageSource;

    public HttpController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/")
    public String getSignInPage(Model model) {
        model.addAttribute("user", PersonaDto.builder().build());
        return "index";
    }

    @GetMapping("/signin")
    public String getAccess(@ModelAttribute("user") PersonaDto user, RedirectAttributes redirectAttributes) {
        PersonaDto person = personaService.userHasAccess(user.email(), user.pass());

        //Not registered or not authorized
        if(person == null) return "redirect:/";

        //Authenticated
        redirectAttributes.addFlashAttribute("person", person);
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("user", PersonaDto.builder().build());
        return "register";
    }

    @PostMapping("/register")
    public String registerSupplier(@ModelAttribute("register") PersonaDto person, Model model){
        //Validation of saving into db
        model.addAttribute("isAccepted", personaService.saveProveedor(person));

        return "confirmation";
    }
}
