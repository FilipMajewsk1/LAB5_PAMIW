package mwo.lab.controllers;

import jakarta.validation.Valid;
import mwo.lab.models.Client;
import mwo.lab.models.Table;
import mwo.lab.repositories.ClientRepository;
import mwo.lab.repositories.TableRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tables")
public class TableController {
    private TableRepository repository;

    public TableController(TableRepository repository){
        this.repository = repository;
    }

    @GetMapping()
    public String getAll(Model model){
        model.addAttribute(new Table());
        model.addAttribute("tables", repository.findAll());
        return "tables";
    }

    @PostMapping()
    public String create(@Valid Table table, Errors errors,Model model){
        if (errors.hasErrors()) {
            model.addAttribute("tables", repository.findAll());
            return "tables";
        } else {
            repository.save(table);
            return "redirect:/tables";
        }

    }
    @PostMapping("/bookings")
    public String bookings(){
        return "bookings";
    }
    @PostMapping("/clients")
    public String clients(){
        return "clients";
    }

}
