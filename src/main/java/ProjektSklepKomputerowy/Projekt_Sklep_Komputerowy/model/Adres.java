package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Adresy")
public class Adres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdresu;
    @Size(min = 4, max = 40, message = "Ulica musi miec miedzy 4 a 40 znaków")
    private String ulica;
    @Size(min = 1, max = 10, message = "Nr domu musi miec miedzy 1 a 10 znaków")
    private String nrDomu;
    @Size(min = 6, max = 6, message = "Kod pocztowy musi miec 6 znaków")
    private String kodPocztowy;
    @Size(min = 4, max = 40, message = "Miejscowość musi miec miedzy 4 a 40 znaków")
    private String miejscowosc;
    @OneToMany(mappedBy = "adres")
    private List<Klient> klienci;
    @OneToMany(mappedBy = "adres")
    private List<Pracownik> pracownicy;


    public Adres() {}

    public Adres(@Size(min = 4, max = 40, message = "Imie musi miec miedzy 4 a 40 znaków") String ulica, @Size(min = 1, max = 10, message = "Imie musi miec miedzy 4 a 40 znaków") String nrDomu, @Size(min = 6, max = 6, message = "Imie musi miec miedzy 4 a 40 znaków") String kodPocztowy, @Size(min = 4, max = 40, message = "Imie musi miec miedzy 4 a 40 znaków") String miejscowosc) {
        this.ulica = ulica;
        this.nrDomu = nrDomu;
        this.kodPocztowy = kodPocztowy;
        this.miejscowosc = miejscowosc;
    }

    public Integer getIdAdresu() {
        return idAdresu;
    }

    public void setIdAdresu(Integer idAdresu) {
        this.idAdresu = idAdresu;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNrDomu() {
        return nrDomu;
    }

    public void setNrDomu(String nrDomu) {
        this.nrDomu = nrDomu;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public List<Klient> getKlienci() {
        return klienci;
    }

    public void setKlienci(List<Klient> klienci) {
        this.klienci = klienci;
    }

    public List<Pracownik> getPracownicy() {
        return pracownicy;
    }

    public void setPracownicy(List<Pracownik> pracownicy) {
        this.pracownicy = pracownicy;
    }

    @Override
    public String toString() {
        return String.format("[%s - %s - %s - %s - %s]", idAdresu, ulica, nrDomu, kodPocztowy, miejscowosc);
    }
}
