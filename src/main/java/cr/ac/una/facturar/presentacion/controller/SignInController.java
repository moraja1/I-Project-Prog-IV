package cr.ac.una.facturar.presentacion.controller;

import cr.ac.una.facturar.business.service.impl.AdminServiceImplement;
import cr.ac.una.facturar.business.service.impl.ProveedorServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {
    private final ProveedorServiceImplement proveedorService;
    private final AdminServiceImplement adminService;

    @Autowired
    public SignInController(ProveedorServiceImplement proveedorService, AdminServiceImplement adminService) {
        this.proveedorService = proveedorService;
        this.adminService = adminService;
    }

    @GetMapping("/signin")
    public String getAccess(Model model) {
        //Aqui recibe parametros

        //email
        //contrasena

        //busca al usuario

        //Despliega el perfil

        //Si tiene acceso
        return "Perfil";

        //Si no tiene acceso
        return "Otra cosa";
    }
}
