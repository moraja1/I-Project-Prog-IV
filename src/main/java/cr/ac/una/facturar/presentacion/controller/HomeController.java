package cr.ac.una.facturar.presentacion.controller;

import cr.ac.una.facturar.business.service.PersonaService;
import cr.ac.una.facturar.data.dto.PersonaDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    private final PersonaService personaService;

    public HomeController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/home")
    public String getHomePage(Model model, HttpSession session){
        //Block home access
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        //Capture access data
        PersonaDto person;
        if((person = (PersonaDto) session.getAttribute("info")) == null) {
            person = getAttrsFromSession(session);
            session.setAttribute("info", person);
        }

        //Sets model
        model.addAttribute("user", person);

        //Find registered suppliers
        List<PersonaDto> persons = personaService.findAllRegisteredUsers();
        model.addAttribute("persons", persons);

        return "home";
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model, HttpSession session){
        PersonaDto person;
        if((person = (PersonaDto) session.getAttribute("info")) == null) person = getAttrsFromSession(session);

        model.addAttribute("user", person);

        //Envía la info a la ventana con el model
        return "perfil";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("user") PersonaDto user,
                                HttpSession session,
                                Model model){

        //Password incorrect
        if(!user.pass().equals(session.getAttribute("pass"))) {
            model.addAttribute("isAccepted", false);
            model.addAttribute("isReg", false);
            model.addAttribute("next", "/profile");

            return "confirmation";
        }

        //Update info
        user = normalizeUser(user, session);

        return "redirect:/profile";
    }

    private PersonaDto normalizeUser(PersonaDto user, HttpSession session) {
        return PersonaDto.builder()
                .dtype(user.dtype())
                .build();
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
