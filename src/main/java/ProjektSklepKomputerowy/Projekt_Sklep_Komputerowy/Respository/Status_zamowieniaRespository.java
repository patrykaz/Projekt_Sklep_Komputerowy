package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository;


import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Rola;
import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.StatusZamowienia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Status_zamowieniaRespository extends JpaRepository<StatusZamowienia, Integer> {
//
//    @Query("SELECT s FROM StatusZamowienia s WHERE s.nazwaStatusuZamowienia = ?1")
//    StatusZamowienia getStatusZamowieniaByNazwaStatusuZamowienia(String nazwaStatusuZamowienia);

    StatusZamowienia findByNazwaStatusuZamowienia(String nazwaStatusu);
}
