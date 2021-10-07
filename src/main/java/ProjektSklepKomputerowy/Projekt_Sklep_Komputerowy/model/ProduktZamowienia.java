package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Produkty_zamowienia")
public class ProduktZamowienia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduktuZamowienia;
    @ManyToOne
    private Zamowienie zamowienie;
    @ManyToOne
    private Produkt produkt;
    @NotNull
    @Min(value = 0)
    @Max(value = 40)
    private int wybranaLiczbaSztuk;

    public ProduktZamowienia(){}

    public ProduktZamowienia(Zamowienie zamowienie, Produkt produkt, @NotNull @Min(value = 0) @Max(value = 40) int wybranaLiczbaSztuk) {
        this.zamowienie = zamowienie;
        this.produkt = produkt;
        this.wybranaLiczbaSztuk = wybranaLiczbaSztuk;
    }

    public Integer getIdProduktuZamowienia() {
        return idProduktuZamowienia;
    }

    public void setIdProduktuZamowienia(Integer idProduktuZamowienia) {
        this.idProduktuZamowienia = idProduktuZamowienia;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public int getWybranaLiczbaSztuk() {
        return wybranaLiczbaSztuk;
    }

    public void setWybranaLiczbaSztuk(int wybranaLiczbaSztuk) {
        this.wybranaLiczbaSztuk = wybranaLiczbaSztuk;
    }

    @Override
    public String toString() {
        return String.format("[%s] - [%s] - [%s]", zamowienie.getIdZamowienia(), produkt.getIdProduktu(), wybranaLiczbaSztuk);
    }
}
