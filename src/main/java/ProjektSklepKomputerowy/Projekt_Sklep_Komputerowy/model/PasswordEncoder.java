package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main(String[] args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String haslo = "admin";
        String zakodowane_haslo = encoder.encode(haslo);
        System.out.println(zakodowane_haslo);
    }
}
