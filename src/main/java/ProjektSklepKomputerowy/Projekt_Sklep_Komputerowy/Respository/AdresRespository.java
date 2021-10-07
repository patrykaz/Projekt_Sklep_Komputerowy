package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository;

import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Adres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface AdresRespository extends JpaRepository<Adres, Integer> {
}
