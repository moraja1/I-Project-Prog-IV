package cr.ac.una.facturar.presentacion.controller;

import cr.ac.una.facturar.business.mappers.ClienteMapper;
import cr.ac.una.facturar.business.service.*;
import cr.ac.una.facturar.data.dto.*;
import cr.ac.una.facturar.data.entities.Cliente;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final PersonaService personaService;
    private final CuentaService cuentaService;
    private final ProveedorService proveedorService;
    private final ProductoService productosService;
    private final FacturaService facturaService;
    private final ClienteService clienteService;


    public HomeController(PersonaService personaService, CuentaService cuentaService, ProveedorService proveedorService,
                          ProductoService PS, FacturaService facturaService, ClienteService clienteS) {
        this.personaService = personaService;
        this.cuentaService = cuentaService;
        this.proveedorService = proveedorService;
        this.productosService = PS;
        this.facturaService = facturaService;
        this.clienteService = clienteS;
    }

    @GetMapping("/home")
    public String getHomePage(Model model, HttpSession session) {
        //Block home access
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";
        //Capture access data
        PersonaDto person = (PersonaDto) session.getAttribute("user");

        //Sets model
        model.addAttribute("user", person);

        //Find registered suppliers
        if (person.dtype().equals("Admin")) {
            List<PersonaDto> authorizedProvs = (List<PersonaDto>) session.getAttribute("auth");
            if (authorizedProvs == null) {
                authorizedProvs = personaService.findAllAuthorizedProvs();
                session.setAttribute("auth", authorizedProvs);
            }
            model.addAttribute("auth", authorizedProvs);
        }
        if (person.dtype().equals("Proveedor")) {
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
    public String getProfilePage(Model model, HttpSession session) {
        //Block home access
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";

        //Set user from session to view
        model.addAttribute("user", session.getAttribute("user"));

        //Envía la info a la ventana con el model
        return "perfil";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("user") PersonaDto user,
                                HttpSession session,
                                Model model) {
        //Block home access
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";

        //Password incorrect
        PersonaDto p = (PersonaDto) session.getAttribute("user");
        p = personaService.userHasAccess(p.email(), user.pass());
        if (p == null) return confirmationMessage(false, model, "/profile");

        //Update correct
        if ((p = personaService.updatePersonProfile(p.id(), user)) != null) {
            session.setAttribute("user", p);
            return confirmationMessage(true, model, "/profile");
        }

        //Update incorrect
        else return confirmationMessage(false, model, "/profile");
    }

    @GetMapping("/requests")
    public String getRequestsPage(Model model, HttpSession session) {
        //Block home access
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";

        //Set user from session to view
        model.addAttribute("user", session.getAttribute("user"));

        //Get unathorized list
        List<PersonaDto> unauthorizedProvs = (List<PersonaDto>) session.getAttribute("unauth");
        if (unauthorizedProvs == null) {
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
        if (newProv == null) return confirmationMessage(false, model, "/requests");

        //Use cuentaService to give an Acc to prov
        cuentaService.addCuentaToProv(newAccess);


        //Update unauthorized provs list
        List<PersonaDto> unauthorizedProvs = new ArrayList<>((List<PersonaDto>) session.getAttribute("unauth"));
        if(unauthorizedProvs == null) {
            unauthorizedProvs = personaService.findAllUnauthorizedProvs();
            session.setAttribute("unauth", unauthorizedProvs);
        }
        unauthorizedProvs = unauthorizedProvs.stream().filter(a -> !(a.id().equals(newAccess.id()))).collect(Collectors.toList());
        session.setAttribute("unauth", unauthorizedProvs);

        List<PersonaDto> authorizedProvs = new ArrayList<>((List<PersonaDto>) session.getAttribute("auth"));
        if (authorizedProvs == null) {
            authorizedProvs = personaService.findAllAuthorizedProvs();
            session.setAttribute("auth", authorizedProvs);
        }
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
    public String getCliente(Model model, HttpSession session) {
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";

        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("client", ClienteDto.builder().build());

        return "cliente";
    }

    @GetMapping("/clients/find")
    public String getClientInfo(Model model, HttpSession session, @PathParam("busc") String id) {
        //Block home access
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";

        PersonaDto p = (PersonaDto) session.getAttribute("user");
        model.addAttribute("user", p);

        ProveedorDto prov = proveedorService.findById(((PersonaDto) session.getAttribute("user")).id());
        List<PersonaDto> clients = cuentaService.findClientesDtoList(prov.getCuentaId());

        for (PersonaDto client : clients) {
            if (Objects.equals(client.id(), id)) model.addAttribute("clienteInfo", client);
        }
        if (model.getAttribute("clienteInfo") == null) return confirmationMessage(false, model, "/clients");
        return "clientUpdate";
    }
    @PostMapping("/clients/find")
    public String findCliente(@ModelAttribute("clienteInfo") ClienteDto client, Model model, HttpSession session, @PathParam("id") String id) {
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";

        PersonaDto p = (PersonaDto) session.getAttribute("user");
        if (client == null) return confirmationMessage(false, model, "/clients");

        Cliente c = ClienteMapper.mapClienteDtoToCliente(client);
        c.setId(id);
        client = ClienteMapper.mapClienteToClienteDto(c);

        PersonaDto person = ClienteMapper.mapClienteDtoToPersonaDto(client);
        ClienteDto dto = clienteService.updateClientInfo(person.id(), person);

        if (dto != null) {
            session.setAttribute("clientUpdated", dto);
            return confirmationMessage(true, model, "/clients");
        } else return confirmationMessage(false, model, "/clients");
    }

    @PostMapping("/clients")
    public String saveOrUpdateCliente(@ModelAttribute("client") ClienteDto cliente, HttpSession session, Model model) {
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";

        ProveedorDto prov = proveedorService.findById(((PersonaDto) session.getAttribute("user")).id());

        if (cliente != null) if (!(cuentaService.addClient(prov.getCuentaId(), cliente)))
            return confirmationMessage(false, model, "/clients");

        List<PersonaDto> clients = cuentaService.findClientesDtoList(prov.getCuentaId());
        session.setAttribute("clients", clients);

        return confirmationMessage(true, model, "/clients");


    }

    //Dylan
    @GetMapping("/products")
    public String getProductos(Model model, HttpSession session) {
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";

        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("product", ProductoDto.builder().build());

        return "productos";
    }

    @PostMapping("/products/find")
    public String findProduct(Model model, HttpSession session, @PathParam("busc") Long id) {
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";

        PersonaDto p = (PersonaDto) session.getAttribute("user");
        model.addAttribute("user",p);

        ProveedorDto prov = proveedorService.findById(((PersonaDto)session.getAttribute("user")).id());
        List<ProductoDto> products = cuentaService.findProductosDtoList(prov.getCuentaId());

        for (ProductoDto product : products) {
            if (Objects.equals(product.getId(), id)) model.addAttribute("productoInfo", product);
        }
        if (model.getAttribute("productoInfo")==null) return confirmationMessage(false, model, "/products");
        return "productUpdate";
    }

    @PostMapping("/products")
    public String saveOrUpdateProduct(@ModelAttribute("product") ProductoDto producto, Model model, HttpSession session) {
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";

        ProveedorDto prov = proveedorService.findById(((PersonaDto) session.getAttribute("user")).id());

        if (producto != null) if (!(cuentaService.addProducto(prov.getCuentaId(), producto)))
            return confirmationMessage(false, model, "/products");

        List<ProductoDto> products = cuentaService.findProductosDtoList(prov.getCuentaId());
        session.setAttribute("products", products);

        return confirmationMessage(true, model, "/products");
    }

    @GetMapping("/invoices")
    public String getInvoices(Model model, HttpSession session) {
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";
        model.addAttribute("user", session.getAttribute("user"));
        //removeInvoiceAttrs(session);

        //Client addition controller
        Boolean hasClient = (Boolean) session.getAttribute("hasClient");
        if (hasClient == null) {
            hasClient = false;
            session.setAttribute("hasClient", hasClient);
        }
        model.addAttribute("hasClient", hasClient);

        //Products addition controller
        Boolean hasProducts = (Boolean) session.getAttribute("hasProducts");
        if (hasProducts == null) {
            hasProducts = false;
            session.setAttribute("hasProducts", hasProducts);
        }
        model.addAttribute("hasProducts", hasProducts);

        //CLIENT SELECTED
        PersonaDto clientSelected = (PersonaDto) session.getAttribute("clientSelected");
        if (clientSelected == null) {
            clientSelected = PersonaDto.builder().build();
            session.setAttribute("clientSelected", clientSelected);
        }
        model.addAttribute("clientSelected", clientSelected);

        //REGISTERED PRODUCTS
        List<ProductoDto> products = (List<ProductoDto>) session.getAttribute("products");
        if (products == null) {
            ProveedorDto prov = proveedorService.findById(((PersonaDto) session.getAttribute("user")).id());
            products = cuentaService.findProductosDtoList(prov.getCuentaId());
        }
        model.addAttribute("products", products);

        //SELECTED PRODUCTS
        List<FacturaProductoCantidadDto> productsSelected = (List<FacturaProductoCantidadDto>) session.getAttribute("productsSelected");
        if (productsSelected == null) {
            productsSelected = new ArrayList<>();
            session.setAttribute("productsSelected", productsSelected);
        }
        model.addAttribute("productsSelected", productsSelected);
        model.addAttribute("adition", FacturaProductoCantidadDto.builder().build());

        //REGISTERED CLIENTS
        List<PersonaDto> clients = (List<PersonaDto>) session.getAttribute("clients");
        model.addAttribute("clients", clients);

        //FACTURA TO CREATE
        FacturaDto facturaDto = (FacturaDto) session.getAttribute("invoice");
        if (facturaDto == null) {
            facturaDto = FacturaDto.builder().build();
            session.setAttribute("invoice", facturaDto);
        }
        model.addAttribute("invoice", facturaDto);
        model.addAttribute("date", new Date());

        return "invoices";
    }

    private void removeInvoiceAttrs(HttpSession session) {
        session.removeAttribute("hasClient");
        session.removeAttribute("hasProducts");
        session.removeAttribute("clientSelected");
        session.removeAttribute("productsSelected");
    }

    @PostMapping("/invoices/client/")
    public String addClientToInvoice(@ModelAttribute("clientSelected") PersonaDto clientSelected, Model model, HttpSession session) {
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";

        session.setAttribute("hasClient", true);
        clientSelected = personaService.findById(clientSelected.id());
        if (clientSelected == null) {
            removeInvoiceAttrs(session);
            return confirmationMessage(false, model, "/home");
        }
        session.setAttribute("clientSelected", clientSelected);

        return "redirect:/invoices";
    }

    @GetMapping(path = "/invoices/product/")
    public String addProductToInvoice(
            @ModelAttribute(value = "adition") FacturaProductoCantidadDto prodCant, Model model, HttpSession session) {
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";

        if (prodCant == null) {
            removeInvoiceAttrs(session);
            return confirmationMessage(false, model, "/invoice");
        }

        ProductoDto productoDto = productosService.findById(prodCant.getProductoId());
        prodCant.setProductoDto(productoDto);

        List<FacturaProductoCantidadDto> productsSelected = (List<FacturaProductoCantidadDto>) session.getAttribute("productsSelected");

        if (productsSelected.stream().anyMatch(x -> x.getProductoDto().equals(productoDto))) {
            FacturaProductoCantidadDto f = productsSelected.stream().filter(x -> x.getProductoDto().equals(productoDto)).toList().get(0);
            productsSelected.remove(f);
            prodCant.setCantidad(prodCant.getCantidad() + f.getCantidad());
            prodCant.setCosto(prodCant.getCantidad() * prodCant.getProductoDto().getCosto());
        } else prodCant.setCosto(prodCant.getCantidad() * prodCant.getProductoDto().getCosto());

        productsSelected.add(prodCant);
        session.setAttribute("productsSelected", productsSelected);

        return "redirect:/invoices";
    }

    @PostMapping(path = "/invoices/product/")
    public String postProducts(Model model, HttpSession session) {
        Boolean access = (Boolean) session.getAttribute("access");
        if (access == null || !access) return "redirect:/";

        session.setAttribute("hasProducts", true);

        FacturaDto facturaDto = (FacturaDto) session.getAttribute("invoice");
        facturaDto = facturaService.joinFacturaComponents(
                (PersonaDto) session.getAttribute("clientSelected"),
                (List<FacturaProductoCantidadDto>) session.getAttribute("productsSelected"));

        session.setAttribute("invoice", facturaDto);

        return "redirect:/invoices";
    }


    @PostMapping("/invoices/declare/")
    public String declareInvoice(
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, Model model, HttpSession session){

        Boolean access = (Boolean) session.getAttribute("access");
        if(access == null || !access) return "redirect:/";

        FacturaDto facturaDto = (FacturaDto) session.getAttribute("invoice");
        facturaDto.setDate(date);


        return "redirect:/invoices";
    }
}
