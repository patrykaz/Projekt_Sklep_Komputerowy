package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model;

import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository.KlientRespository;
import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository.PracownikRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class Uzytkownik_konto_serwis implements UserDetailsService {

    @Autowired
    private KlientRespository klientRespository;

    @Autowired
    private PracownikRespository pracownikRespository;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Klient klient = klientRespository.getUzytkownikByLogin(login);
        Pracownik pracownik = pracownikRespository.getUzytkownikByLogin(login);
        if (pracownik != null){
          return new Pracownik_konto(pracownik);
        }
        if (klient != null){
            return new Klient_konto(klient);
        }
        throw new UsernameNotFoundException("Nie znaleziono użytkownika o podanym loginie");
    }
}
