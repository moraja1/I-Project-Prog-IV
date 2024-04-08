package cr.ac.una.facturar.presentacion.controller;

import cr.ac.una.facturar.business.service.PersonaService;
import cr.ac.una.facturar.data.dto.PersonaDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HttpController {
    private final PersonaService personaService;

    public HttpController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/")
    public String getSignInPage(Model model, HttpSession session) {
        if(session.getAttribute("access") != null) if((Boolean) session.getAttribute("access")) return "redirect:/home";

        model.addAttribute("sessionId", session.getId());
        model.addAttribute("user", PersonaDto.builder().build());

        return "index";
    }

    @GetMapping("/signin")
    public String getAccess(@ModelAttribute("user") PersonaDto user,
                            HttpSession session) {

        PersonaDto person = personaService.userHasAccess(user.email(), user.pass());

        //Not registered or not authorized
        if(person == null) return "redirect:/";

        //Authenticated
        session.setAttribute("user", person);
        session.setAttribute("access", true);

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
        model.addAttribute("isReg", true);
        model.addAttribute("next", "/");

        return "confirmation";
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session){
        session.setAttribute("access", false);
        session.invalidate();
        return "redirect:/";
    }
}
