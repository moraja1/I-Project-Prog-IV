package cr.ac.una.facturar.presentacion.controller;

import cr.ac.una.facturar.business.mappers.ProductoMapper;
import cr.ac.una.facturar.business.service.*;
import cr.ac.una.facturar.data.dto.*;
import cr.ac.una.facturar.data.entities.Cuenta;
import cr.ac.una.facturar.data.entities.Producto;
import cr.ac.una.facturar.data.entities.Proveedor;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final PersonaService personaService;
    private final CuentaService cuentaService;
    private final ProveedorService proveedorService;
    private final ProductoService productosService;
    private final ClienteService clienteService;

    public HomeController(PersonaService personaService, CuentaService cuentaService, ProveedorService proveedorService, ClienteService clienteS ,ProductoService PS) {
        this.personaService = personaService;
        this.cuentaService = cuentaService;
        this.proveedorService = proveedorService;
        this.productosService = PS;
        this.clienteService= clienteS;
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
            List<FacturaDto> invoices = cuentaService.findFacturaDtoList(prov.getCuentaId());

            //CLIENTES
            List<PersonaDto> clients = cuentaService.findClientesDtoList(prov.getCuentaId());

            //PRODUCTOS
            List<ProductoDto> products = cuentaService.findProductosDtoList(prov.getCuentaId());

            //Update state
            session.setAttribute("invoices", invoices);
            session.setAttribute("clients", clients);
            session.setAttribute("products", products);
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
        if(p == null) return confirmationMessage(false, model, "/profile");

        //Update correct
        if((p = personaService.updatePersonProfile(p.id(), user)) != null) {
            session.setAttribute("user", p);
            return confirmationMessage(true, model, "/profile");
        }

        //Update incorrect
        else return confirmationMessage(false, model, "/profile");
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
        if(newProv == null) return confirmationMessage(false, model, "/requests");

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

    private String confirmationMessage(boolean b, Model model, String next) {
        model.addAttribute("isAccepted", b);
        model.addAttribute("isReg", b);
        model.addAttribute("next", next);

        return "confirmation";
    }
    //Dixon
    @GetMapping("/clients")
    public String getCliente(Model model, HttpSession session){
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("client", ClienteDto.builder().build());

        return "cliente";
    }

    @PostMapping("/clients/find")
    public String findCliente(@ModelAttribute("client") ClienteDto client, Model model, HttpSession session) {
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        System.out.println();
        return "";
    }

    @PostMapping("/clients")
    public String saveOrUpdateCliente(@ModelAttribute("client") ClienteDto cliente, HttpSession session, Model model) {
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        ProveedorDto prov = proveedorService.findById(((PersonaDto)session.getAttribute("user")).id());

        if(cliente != null) if(cuentaService.addClient(prov.getCuentaId(), cliente)) return confirmationMessage(true, model, "/clients");

        return confirmationMessage(false, model, "/clients");
    }
    //Dylan
    @GetMapping("/products")
    public String getProductos(Model model, HttpSession session){
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("product", ProductoDto.builder().build());

        return "productos";
    }

    @PostMapping("/products/find")
    public String findProduct(@ModelAttribute("product") ProductoDto product, Model model, HttpSession session) {
        Long id = (Long) model.getAttribute("busc");
        model.addAttribute("product", productosService.findProductoById(id));
        return "productos";
    }

    @PostMapping("/products")
    public String saveOrUpdateProduct(@ModelAttribute("product") ProductoDto producto, Model model, HttpSession session) {
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        ProveedorDto prov = proveedorService.findById(((PersonaDto)session.getAttribute("user")).id());

        if(producto != null) if(cuentaService.addProducto(prov.getCuentaId(), producto)) return confirmationMessage(true, model, "/products");

        return confirmationMessage(false, model, "/products");
    }

    @GetMapping("/invoices")
    public String getInvoices(Model model, HttpSession session){
        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        PersonaDto personaDto = (PersonaDto) session.getAttribute("user");
        model.addAttribute("prov", personaDto);
        model.addAttribute("invoice", FacturaDto.builder().build());

        //FOR TESTING
        Cuenta cuenta=new Cuenta();
        List<ProductoDto> productoDtoList = new ArrayList<>();
        productoDtoList.add(ProductoDto.builder().build());
        productoDtoList.add(ProductoDto.builder().build());
        productoDtoList.add(ProductoDto.builder().build());

        model.addAttribute("productsList", productoDtoList);

        model.addAttribute("clientes", session.getAttribute("clients"));
        model.addAttribute("productos", session.getAttribute("products"));
        return "invoices";
    }

    @PostMapping("/invoices")
    public String postInvoices(){
        //FOR TESTING
        return "redirect:/home";
    }
}
