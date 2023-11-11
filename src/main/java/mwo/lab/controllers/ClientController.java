package mwo.lab.controllers;


import jakarta.validation.Valid;
import mwo.lab.models.Client;
import mwo.lab.repositories.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private ClientRepository repository;

    public ClientController(ClientRepository repository){
        this.repository = repository;
    }

    @GetMapping()
    public String getAll(Model model){
        model.addAttribute(new Client());
        model.addAttribute("clients", repository.findAll());
        return "clients";
    }

    @PostMapping()
    public String create(@Valid Client client, Errors errors,Model model){
        if (errors.hasErrors()) {
            model.addAttribute("clients", repository.findAll());
            return "clients";
        } else {
            repository.save(client);
            return "redirect:/clients";
        }

    }
    @PostMapping("/tables")
    public String tables(){
        return "tables";
    }
    @PostMapping("/bookings")
    public String bookings(){
        return "bookings";
    }


}
