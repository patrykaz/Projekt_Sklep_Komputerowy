package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository;

import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.KategoriaProduktu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Kategoria_produktuRespository extends JpaRepository<KategoriaProduktu, Integer> {

    Optional<KategoriaProduktu> findById (Integer id);

}
