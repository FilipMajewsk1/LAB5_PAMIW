package mwo.lab.controllers;

import jakarta.validation.Valid;
import mwo.lab.models.Booking;
import mwo.lab.models.Client;
import mwo.lab.models.Table;
import mwo.lab.repositories.BookingRepository;
import mwo.lab.repositories.ClientRepository;
import mwo.lab.repositories.TableRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    private BookingRepository bookingRepository;
    private TableRepository tableRepository;
    private ClientRepository clientRepository;
    public BookingController(BookingRepository bookingRepository,
                             TableRepository tableRepository,
                             ClientRepository clientRepository) {
        this.bookingRepository = bookingRepository;
        this.tableRepository = tableRepository;
        this.clientRepository = clientRepository;
    }
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute(new Booking());
        model.addAttribute("bookings", bookingRepository.findAll());
        return "bookings";
    }
    @PostMapping
    public String create(@Valid Booking booking, Errors errors,
                         Model model) {
        String view;
        if (errors.hasErrors()) {
            model.addAttribute("bookings",
                    bookingRepository.findAll());
            view = "bookings";
        } else {
            int numGuests = booking.getGuestNumber();
            List<Table> availableTables =
                    tableRepository.getFreeTable(numGuests);
            String guestName = booking.getClientName();
            List<Client> client = clientRepository.getClient(guestName);
            if(availableTables.size() == 0) {
                model.addAttribute("noTableAvailable", true);
                model.addAttribute("bookings",
                        bookingRepository.findAll());
                view = "bookings";
            } else if (client.size() == 0) {
                model.addAttribute("noClientsFound", true);
                model.addAttribute("bookings",
                        bookingRepository.findAll());
                view = "bookings";

            } else {
                booking.setTable(availableTables.get(0));
                booking.setClient(client.get(0));
                bookingRepository.save(booking);
                view = "redirect:/bookings";
            }
        }
        return view;
    }
    @PostMapping("/tables")
    public String tables(){
        return "tables";
    }
    @PostMapping("/clients")
    public String clients(){
        return "clients";
    }

}

