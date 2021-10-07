package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.controller;


import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository.Kategoria_produktuRespository;
import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.KategoriaProduktu;
import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Klient;
import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Produkt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;




@Controller
public class KategoriaProduktuController {

    @Autowired
    Kategoria_produktuRespository kategoria_produktuRespository;

    @RequestMapping(value = "/dodaj_nowa_kategorie", method = RequestMethod.GET)
    public String dodaj_nowa_kategorie (Model model){
        KategoriaProduktu nowa_kategoria = new KategoriaProduktu();
        model.addAttribute("nowa_kategoria", nowa_kategoria);
        return "dodaj_nowa_kategorie";
    }

    @RequestMapping(value = "/dodaj_nowa_kategorie", method = RequestMethod.POST)
    public String dodaj_nowa_kategorie(@Valid @ModelAttribute("nowa_kategoria") KategoriaProduktu nowa_kategoria, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("nowa_kategoria", nowa_kategoria);
            return "dodaj_nowa_kategorie";
        }
        else{
            kategoria_produktuRespository.save(nowa_kategoria);
            model.addAttribute("icon", "success");
            model.addAttribute("header", "Gratulacje!");
            model.addAttribute("message","Kategoria: " +nowa_kategoria.getNazwaKategorii() + " została dodana pomyślnie");
            return "wynik_operacji_wiadomosc";
        }
    }

    @RequestMapping(value = "/usuwanie_edycja_kategorii_produktow", method = RequestMethod.GET)
    public String usuwanie_edycja_kategorii_produktow(Model model)
    {
        List<KategoriaProduktu> ListaKategorii = kategoria_produktuRespository.findAll();
        model.addAttribute("header","Lista wszystkich kategorii produktów");
        model.addAttribute("ListaKategorii", ListaKategorii);
        return "usuwanie_edycja_kategorii_produktow";
    }

    @RequestMapping(value = "/usun_Kategorie", method = RequestMethod.GET)
    public String usun_Kategorie(@RequestParam(name = "ID_kategorii")String id, Model model) {

        try{
            Integer IntegerID = Integer.parseInt(id);
            kategoria_produktuRespository.deleteById(IntegerID);
            List<KategoriaProduktu> ListaKategorii =  kategoria_produktuRespository.findAll();
            model.addAttribute("header","Lista wszystkich kategorii produktów");
            model.addAttribute("ListaKategorii", ListaKategorii);

            return "usuwanie_edycja_kategorii_produktow";
        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message", "Próba usuniecia kategorii produktu, nie powiodła się. ");
            return "wynik_operacji_wiadomosc";
        }
    }

    @RequestMapping(value = "/edytuj_Kategorie", method = RequestMethod.GET)
    public String edytuj_Kategorie (@RequestParam(name = "ID_kategorii")String id, Model model)
    {
        try{
            Integer IntegerID = Integer.parseInt(id);
            KategoriaProduktu edycja_kategorii = kategoria_produktuRespository.findById(IntegerID).orElseThrow(() -> new EntityNotFoundException(id));
            model.addAttribute("edycja_kategorii", edycja_kategorii);
            return "edytuj_kategorie_form";

        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message","Błąd edycji kategorii!");
            return "wynik_operacji_wiadomosc";
        }

    }

    @RequestMapping(value = "/edytuj_Kategorie", method = RequestMethod.POST)
    public String edytuj_klienta (@Valid @ModelAttribute("edycja_klienta") KategoriaProduktu edycja_kategorii, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("edycja_kategorii", edycja_kategorii);
            return "dodaj_nowa_kategorie";
        }
        else{
            kategoria_produktuRespository.save(edycja_kategorii);
            model.addAttribute("icon", "success");
            model.addAttribute("header", "Gratulacje!");
            model.addAttribute("message","Kategoria: " +edycja_kategorii.getNazwaKategorii() + " została zaktualizowana pomyślnie");
            return "wynik_operacji_wiadomosc";
        }
    }
}
