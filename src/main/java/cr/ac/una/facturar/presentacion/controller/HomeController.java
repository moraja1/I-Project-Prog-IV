package cr.ac.una.facturar.presentacion.controller;

import cr.ac.una.facturar.business.service.PersonaService;
import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.dto.ProveedorDto;
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
            //Set user to session
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
        //Block home access
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        //Set user from session to view
        model.addAttribute("user", getPersonFromSession(session));

        //Envía la info a la ventana con el model
        return "perfil";
    }

    private PersonaDto getPersonFromSession(HttpSession session) {
        PersonaDto person;
        if((person = (PersonaDto) session.getAttribute("info")) == null) person = getAttrsFromSession(session);
        return person;
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("user") PersonaDto user,
                                HttpSession session,
                                Model model){
        //Block home access
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        //Password incorrect
        if(!user.pass().equals(session.getAttribute("pass"))) return confirmationMessage(false, model);

        //Update info
        user = normalizeUser(user, session);

        //Update correct
        if(personaService.updatePersonProfile(user)) return confirmationMessage(true, model);

        //Update incorrect
        else return confirmationMessage(false, model);
    }

    @GetMapping("/requests")
    public String getRequestsPage(Model model, HttpSession session){
        //Block home access
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        //Set user from session to view
        model.addAttribute("user", getPersonFromSession(session));

        List<PersonaDto> unauthorizedProvs = personaService.findAllUnauthorizedProvs();
        model.addAttribute("persons", unauthorizedProvs);

        return "accesos";
    }

    private String confirmationMessage(boolean b, Model model) {
        model.addAttribute("isAccepted", false);
        model.addAttribute("isReg", false);
        model.addAttribute("next", "/profile");

        return "confirmation";
    }

    private PersonaDto normalizeUser(PersonaDto user, HttpSession session) {
        return PersonaDto.builder()
                .id((String) session.getAttribute("id"))
                .name(user.name())
                .lastName(user.lastName())
                .email(user.email())
                .phoneNumber(user.phoneNumber())
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
