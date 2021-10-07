package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository;

import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface ZamowienieRespository extends JpaRepository<Zamowienie, Integer> {

//    @Query("SELECT * FROM Zamowienie z WHERE z.klient.getId_klienta() = :id")
//    public Zamowienie getZamowienieByIdKlienta(@Param("login") Integer id);

    List<Zamowienie> findByStatusZamowieniaNazwaStatusuZamowienia(String status);

    List<Zamowienie> findByStatusZamowieniaNazwaStatusuZamowieniaAndKlientIdKlienta(String status, Integer idKlienta);

    List<Zamowienie> findByStatusZamowieniaNazwaStatusuZamowieniaInAndKlientIdKlienta(List<String> statusy, Integer idKlienta);




    //Zamowienie findByStatusZamowieniaNazwaStatusuZamowieniaAndKlientIdKlienta(String status, Integer idKlienta);
}
