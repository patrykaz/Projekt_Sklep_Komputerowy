package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Produkty")
public class Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduktu;
    @Size(min = 4, max = 50, message = "Nazwa produktu musi miec miedzy 4 a 50 znaków")
    private String nazwa;
    @NotNull
    @ManyToOne
    private KategoriaProduktu kategoriaProduktu;
    @Size(min = 10, max = 400, message = "Opis produktu musi miec miedzy 10 a 400 znaków")
    private String opis;
    @Min(value = 0, message = "Cena nie może być mniejsza i równa 0")
    @Max(value = 30000, message = "Cena nie może być większa od 30 000 zł")
    private double cena;
    @Min(value = 0,  message = "Liczba sztuk musi być wieksza od 0")
    @Max(value = 1000, message = "Liczba sztuk nie może prakraczać 1 000")
    private int liczbaSztuk;
    @OneToMany(mappedBy = "produkt")
    private List<ProduktZamowienia> produktyZamowienia;

    public Produkt(){};

    public Produkt(@Size(min = 4, max = 40, message = "Nazwa produktu musi miec miedzy 4 a 50 znaków") String nazwa, @NotNull KategoriaProduktu kategoriaProduktu, @Size(min = 10, max = 400, message = "Opis produktu musi miec miedzy 10 a 400 znaków") String opis, @Min(value = 0, message = "Cena nie może być mniejsza i równa 0") @Max(value = 30000, message = "Cena nie może być większa od 30 000 zł") double cena, @Min(value = 1, message = "Liczba sztuk musi być wieksza lub równa 1") @Max(value = 1000, message = "Liczba sztuk nie może prakraczać 1 000") int liczbaSztuk) {
        this.nazwa = nazwa;
        this.kategoriaProduktu = kategoriaProduktu;
        this.opis = opis;
        this.cena = cena;
        this.liczbaSztuk = liczbaSztuk;
    }

    public Integer getIdProduktu() {
        return idProduktu;
    }

    public void setIdProduktu(Integer idProduktu) {
        this.idProduktu = idProduktu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public KategoriaProduktu getKategoriaProduktu() {
        return kategoriaProduktu;
    }

    public void setKategoriaProduktu(KategoriaProduktu kategoriaProduktu) {
        this.kategoriaProduktu = kategoriaProduktu;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getLiczbaSztuk() {
        return liczbaSztuk;
    }

    public void setLiczbaSztuk(int liczbaSztuk) {
        this.liczbaSztuk = liczbaSztuk;
    }

    public List<ProduktZamowienia> getProduktyZamowienia() {
        return produktyZamowienia;
    }

    public void setProduktyZamowienia(List<ProduktZamowienia> produktyZamowienia) {
        this.produktyZamowienia = produktyZamowienia;
    }

    @Override
    public String toString() {
        return String.format("[%s - %s - %s - %s - %s - %s]", idProduktu, nazwa, kategoriaProduktu.getNazwaKategorii(), opis, cena, liczbaSztuk);
    }
}
