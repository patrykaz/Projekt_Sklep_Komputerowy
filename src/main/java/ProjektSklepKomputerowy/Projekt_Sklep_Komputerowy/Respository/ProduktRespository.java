package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository;

import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProduktRespository extends JpaRepository<Produkt, Integer> {
    void deleteById(Integer id);
    Optional<Produkt> findById(Integer id);

    List<Produkt> findByKategoriaProduktuNazwaKategorii(String nazwaKategorii);

}
