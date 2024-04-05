package cr.ac.una.facturar.presentacion.controller;

import cr.ac.una.facturar.business.service.PersonaService;
import cr.ac.una.facturar.data.dto.PersonaDto;
import jakarta.servlet.http.HttpSession;
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
    public String getHomePage(Model model, HttpSession session){
        PersonaDto person = getAttrsFromSession(session);
        model.addAttribute("user", person);
        List<PersonaDto> persons = personaService.findAllRegisteredUsers();
        model.addAttribute("persons", persons);

        return "home";
    }

    private PersonaDto getAttrsFromSession(HttpSession session) {
        return PersonaDto.builder()
                .id((String) session.getAttribute("id"))
                .name((String) session.getAttribute("name"))
                .lastName((String) session.getAttribute("lastName"))
                .phoneNumber((String) session.getAttribute("phone"))
                .email((String) session.getAttribute("email"))
                .dtype((String) session.getAttribute("role"))
                .build();
    }
}
