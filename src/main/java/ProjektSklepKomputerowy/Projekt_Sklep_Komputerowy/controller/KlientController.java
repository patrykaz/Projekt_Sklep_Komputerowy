package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.controller;

import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.HelperClass.DodajProduktDoKoszyka;
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
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Controller
public class KlientController {

    @Autowired
    RolaRespository rolaRespository;

    @Autowired
    ZamowienieRespository zamowienieRespository;

    @Autowired
    Status_zamowieniaRespository status_zamowieniaRespository;

    @Autowired
    Produkt_zamowieniaRespository produkt_zamowieniaRespository;

    @Autowired
    ProduktRespository produktRespository;

    @Autowired
    KlientRespository klientRespository;

    @Autowired
    AdresRespository adresRespository;

    @Autowired
    PracownikRespository pracownikRespository;

    DodajProduktDoKoszyka dodajProduktDoKoszyka = new DodajProduktDoKoszyka();


    // Funkcja zwracajaca role użytkownika
    Rola RolaUser(){
        Rola rolaUser = rolaRespository.getRolaByNazwa("ROLE_USER");
        return rolaUser;
    }

    // Funkcja zwracajaca id zalogowanego klienta
    public Klient ZalogowanyKlient(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        Klient zalogowany_klient =klientRespository.getUzytkownikByLogin(username);
        return zalogowany_klient;
    }


    @RequestMapping("/dodaj_nowego_klienta")
    public String dodaj_klienta(Model model)
    {
        Klient klient = new Klient();
        model.addAttribute("nowy_klient", klient);
        return "dodaj_nowego_klienta";
    }

    @RequestMapping(value = "/dodaj_nowego_klienta", method = RequestMethod.POST)
    public String dodaj_klienta(@Valid @ModelAttribute("nowy_klient") Klient nowy_klient, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors() ) {
            model.addAttribute("nowy_klient", nowy_klient);
            return "dodaj_nowego_klienta";
        }
        else{
            Klient klient = klientRespository.getUzytkownikByLogin(nowy_klient.getLogin());
            Pracownik pracownik = pracownikRespository.getUzytkownikByLogin(nowy_klient.getLogin());
            if (klient != null || pracownik != null) {
                model.addAttribute("LoginInfo", "istnieje");
                model.addAttribute("nowy_klient", nowy_klient);
                return "dodaj_nowego_klienta";
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String zaszyfrowane_haslo = encoder.encode(nowy_klient.getHaslo());
            nowy_klient.setHaslo(zaszyfrowane_haslo);
            nowy_klient.setRola(RolaUser());
            klientRespository.save(nowy_klient);
            model.addAttribute("icon", "success");
            model.addAttribute("header", "Gratulacje");
            model.addAttribute("message","Klient: " +nowy_klient.getImie() + " " +nowy_klient.getNazwisko() + " został dodany pomyślnie");
            return "wynik_operacji_wiadomosc";
        }
    }

    @RequestMapping("/rejestracja")
    public String rejestracja(Model model)
    {
        Klient klient = new Klient();
        model.addAttribute("nowy_klient", klient);
        return "rejestracja";
    }

    @RequestMapping(value = "/rejestracja", method = RequestMethod.POST)
    public String rejestracja(@Valid @ModelAttribute("nowy_klient") Klient nowy_klient, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors() ) {
            model.addAttribute("nowy_klient", nowy_klient);
            return "rejestracja";
        }
        else{
            Klient klient = klientRespository.getUzytkownikByLogin(nowy_klient.getLogin());
            Pracownik pracownik = pracownikRespository.getUzytkownikByLogin(nowy_klient.getLogin());
            if (klient != null || pracownik != null) {
                model.addAttribute("LoginInfo", "istnieje");
                model.addAttribute("nowy_klient", nowy_klient);
                return "rejestracja";
            }

            try{
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String zaszyfrowane_haslo = encoder.encode(nowy_klient.getHaslo());
                nowy_klient.setHaslo(zaszyfrowane_haslo);
                nowy_klient.setRola(RolaUser());
                adresRespository.save(nowy_klient.getAdres());
                klientRespository.save(nowy_klient);
                model.addAttribute("icon", "success");
                model.addAttribute("header", "Gratulacje!");
                model.addAttribute("message","Rejestracja powiodła się!");
                return "wynik_operacji_wiadomosc";
            }
            catch (Exception e){
                model.addAttribute("icon", "error");
                model.addAttribute("header", "UWAGA !!!");
                model.addAttribute("message","Rejestracja nie powiodła się!");
                return "wynik_operacji_wiadomosc";
            }
        }
    }


    @RequestMapping(value = "/usuwanie_edycja_klientow", method = RequestMethod.GET)
    public String usun_edytuj_klienta(Model model)
    {
        List<Klient> ListaKlientow =  klientRespository.findAll();
        model.addAttribute("header","Lista wszystkich klientów");
        model.addAttribute("ListaKlientow", ListaKlientow);
        return "usuwanie_edycja_klientow";
    }

    @RequestMapping(value = "/usun_Klienta", method = RequestMethod.GET)
    public String usun_klienta(@RequestParam(name = "ID_klienta")String id, Model model) {

        try{
            Integer IntegerID = Integer.parseInt(id);
            klientRespository.deleteById(IntegerID);
            List<Klient> ListaKlientow =  klientRespository.findAll();

            model.addAttribute("header","Lista wszystkich klientów");
            model.addAttribute("ListaKlientow", ListaKlientow);

            return "usuwanie_edycja_klientow";
        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message", "Próba usuniecia klienta, nie powiodła się. ");
            return "wynik_operacji_wiadomosc";
        }
    }

    @RequestMapping(value = "/edytuj_Klienta", method = RequestMethod.GET)
    public String edytuj_klienta (@RequestParam(name = "ID_klienta")String id, Model model)
    {
        try{
            Integer IntegerID = Integer.parseInt(id);
            Klient klient = klientRespository.findById(IntegerID).orElseThrow(() -> new EntityNotFoundException(id));
            model.addAttribute("edycja_klienta", klient);
            return "edytuj_klienta_form";

        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message","Błąd edycji klienta!");
            return "wynik_operacji_wiadomosc";
        }

    }

    @RequestMapping(value = "/edytuj_Klienta", method = RequestMethod.POST)
    public String edytuj_klienta (@Valid @ModelAttribute("edycja_klienta") Klient edycja_klienta, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors() ) {
            model.addAttribute("edytowany_klient", edycja_klienta);
            return "edytuj_klienta_form";
        }
        else {
            klientRespository.save(edycja_klienta);
            model.addAttribute("icon", "success");
            model.addAttribute("header", "Gratulacje!");
            model.addAttribute("message","Dane klienta: " +edycja_klienta.getImie() + " " + edycja_klienta.getNazwisko() + " zostały zaktualizowane pomyślnie");
            return "wynik_operacji_wiadomosc";
        }
    }

    // Koszyk Klienta

    @RequestMapping(value = "/koszyk_klienta", method = RequestMethod.GET)
    public String koszyk_klienta(Model model)
    {
        List<Zamowienie> zamowienia= zamowienieRespository.findByStatusZamowieniaNazwaStatusuZamowieniaAndKlientIdKlienta("Otwarte", ZalogowanyKlient().getIdKlienta());
        if (zamowienia.size() == 0)
        {
            Zamowienie nowe_zamowienie = new Zamowienie();
            nowe_zamowienie.setKlient(ZalogowanyKlient());
            StatusZamowienia statusZamowienia = status_zamowieniaRespository.findByNazwaStatusuZamowienia("Otwarte");
            nowe_zamowienie.setStatusZamowienia(statusZamowienia);
            zamowienieRespository.save(nowe_zamowienie);
            List<ProduktZamowienia> produktyZamowienia = produkt_zamowieniaRespository.findByZamowienie(nowe_zamowienie);
            if(produktyZamowienia.size() == 0){
                model.addAttribute("icon", "info");
                model.addAttribute("header", "Twój koszyk");
                model.addAttribute("message", "Twój koszyk jest pusty.");
                return "wynik_operacji_wiadomosc";
            }
            model.addAttribute("header","Twój koszyk z zakupami");
            model.addAttribute("Koszyk", produktyZamowienia);
            return "koszyk_klienta";
        }
        Zamowienie zamowienie = zamowienia.get(0);
        List<ProduktZamowienia> produktyZamowienia = produkt_zamowieniaRespository.findByZamowienie(zamowienie);
        if(produktyZamowienia.size() == 0) {
            model.addAttribute("icon", "info");
            model.addAttribute("header", "Twój koszyk");
            model.addAttribute("message", "Twój koszyk jest pusty.");
            return "wynik_operacji_wiadomosc";
        }

        double sumaKoszyka = 0;
        for(ProduktZamowienia produktZam: produktyZamowienia){
            sumaKoszyka+= produktZam.getWybranaLiczbaSztuk() * produktZam.getProdukt().getCena();
        }
        DecimalFormat df = new DecimalFormat("###.##");
        String sumaKoszykaString = df.format(sumaKoszyka);

        model.addAttribute("SumaKoszyka", sumaKoszykaString);
        model.addAttribute("header","Twój koszyk z zakupami");
        model.addAttribute("Koszyk", produktyZamowienia);
        return "koszyk_klienta";
    }

    @RequestMapping(value = "/zloz_zamowienie", method = RequestMethod.GET)
    public String zloz_zamowienie(Model model) {
        List<Zamowienie> zamowienia = zamowienieRespository.findByStatusZamowieniaNazwaStatusuZamowieniaAndKlientIdKlienta("Otwarte", ZalogowanyKlient().getIdKlienta());
        Zamowienie zamowienie = zamowienia.get(0);
        StatusZamowienia statusZamowienia = status_zamowieniaRespository.findByNazwaStatusuZamowienia("Zapłacone");
        zamowienie.setStatusZamowienia(statusZamowienia);
        zamowienie.setDataRealizacjiZamowienia(LocalDateTime.now().withNano(0));
        zamowienieRespository.save(zamowienie);
        model.addAttribute("icon", "success");
        model.addAttribute("header", "Gratulacje!");
        model.addAttribute("message","Twoje zamówienie zostało przekazane do realizacji.");
        return "wynik_operacji_wiadomosc";
    }

    @RequestMapping(value = "/usun_produkt_z_koszyka", method = RequestMethod.GET)
    public String usun_produkt_z_koszyka(@RequestParam(name = "ID_produktu_zamowienia")String id_produktu_zamowienia, Model model)
    {
        try{
            Integer IntegerID = Integer.parseInt(id_produktu_zamowienia);
            ProduktZamowienia produktZamowienia = produkt_zamowieniaRespository.findByIdProduktuZamowienia(IntegerID);
            if(produktZamowienia.getWybranaLiczbaSztuk() > 1){
                produktZamowienia.setWybranaLiczbaSztuk(produktZamowienia.getWybranaLiczbaSztuk()-1);
                produkt_zamowieniaRespository.save(produktZamowienia);
            }
            else{
                produkt_zamowieniaRespository.deleteById(IntegerID);
            }
            Produkt produkt = produktRespository.findById(produktZamowienia.getProdukt().getIdProduktu()).orElseThrow();
            produkt.setLiczbaSztuk(produkt.getLiczbaSztuk() + 1);
            produktRespository.save(produkt);

            List<ProduktZamowienia> produktyZamowienia = produkt_zamowieniaRespository.findByZamowienie(produktZamowienia.getZamowienie());
            if(produktyZamowienia.size() == 0) {
                model.addAttribute("icon", "info");
                model.addAttribute("header", "Twój koszyk");
                model.addAttribute("message", "Twój koszyk jest pusty.");
                return "wynik_operacji_wiadomosc";
            }

            double sumaKoszyka = 0;
            for(ProduktZamowienia produktZam: produktyZamowienia){
                sumaKoszyka+= produktZam.getWybranaLiczbaSztuk() * produktZam.getProdukt().getCena();
            }

            model.addAttribute("SumaKoszyka", sumaKoszyka);
            model.addAttribute("header","Twój koszyk z zakupami");
            model.addAttribute("Koszyk", produktyZamowienia);
            return "koszyk_klienta";

        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message", "Próba usuniecia produktu z koszyka, nie powiodła się. ");
            return "wynik_operacji_wiadomosc";
        }
    }


    @RequestMapping(value = "/szczegoly_produktu", method = RequestMethod.GET)
    public String szczegoly_produktu (@RequestParam(name = "ID_produktu")String id, Model model)
    {
        try{
            Integer IntegerID = Integer.parseInt(id);
            Produkt produkt = produktRespository.findById(IntegerID).orElseThrow(() -> new EntityNotFoundException(id));
            dodajProduktDoKoszyka.setProdukt(produkt);
            model.addAttribute("dodaj_produkt_do_koszyka", dodajProduktDoKoszyka);
            model.addAttribute("szczegoly_produktu", produkt);
            return "szczegoly_produktu";

        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message","Wprowadzono błędne dane ");
            return "wynik_operacji_wiadomosc";
        }
    }

    @RequestMapping(value = "/szczegoly_produktu", method = RequestMethod.POST)
    public String szczegoly_produktu(@ModelAttribute("dodaj_produkt_do_koszyka") DodajProduktDoKoszyka dodajProduktDoKoszyka, Model model)
    {
        if(dodajProduktDoKoszyka.getLiczba_wybranych_sztuk() > dodajProduktDoKoszyka.getProdukt().getLiczbaSztuk()){
            model.addAttribute("SztukiInfo", "SztukiInfo");
            model.addAttribute("dodaj_produkt_do_koszyka", dodajProduktDoKoszyka);
            model.addAttribute("szczegoly_produktu", dodajProduktDoKoszyka.getProdukt());
            return "szczegoly_produktu";
        }
        try{
            List<Zamowienie> zamowienia= zamowienieRespository.findByStatusZamowieniaNazwaStatusuZamowieniaAndKlientIdKlienta("Otwarte", ZalogowanyKlient().getIdKlienta());
            if (zamowienia.size() == 0)
            {
                Zamowienie nowe_zamowienie = new Zamowienie();
                nowe_zamowienie.setKlient(ZalogowanyKlient());
                StatusZamowienia statusZamowienia = status_zamowieniaRespository.findByNazwaStatusuZamowienia("Otwarte");
                nowe_zamowienie.setStatusZamowienia(statusZamowienia);
                zamowienieRespository.save(nowe_zamowienie);
                zamowienia.add(nowe_zamowienie);
            }
            Produkt produkt = produktRespository.findById(dodajProduktDoKoszyka.getProdukt().getIdProduktu()).orElseThrow();
            produkt.setLiczbaSztuk(dodajProduktDoKoszyka.getProdukt().getLiczbaSztuk()-dodajProduktDoKoszyka.getLiczba_wybranych_sztuk());
            produktRespository.save(produkt);
            Zamowienie zamowienie = zamowienia.get(0);
            ProduktZamowienia produktZamowienia = new ProduktZamowienia(zamowienie, dodajProduktDoKoszyka.getProdukt(), dodajProduktDoKoszyka.getLiczba_wybranych_sztuk());
            produkt_zamowieniaRespository.save(produktZamowienia);
            List<ProduktZamowienia> produktyZamowienia = produkt_zamowieniaRespository.findByZamowienie(zamowienie);

            double sumaKoszyka = 0;
            for(ProduktZamowienia produktZam: produktyZamowienia){
                sumaKoszyka+= produktZam.getWybranaLiczbaSztuk() * produktZam.getProdukt().getCena();
            }

            model.addAttribute("SumaKoszyka", sumaKoszyka);
            model.addAttribute("header","Twój koszyk z zakupami");
            model.addAttribute("Koszyk", produktyZamowienia);
            return "koszyk_klienta";

        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message", "Próba dodania produktu do koszyka, nie powiodła się. ");
            return "wynik_operacji_wiadomosc";
        }
    }

    @RequestMapping(value = "/lista_zrealizowanych_zamowien_klienta", method = RequestMethod.GET)
    public String lista_zrealizowanych_zamowien_klienta(Model model)
    {
        List<String> statusyZamowienia = Arrays.asList("Zapłacone", "Wysłane");
        List<Zamowienie> zamowienia= zamowienieRespository.findByStatusZamowieniaNazwaStatusuZamowieniaInAndKlientIdKlienta(statusyZamowienia, ZalogowanyKlient().getIdKlienta());
        if (zamowienia.size() == 0) {
            model.addAttribute("icon", "info");
            model.addAttribute("header", "Twoja historia zamówień");
            model.addAttribute("message", "Nie posiadasz jeszcze żadnych zrealziowanych zamówień.");
            return "wynik_operacji_wiadomosc";
        }

        model.addAttribute("header","Twoja historia zamówień");
        model.addAttribute("Zamowienia", zamowienia);
        return "lista_zrealizowanych_zamowien_klienta";
    }

    @RequestMapping(value = "/szczegoly_zamowienia", method = RequestMethod.GET)
    public String szczegoly_Zamowienia(@RequestParam(name = "ID_zamowienia")String id, Model model)
    {
        try{
            Integer IntegerID = Integer.parseInt(id);
            Zamowienie zamowienie = zamowienieRespository.findById(IntegerID).orElseThrow(() -> new EntityNotFoundException(id));
            List<ProduktZamowienia> produktyZamowienia = produkt_zamowieniaRespository.findByZamowienie(zamowienie);
            double sumaZamowienia = 0;
            for(ProduktZamowienia produktZam: produktyZamowienia){
                sumaZamowienia+= produktZam.getWybranaLiczbaSztuk() * produktZam.getProdukt().getCena();
            }
            DecimalFormat df = new DecimalFormat("###.##");
            String sumaZamowieniaString = df.format(sumaZamowienia);

            model.addAttribute("SumaZamowienia", sumaZamowieniaString);
            model.addAttribute("header","Produkty zrealizowanego zamówienia");
            model.addAttribute("ProduktyZamowienia", produktyZamowienia);
            return "szczegoly_zamowienia";

        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message","Wystąpił problem z wczytaniem zamówienia ");
            return "wynik_operacji_wiadomosc";
        }
    }

}
