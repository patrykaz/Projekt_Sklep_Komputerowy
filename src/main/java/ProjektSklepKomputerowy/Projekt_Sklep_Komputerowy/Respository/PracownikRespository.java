package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository;


import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Pracownik;
import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Rola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PracownikRespository extends JpaRepository <Pracownik, Integer> {

    void deleteById(Integer id);

    Optional<Pracownik> findById(Integer id);

    @Query("SELECT p FROM Pracownik p WHERE p.login = :login")
    public Pracownik getUzytkownikByLogin(@Param("login") String login);

    List<Pracownik> getUzytkownikByRola(Rola rola);

}
