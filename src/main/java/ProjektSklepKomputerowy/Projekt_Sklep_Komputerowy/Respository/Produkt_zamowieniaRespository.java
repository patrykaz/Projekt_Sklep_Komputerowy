package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository;

import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.ProduktZamowienia;
import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Rola;
import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Produkt_zamowieniaRespository extends JpaRepository<ProduktZamowienia, Integer> {

    void deleteById(Integer id);
    List<ProduktZamowienia> findByZamowienie(Zamowienie zamowienie);
    ProduktZamowienia findByIdProduktuZamowienia(Integer idProduktuZamowienia);

    List<ProduktZamowienia> findByZamowienieIn(List<Zamowienie> zamowienia);


}
