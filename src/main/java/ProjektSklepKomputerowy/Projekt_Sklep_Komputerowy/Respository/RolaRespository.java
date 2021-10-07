package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository;

import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Rola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RolaRespository extends JpaRepository<Rola, Integer> {

    @Query("SELECT r FROM Rola r WHERE r.nazwaRoli = ?1")
    Rola getRolaByNazwa(String rola);
}
