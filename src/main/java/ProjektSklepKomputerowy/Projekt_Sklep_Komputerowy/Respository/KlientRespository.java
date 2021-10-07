package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository;

import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Klient;
import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Rola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface KlientRespository extends JpaRepository <Klient, Integer> {

    void deleteById(Integer id);

    Optional<Klient> findById(Integer id);

    @Query("SELECT k FROM Klient k WHERE k.login = :login")
    public Klient getUzytkownikByLogin(@Param("login") String login);

    List<Klient> getUzytkownikByRola(Rola rola);
}
