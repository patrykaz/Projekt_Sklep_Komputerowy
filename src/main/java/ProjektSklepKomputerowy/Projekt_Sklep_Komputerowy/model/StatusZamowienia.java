package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "StatusZamowienia")
public class StatusZamowienia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatusuZamowienia;
    private String nazwaStatusuZamowienia;
    @OneToMany(mappedBy = "statusZamowienia")
    private List<Zamowienie> zamowienia;

    public StatusZamowienia(){};

    public StatusZamowienia(String nazwaStatusuZamowienia) {
        this.nazwaStatusuZamowienia = nazwaStatusuZamowienia;
    }

    public String getNazwaStatusuZamowienia() {
        return nazwaStatusuZamowienia;
    }

    public void setNazwaStatusuZamowienia(String nazwaStatusuZamowienia) {
        this.nazwaStatusuZamowienia = nazwaStatusuZamowienia;
    }

    @Override
    public String toString() {
        return String.format("[%s - %s]", idStatusuZamowienia, nazwaStatusuZamowienia);
    }
}
