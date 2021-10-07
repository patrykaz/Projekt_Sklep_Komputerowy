package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.controller;

import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository.*;
import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Controller
public class PracownikController {



    @Autowired
    PracownikRespository pracownikRespository;

    @Autowired
    RolaRespository rolaRespository;

    @Autowired
    ZamowienieRespository zamowienieRespository;

    @Autowired
    Produkt_zamowieniaRespository produkt_zamowieniaRespository;

    @Autowired
    Status_zamowieniaRespository status_zamowieniaRespository;

    @Autowired
    KlientRespository klientRespository;

    // Funkcja zwracajaca role pracownika
    Rola RolaPracownik(){
        Rola rolaPracownik = rolaRespository.getRolaByNazwa("ROLE_EMPLOYER");
        return rolaPracownik;
    }

    // Funkcja zwracajaca id zalogowanego pracownika
    public Pracownik ZalogowanyPracownik(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        Pracownik zalogowany_pracownik = pracownikRespository.getUzytkownikByLogin(username);
        return zalogowany_pracownik;
    }

    @RequestMapping("/dodaj_nowego_pracownika")
    public String dodaj_pracownika(Model model)
    {
        Pracownik pracownik = new Pracownik();
        model.addAttribute("nowy_pracownik", pracownik);
        return "dodaj_nowego_pracownika";
    }

    @RequestMapping(value = "/dodaj_nowego_pracownika", method = RequestMethod.POST)
    public String dodaj_pracownika(@Valid @ModelAttribute("nowy_pracownik") Pracownik nowy_pracownik, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors() ) {
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getObjectName() + " " + objectError.getDefaultMessage()));
            model.addAttribute("nowy_pracownik", nowy_pracownik);
            return "dodaj_nowego_pracownika";
        }
        else{
            Klient klient = klientRespository.getUzytkownikByLogin(nowy_pracownik.getLogin());
            Pracownik pracownik = pracownikRespository.getUzytkownikByLogin(nowy_pracownik.getLogin());
            if (klient != null || pracownik != null) {
                model.addAttribute("LoginInfo", "istnieje");
                model.addAttribute("nowy_pracownik", nowy_pracownik);
                return "dodaj_nowego_pracownika";
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String zaszyfrowane_haslo = encoder.encode(nowy_pracownik.getHaslo());
            nowy_pracownik.setHaslo(zaszyfrowane_haslo);
            nowy_pracownik.setRola(RolaPracownik());
            pracownikRespository.save(nowy_pracownik);
            model.addAttribute("icon", "success");
            model.addAttribute("header", "Gratulacje!");
            model.addAttribute("message","Pracownik: " +nowy_pracownik.getImie() + " " +nowy_pracownik.getNazwisko() + " został dodany pomyślnie");
            return "wynik_operacji_wiadomosc";
        }
    }


    @RequestMapping(value = "/usuwanie_edycja_pracownikow", method = RequestMethod.GET)
    public String usun_edytuj_pracownika(Model model)
    {
        List<Pracownik>  ListaPracownikow =  pracownikRespository.findAll();
        model.addAttribute("header","Lista wszystkich pracowników");
        model.addAttribute("ListaPracownikow", ListaPracownikow);
        return "usuwanie_edycja_pracownikow";
    }

    @RequestMapping(value = "/usun_Pracownika", method = RequestMethod.GET)
    public String usun_pracownika(@RequestParam(name = "ID_pracownika")String id, Model model) {

        try{
            Integer IntegerID = Integer.parseInt(id);
            pracownikRespository.deleteById(IntegerID);
            List<Pracownik>  ListaPracownikow =  pracownikRespository.findAll();
            model.addAttribute("header","Lista wszystkich pracowników");
            model.addAttribute("ListaPracownikow", ListaPracownikow);
            return "usuwanie_edycja_pracownikow";

        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message", "Próba usuniecia pracownika, nie powiodła się. ");
            return "wynik_operacji_wiadomosc";
        }
    }

    @RequestMapping(value = "/edytuj_Pracownika", method = RequestMethod.GET)
    public String edytuj_pracownika (@RequestParam(name = "ID_pracownika")String id, Model model)
    {
        try{
            Integer IntegerID = Integer.parseInt(id);
            Pracownik pracownik = pracownikRespository.findById(IntegerID).orElseThrow(() -> new EntityNotFoundException(id));
            model.addAttribute("edycja_pracownika", pracownik);
            return "edytuj_pracownika_form";

        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message", "Próba edycji pracownika nie powiodła się! ");
            return "wynik_operacji_wiadomosc";
        }
    }

    @RequestMapping(value = "/edytuj_Pracownika", method = RequestMethod.POST)
    public String edytuj_pracownika (@Valid @ModelAttribute("edycja_pracownika") Pracownik edycja_pracownika, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors() ) {
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getObjectName() + " " + objectError.getDefaultMessage()));
            model.addAttribute("edycja_pracownika", edycja_pracownika);
            return "edytuj_pracownika_form";
        }
        else {
            pracownikRespository.save(edycja_pracownika);
            model.addAttribute("header", "Wynik");
            model.addAttribute("message","Dane pracownika: " +edycja_pracownika.getImie() + " " + edycja_pracownika.getNazwisko() + " zostały zaktualizowane pomyślnie");
            return "wynik_operacji_wiadomosc";
        }
    }

    // Relizacja zamówień i ich podgląd przez pracownika

    @RequestMapping(value = "/lista_zamowien_do_wysylki", method = RequestMethod.GET)
    public String lista_zamowien_do_wysylki(Model model)
    {
        String statusZamowienia = "Zapłacone";
        List<Zamowienie> zamowienia_do_wysylki= zamowienieRespository.findByStatusZamowieniaNazwaStatusuZamowienia(statusZamowienia);
        if (zamowienia_do_wysylki.size() == 0) {
            model.addAttribute("icon", "info");
            model.addAttribute("header", "Lista zamówień do wysyłki");
            model.addAttribute("message", "W bazie nie widnieją zamówienia do wysyłki");
            return "wynik_operacji_wiadomosc";
        }

        model.addAttribute("header","Lista zamówień do wysyłki");
        model.addAttribute("zamowienia_do_wysylki", zamowienia_do_wysylki);
        return "lista_zamowien_do_wysylki";
    }


    @RequestMapping(value = "/szczegoly_zamowienia_do_wysylki", method = RequestMethod.GET)
    public String szczegoly_zamowienia_do_wysylki(@RequestParam(name = "ID_zamowienia_do_wysylki")String id, Model model)
    {
        try{
            Integer IntegerID = Integer.parseInt(id);
            Zamowienie zamowienie = zamowienieRespository.findById(IntegerID).orElseThrow(() -> new EntityNotFoundException(id));
            List<ProduktZamowienia> produktyZamowienia = produkt_zamowieniaRespository.findByZamowienie(zamowienie);
            double sumaZamowienia = 0;
            for(ProduktZamowienia produktZam: produktyZamowienia){
                sumaZamowienia+= produktZam.getWybranaLiczbaSztuk() * produktZam.getProdukt().getCena();
            }
            model.addAttribute("Zamowienie", zamowienie);
            model.addAttribute("SumaZamowienia", sumaZamowienia);
            model.addAttribute("header","Produkty zamówienia do wysyłki");
            model.addAttribute("ProduktyZamowienia", produktyZamowienia);
            return "szczegoly_zamowienia_do_wysylki";

        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message","Wystąpił problem z wczytaniem zamówienia");
            return "wynik_operacji_wiadomosc";
        }
    }

    @RequestMapping(value = "/wyslij_zamowienie", method = RequestMethod.GET)
    public String wyslij_zamowienie(@RequestParam(name = "ID_zamowienia_do_wysylki")String id, Model model)
    {
        try{
            Integer IntegerID = Integer.parseInt(id);
            Zamowienie zamowienie = zamowienieRespository.findById(IntegerID).orElseThrow(() -> new EntityNotFoundException(id));
            StatusZamowienia statusZamowienia = status_zamowieniaRespository.findByNazwaStatusuZamowienia("Wysłane");
            zamowienie.setStatusZamowienia(statusZamowienia);
            zamowienie.setPracownik(ZalogowanyPracownik());
            zamowienie.setDataWysylkiZamowienia(LocalDateTime.now().withNano(0));
            zamowienieRespository.save(zamowienie);
            model.addAttribute("icon", "success");
            model.addAttribute("header", "Gratulacje!");
            model.addAttribute("message","Zamówienie nr. " + zamowienie.getIdZamowienia() + " zmieniło swój status na: " + zamowienie.getStatusZamowienia().getNazwaStatusuZamowienia() );
            return "wynik_operacji_wiadomosc";

        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message","Wystąpił problem z wysłaniem zamówienia");
            return "wynik_operacji_wiadomosc";
        }
    }

    @RequestMapping(value = "/lista_wszystkich_wyslanych_zamowien", method = RequestMethod.GET)
    public String lista_wszystkich_wyslanych_zamowien(Model model)
    {
        StatusZamowienia statusZamowienia = status_zamowieniaRespository.findByNazwaStatusuZamowienia("Wysłane");
        List<Zamowienie> historia_wyslanych_zamowien= zamowienieRespository.findByStatusZamowieniaNazwaStatusuZamowienia(statusZamowienia.getNazwaStatusuZamowienia());

        if (historia_wyslanych_zamowien.size() == 0) {
            model.addAttribute("icon", "info");
            model.addAttribute("header", "Lista wszystkich wsyłanych zamówień");
            model.addAttribute("message", "W bazie nie widnieją zamówienia, które zostały wsyłane");
            return "wynik_operacji_wiadomosc";
        }

        List<ProduktZamowienia> produktyZamowienia = produkt_zamowieniaRespository.findByZamowienieIn(historia_wyslanych_zamowien);
        double sumaWszystkichZamowien = 0;
        int liczbaSprzedanychProduktow = 0;
        for(ProduktZamowienia produktZam: produktyZamowienia){
            sumaWszystkichZamowien+= produktZam.getWybranaLiczbaSztuk() * produktZam.getProdukt().getCena();
            liczbaSprzedanychProduktow += produktZam.getWybranaLiczbaSztuk();
        }
        DecimalFormat df = new DecimalFormat("###.##");
        String sumaWszystkichZamowienString = df.format(sumaWszystkichZamowien);

        model.addAttribute("LiczbaSprzedanychProduktow", liczbaSprzedanychProduktow);
        model.addAttribute("sumaWszystkichZamowien", sumaWszystkichZamowienString);
        model.addAttribute("header","Lista wszystkich wsyłanych zamówień");
        model.addAttribute("historia_wyslanych_zamowien", historia_wyslanych_zamowien);
        return "lista_wszystkich_wyslanych_zamowien";
    }


    @RequestMapping(value = "/szczegoly_wyslanego_zamowienia", method = RequestMethod.GET)
    public String szczegoly_wyslanego_zamowienia(@RequestParam(name = "ID_zamowienia_do_wysylki")String id, Model model)
    {
        try{
            Integer IntegerID = Integer.parseInt(id);
            Zamowienie zamowienie = zamowienieRespository.findById(IntegerID).orElseThrow(() -> new EntityNotFoundException(id));
            List<ProduktZamowienia> produktyZamowienia = produkt_zamowieniaRespository.findByZamowienie(zamowienie);
            double sumaZamowienia = 0;
            for(ProduktZamowienia produktZam: produktyZamowienia){
                sumaZamowienia+= produktZam.getWybranaLiczbaSztuk() * produktZam.getProdukt().getCena();
            }
            model.addAttribute("Zamowienie", zamowienie);
            model.addAttribute("SumaZamowienia", sumaZamowienia);
            model.addAttribute("header","Produkty wysłanego zamówienia");
            model.addAttribute("ProduktyZamowienia", produktyZamowienia);
            return "szczegoly_wyslanego_zamowienia";

        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message","Wystąpił problem z wczytaniem zamówienia");
            return "wynik_operacji_wiadomosc";
        }
    }

}
