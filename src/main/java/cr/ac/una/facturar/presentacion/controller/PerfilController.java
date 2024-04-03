package cr.ac.una.facturar.presentacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile")
public class PerfilController {

    @GetMapping
    public String getPage(@RequestParam(value = "id") String id){

        return "perfil";
    }


}
