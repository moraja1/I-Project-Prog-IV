package cr.ac.una.facturar.presentacion.controller;

import cr.ac.una.facturar.business.service.CuentaService;
import cr.ac.una.facturar.business.service.PersonaService;
import cr.ac.una.facturar.business.service.ProductoService;
import cr.ac.una.facturar.business.service.ProveedorService;
import cr.ac.una.facturar.data.dto.FacturaDto;
import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.dto.ProductoDto;
import cr.ac.una.facturar.data.dto.ProveedorDto;
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
    private final ProveedorService proveedorService;

    private final ProductoService productosService;

    public HomeController(PersonaService personaService, CuentaService cuentaService, ProveedorService proveedorService, ProductoService PS) {
        this.personaService = personaService;
        this.cuentaService = cuentaService;
        this.proveedorService = proveedorService;
        this.productosService = PS;
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
        if(person.dtype().equals("Admin")) {
            List<PersonaDto> authorizedProvs = (List<PersonaDto>) session.getAttribute("auth");
            if (authorizedProvs == null) {
                authorizedProvs = personaService.findAllAuthorizedProvs();
                session.setAttribute("auth", authorizedProvs);
            }
            model.addAttribute("auth", authorizedProvs);
        }
        if(person.dtype().equals("Proveedor")) {
            //Obtains prov
            ProveedorDto prov = proveedorService.findById(person.id());

            //FACTURAS
            List<FacturaDto> invoices = (List<FacturaDto>) session.getAttribute("invoices");
            if (invoices == null) {
                invoices = cuentaService.findFacturaDtoList(prov.getCuentaId());
                session.setAttribute("invoices", invoices);
            }

            //CLIENTES
            List<PersonaDto> clients = (List<PersonaDto>) session.getAttribute("clients");
            if (clients == null) {
                clients = cuentaService.findClientesDtoList(prov.getCuentaId());
                session.setAttribute("clients", clients);
            }

            //PRODUCTOS
            List<ProductoDto> products = (List<ProductoDto>) session.getAttribute("products");
            if (products == null) {
                products = cuentaService.findProductosDtoList(prov.getCuentaId());
                session.setAttribute("products", products);
            }
            model.addAttribute("invoices", invoices);
            model.addAttribute("clients", clients);
            model.addAttribute("products", products);
        }

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
        PersonaDto newAccess = personaService.findById(person);
        ProveedorDto newProv = cuentaService.addCuentaToProv(newAccess);

        //Evaluate personaService output
        if(newAccess == null) return confirmationMessage(false, model);

        //Use cuentaService to give an Acc to prov
        cuentaService.addCuentaToProv(newAccess);


        //Update unauthorized provs list
        List<PersonaDto> unauthorizedProvs = (List<PersonaDto>) session.getAttribute("unauth");
        unauthorizedProvs = unauthorizedProvs.stream().filter(a -> !(a.id().equals(newAccess.id()))).collect(Collectors.toList());
        session.setAttribute("unauth", unauthorizedProvs);

        List<PersonaDto> authorizedProvs = (List<PersonaDto>) session.getAttribute("auth");
        authorizedProvs.add(newAccess);
        session.setAttribute("auth", authorizedProvs);

        return "redirect:/requests";
    }

    private String confirmationMessage(boolean b, Model model) {
        model.addAttribute("isAccepted", b);
        model.addAttribute("isReg", b);
        model.addAttribute("next", "/profile");

        return "confirmation";
    }

    @GetMapping("/products")
    public String getProductos(Model model, HttpSession session){
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";
        model.addAttribute("user", session.getAttribute("user"));
        return "productos";
    }

    @GetMapping("/invoices")
    public String getInvoices(Model model, HttpSession session){
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";
        return "invoices";
    }
}
