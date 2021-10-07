package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Role")
public class Rola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRoli;
    @Size(min = 4, max = 40, message = "Rola musi miec miedzy 4 a 40 znaków")
    private String nazwaRoli;
    @OneToMany(mappedBy = "rola")
    private List<Klient> klienci;
    @OneToMany(mappedBy = "rola")
    private List<Pracownik> pracownicy;


    public Rola(){}

    public Rola(@Size(min = 4, max = 40, message = "Rola musi miec miedzy 4 a 40 znaków") String nazwaRoli) {
        this.nazwaRoli = nazwaRoli;
    }

    public Integer getIdRoli() {
        return idRoli;
    }

    public void setIdRoli(Integer idRoli) {
        this.idRoli = idRoli;
    }

    public String getNazwaRoli() {
        return nazwaRoli;
    }

    public void setNazwaRoli(String nazwaRoli) {
        this.nazwaRoli = nazwaRoli;
    }

}
