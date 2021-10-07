package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String menu()
    {
        return "index";
    }

    @RequestMapping("/403")
    public String error403(Model model){
        model.addAttribute("icon", "warning");
        model.addAttribute("header", "Uwaga!!");
        model.addAttribute("message","Nie posiadasz odpowednich uprawnień!!");
        return "wynik_operacji_wiadomosc";
    }

}
