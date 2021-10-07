package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Klienci")
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idKlienta;
    @Size(min = 3, max = 40, message = "Imie musi miec miedzy 3 a 40 znaków")
    private String imie;
    @Size(min = 3, max = 40, message = "Nazwisko musi miec miedzy 3 a 40 znaków")
    private String nazwisko;
    @Size(min = 9, max = 9, message = "telefon musi miec 9 znaków")
    private String telefon;
    @Size(min = 4, max = 40, message = "Email musi miec miedzy 6 a 40 znaków")
    private String eMail;
    @Valid
    @ManyToOne
    private Adres adres;
    @Column(unique = true)
    @Size(min = 4, max = 15, message = "Login musi miec miedzy 4 a 15 znaków")
    private String login;
    @Size(min = 6, max = 65, message = "Hasło musi miec miedzy 6 a 65 znaków")
    private String haslo;
    private boolean aktywne = true;
    @ManyToOne
    private Rola rola;
    @OneToMany(mappedBy = "klient")
    private List<Zamowienie> zamowieniaKlientow;

    public Klient() {}

    public Klient(@Size(min = 3, max = 40, message = "Imie musi miec miedzy 3 a 40 znaków") String imie, @Size(min = 3, max = 40, message = "Nazwisko musi miec miedzy 3 a 40 znaków") String nazwisko, @Size(min = 9, max = 9, message = "telefon musi miec 9 znaków") String telefon, @Size(min = 4, max = 40, message = "Email musi miec miedzy 6 a 40 znaków") String eMail, @Size(min = 4, max = 15, message = "Login musi miec miedzy 4 a 15 znaków") String login, @Size(min = 6, max = 65, message = "Hasło musi miec miedzy 6 a 65 znaków") String haslo) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
        this.eMail = eMail;
        this.login = login;
        this.haslo = haslo;
    }

    public Integer getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(Integer idKlienta) {
        this.idKlienta = idKlienta;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public boolean isAktywne() {
        return aktywne;
    }

    public void setAktywne(boolean aktywne) {
        this.aktywne = aktywne;
    }

    public Rola getRola() {
        return rola;
    }

    public void setRola(Rola rola) {
        this.rola = rola;
    }

    public List<Zamowienie> getZamowieniaKlientow() {
        return zamowieniaKlientow;
    }

    public void setZamowieniaKlientow(List<Zamowienie> zamowieniaKlientow) {
        this.zamowieniaKlientow = zamowieniaKlientow;
    }
}
