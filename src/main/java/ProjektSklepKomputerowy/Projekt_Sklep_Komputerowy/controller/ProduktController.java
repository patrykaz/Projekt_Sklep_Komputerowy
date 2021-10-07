package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.controller;

import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.HelperClass.WybierzKategorie;
import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository.Kategoria_produktuRespository;
import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository.ProduktRespository;
import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.KategoriaProduktu;
import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Produkt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ProduktController {

    @Autowired
    ProduktRespository produktRespository;

    @Autowired
    Kategoria_produktuRespository kategoria_produktuRespository;


    @RequestMapping(value = "/dodaj_nowy_produkt", method = RequestMethod.GET)
    public String dodaj_nowy_produkt (Model model){
        Produkt nowy_produkt = new Produkt();
        model.addAttribute("nowy_produkt", nowy_produkt);
        List<KategoriaProduktu> Lista_kategorii_produktow = kategoria_produktuRespository.findAll();
        model.addAttribute("Lista_kategorii_produktow", Lista_kategorii_produktow);

        return "dodaj_nowy_produkt";
    }

    @RequestMapping(value = "/dodaj_nowy_produkt", method = RequestMethod.POST)
    public String dodaj_nowy_produkt(@Valid @ModelAttribute("nowy_produkt") Produkt nowy_produkt, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getObjectName() + " " + objectError.getDefaultMessage()));
            List<KategoriaProduktu> Lista_kategorii_produktow = kategoria_produktuRespository.findAll();
            model.addAttribute("Lista_kategorii_produktow", Lista_kategorii_produktow);
            model.addAttribute("nowy_produkt", nowy_produkt);
            return "dodaj_nowy_produkt";
        }
        else{
            produktRespository.save(nowy_produkt);
            model.addAttribute("icon", "success");
            model.addAttribute("header", "Gratulacje!");
            model.addAttribute("message","Produkt: " +nowy_produkt.getNazwa() + " został dodany pomyślnie");
            return "wynik_operacji_wiadomosc";
        }

    }

    @RequestMapping(value = "/lista_produktow", method = RequestMethod.GET)
    public String usun_edytuj_produkt(Model model)
    {
        List<Produkt> ListaProduktow =  produktRespository.findAll();
        List<KategoriaProduktu> Lista_kategorii_produktow = kategoria_produktuRespository.findAll();
        WybierzKategorie wybranaKategoria = new WybierzKategorie();
        model.addAttribute("WybranaKategoria", wybranaKategoria);
        model.addAttribute("header","Lista Produktów");
        model.addAttribute("ListaProduktow", ListaProduktow);
        model.addAttribute("Lista_kategorii_produktow", Lista_kategorii_produktow);

        return "lista_produktow";
    }

    @RequestMapping(value = "/lista_produktow_po_wybranej_kategorii", method = RequestMethod.GET)
    public String lista_produktow_po_wybranej_kategorii(@ModelAttribute("WybranaKategoria") WybierzKategorie wybranaKategoria, Model model)
    {
        try{
            KategoriaProduktu kategoriaProduktu = kategoria_produktuRespository.findById(wybranaKategoria.getKategoriaProduktu().getIdKategorii()).orElseThrow();
            List<Produkt> Lista_produktow_po_wybranej_kategorii = produktRespository.findByKategoriaProduktuNazwaKategorii(kategoriaProduktu.getNazwaKategorii());
            List<KategoriaProduktu> Lista_kategorii_produktow = kategoria_produktuRespository.findAll();
            model.addAttribute("WybranaKategoria", wybranaKategoria);
            model.addAttribute("header","Lista Produktów");
            model.addAttribute("ListaProduktow", Lista_produktow_po_wybranej_kategorii);
            model.addAttribute("Lista_kategorii_produktow", Lista_kategorii_produktow);
            return "lista_produktow";

        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message","Filtracja nie powiodła się!");
            return "wynik_operacji_wiadomosc";
        }
    }

    @RequestMapping(value = "/usun_Produkt", method = RequestMethod.GET)
    public String usun_produkt(@RequestParam(name = "ID_produktu")String id, Model model) {

        try{
            Integer IntegerID = Integer.parseInt(id);
            produktRespository.deleteById(IntegerID);

            List<Produkt> ListaProduktow =  produktRespository.findAll();
            List<KategoriaProduktu> Lista_kategorii_produktow = kategoria_produktuRespository.findAll();

            model.addAttribute("Lista_kategorii_produktow", Lista_kategorii_produktow);
            model.addAttribute("header","Lista wszystkich produktów");
            model.addAttribute("ListaProduktow", ListaProduktow);

            return "lista_produktow";
        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message", "Próba usuniecia rekordu, nie powiodła się. ");
            return "wynik_operacji_wiadomosc";
        }

    }

    @RequestMapping(value = "/edytuj_Produkt", method = RequestMethod.GET)
    public String edytuj_produkt (@RequestParam(name = "ID_produktu")String id, Model model)
    {
        try{
            Integer IntegerID = Integer.parseInt(id);
            Optional<Produkt> produkt = produktRespository.findById(IntegerID);
            model.addAttribute("edytuj_produkt", produkt);
            List<KategoriaProduktu> Lista_kategorii_produktow = kategoria_produktuRespository.findAll();
            model.addAttribute("Lista_kategorii_produktow", Lista_kategorii_produktow);
            return "edytuj_produkt_form";

        }catch (Exception e){
            model.addAttribute("icon", "error");
            model.addAttribute("header", "UWAGA !!!");
            model.addAttribute("message","Próba edycji produktu nie powiodła się!");
            return "wynik_operacji_wiadomosc";
        }

    }

    @RequestMapping(value = "/edytuj_Produkt", method = RequestMethod.POST)
    public String edytuj_produkt (@Valid @ModelAttribute("edytuj_produkt") Produkt edytuj_produkt, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors() ) {
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getObjectName() + " " + objectError.getDefaultMessage()));
            model.addAttribute("edytuj_produkt", edytuj_produkt);
            List<KategoriaProduktu> Lista_kategorii_produktow = kategoria_produktuRespository.findAll();
            model.addAttribute("Lista_kategorii_produktow", Lista_kategorii_produktow);
            return "edytuj_produkt_form";
        }
        else {
            produktRespository.save(edytuj_produkt);
            model.addAttribute("icon", "success");
            model.addAttribute("header", "Gratulacje!");
            model.addAttribute("message","Produkt: " +edytuj_produkt.getNazwa() + " został zaktualizowany pomyślnie");
            return "wynik_operacji_wiadomosc";
        }
    }
}
