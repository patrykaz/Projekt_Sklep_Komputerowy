package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "kategorieProduktow")
public class KategoriaProduktu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idKategorii;
    @Size(min = 3, max = 40, message = "Kategoria musi miec miedzy 3 a 40 znaków")
    private String nazwaKategorii;
    @OneToMany(mappedBy = "kategoriaProduktu")
    private List<Produkt> produkty;

    public KategoriaProduktu(){};

    public KategoriaProduktu(@Size(min = 3, max = 40, message = "Kategoria musi miec miedzy 3 a 40 znaków") String nazwaKategorii) {
        this.nazwaKategorii = nazwaKategorii;
    }

    public Integer getIdKategorii() {
        return idKategorii;
    }

    public void setIdKategorii(Integer idKategorii) {
        this.idKategorii = idKategorii;
    }

    public String getNazwaKategorii() {
        return nazwaKategorii;
    }

    public void setNazwaKategorii(String nazwaKategorii) {
        this.nazwaKategorii = nazwaKategorii;
    }

    public List<Produkt> getProdukty() {
        return produkty;
    }

    public void setProdukty(List<Produkt> produkty) {
        this.produkty = produkty;
    }

    @Override
    public String toString() {
        return String.format("[%s - %s]", idKategorii, nazwaKategorii);
    }
}
