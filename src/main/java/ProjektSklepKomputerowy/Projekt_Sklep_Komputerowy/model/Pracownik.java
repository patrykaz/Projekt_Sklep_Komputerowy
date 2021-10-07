package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "Pracownicy")
public class Pracownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPracownika;
    @Size(min = 3, max = 40, message = "Imie musi miec miedzy 3 a 40 znaków")
    private String imie;
    @Size(min = 3, max = 40, message = "Nazwisko musi miec miedzy 3 a 40 znaków")
    private String nazwisko;
    @Size(min = 9, max = 9, message = "telefon musi miec 9 znaków")
    private String telefon;
    @Size(min = 11, max = 11, message = "Pesel musi mieć 11 znaków")
    private String pesel;
    private LocalDateTime dataZatrudnienia = LocalDateTime.now().withNano(0);
    @Min(value = 0, message = "Cena nie może być mniejsza i równa 0")
    @Max(value = 20000, message = "Cena nie może być większa od 20 000 zł")
    private double pensja;
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
    @OneToMany(mappedBy = "pracownik")
    private List<Zamowienie> zrealizowaneZamowieniaPracownikow;

    public Pracownik() {}

    public Pracownik(@Size(min = 3, max = 40, message = "Imie musi miec miedzy 3 a 40 znaków") String imie, @Size(min = 3, max = 40, message = "Nazwisko musi miec miedzy 3 a 40 znaków") String nazwisko, @Size(min = 9, max = 9, message = "telefon musi miec 9 znaków") String telefon, @Size(min = 11, max = 11, message = "Pesel musi mieć 11 znaków") String pesel, @Min(value = 0, message = "Cena nie może być mniejsza i równa 0") @Max(value = 20000, message = "Cena nie może być większa od 20 000 zł") double pensja, @Size(min = 4, max = 15, message = "Login musi miec miedzy 4 a 15 znaków") String login, @Size(min = 6, max = 65, message = "Hasło musi miec miedzy 6 a 65 znaków") String haslo) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
        this.pesel = pesel;
        this.pensja = pensja;
        this.login = login;
        this.haslo = haslo;
    }

    public Integer getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(Integer idPracownika) {
        this.idPracownika = idPracownika;
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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public LocalDateTime getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(LocalDateTime dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public double getPensja() {
        return pensja;
    }

    public void setPensja(double pensja) {
        this.pensja = pensja;
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

    public List<Zamowienie> getZrealizowaneZamowieniaPracownikow() {
        return zrealizowaneZamowieniaPracownikow;
    }

    public void setZrealizowaneZamowieniaPracownikow(List<Zamowienie> zrealizowaneZamowieniaPracownikow) {
        this.zrealizowaneZamowieniaPracownikow = zrealizowaneZamowieniaPracownikow;
    }
}
