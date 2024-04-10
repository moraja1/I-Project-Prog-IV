package cr.ac.una.facturar.presentacion.controller;

import cr.ac.una.facturar.business.service.CuentaService;
import cr.ac.una.facturar.business.service.PersonaService;
import cr.ac.una.facturar.data.dto.PersonaDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final PersonaService personaService;
    private final CuentaService cuentaService;

    public HomeController(PersonaService personaService, CuentaService cuentaService) {
        this.personaService = personaService;
        this.cuentaService = cuentaService;
    }

    @GetMapping("/home")
    public String getHomePage(Model model, HttpSession session){
        //Block home access
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        //Capture access data
        PersonaDto person = (PersonaDto) session.getAttribute("user");

        //Sets model
        model.addAttribute("user", person);

        //Find registered suppliers
        List<PersonaDto> authorizedProvs = (List<PersonaDto>) session.getAttribute("auth");
        if(authorizedProvs == null) {
            authorizedProvs = personaService.findAllAuthorizedProvs();
            session.setAttribute("auth", authorizedProvs);
        }
        model.addAttribute("auth", authorizedProvs);

        return "home";
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model, HttpSession session){
        //Block home access
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        //Set user from session to view
        model.addAttribute("user", session.getAttribute("user"));

        //Envía la info a la ventana con el model
        return "perfil";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("user") PersonaDto user,
                                HttpSession session,
                                Model model){
        //Block home access
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        //Password incorrect
        PersonaDto p = (PersonaDto) session.getAttribute("user");
        p = personaService.userHasAccess(p.email(), user.pass());
        if(p == null) return confirmationMessage(false, model);

        //Update correct
        if((p = personaService.updatePersonProfile(p.id(), user)) != null) {
            session.setAttribute("user", p);
            return confirmationMessage(true, model);
        }

        //Update incorrect
        else return confirmationMessage(false, model);
    }

    @GetMapping("/requests")
    public String getRequestsPage(Model model, HttpSession session){
        //Block home access
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        //Set user from session to view
        model.addAttribute("user", session.getAttribute("user"));

        //Get unathorized list
        List<PersonaDto> unauthorizedProvs = (List<PersonaDto>) session.getAttribute("unauth");
        if(unauthorizedProvs == null) {
            unauthorizedProvs = personaService.findAllUnauthorizedProvs();
            session.setAttribute("unauth", unauthorizedProvs);
        }
        model.addAttribute("unauth", unauthorizedProvs);

        return "accesos";
    }

    @GetMapping("/giveAccess/")
    public String giveAccess(@RequestParam("person") String person, Model model, HttpSession session) {
        //Give access to proveedor
        PersonaDto newAccess = personaService.giveAccessToProv(person);

        //Evaluate personaService output
        if(newAccess == null) return confirmationMessage(false, model);

        //Use cuentaService to give an Acc to prov
        cuentaService.addCuentaToProv(newAccess);


        //Update unauthorized provs list
        List<PersonaDto> unauthorizedProvs = (List<PersonaDto>) session.getAttribute("unauth");
        unauthorizedProvs = unauthorizedProvs.stream().filter(a -> !(a.id().equals(newAccess.id()))).collect(Collectors.toList());
        session.setAttribute("unauth", unauthorizedProvs);

        return "redirect:/requests";
    }

    private String confirmationMessage(boolean b, Model model) {
        model.addAttribute("isAccepted", b);
        model.addAttribute("isReg", b);
        model.addAttribute("next", "/profile");

        return "confirmation";
    }
}
