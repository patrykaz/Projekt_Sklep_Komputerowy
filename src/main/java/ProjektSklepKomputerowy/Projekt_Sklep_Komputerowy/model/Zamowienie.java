package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "Zamowienia")
public class Zamowienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idZamowienia;
    @ManyToOne
    private Klient klient;
    @ManyToOne
    private Pracownik pracownik;
    private LocalDateTime dataRozpoczeciaZamowienia = LocalDateTime.now().withNano(0);
    private LocalDateTime dataRealizacjiZamowienia;
    private LocalDateTime dataWysylkiZamowienia;
    @NotNull
    @ManyToOne
    private StatusZamowienia statusZamowienia;
    @OneToMany(mappedBy = "zamowienie")
    private List<ProduktZamowienia> produktZamowienia;


    public Zamowienie(){}

    public Integer getIdZamowienia() {
        return idZamowienia;
    }

    public void setIdZamowienia(Integer idZamowienia) {
        this.idZamowienia = idZamowienia;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public LocalDateTime getDataRozpoczeciaZamowienia() {
        return dataRozpoczeciaZamowienia;
    }

    public void setDataRozpoczeciaZamowienia(LocalDateTime dataRozpoczeciaZamowienia) {
        this.dataRozpoczeciaZamowienia = dataRozpoczeciaZamowienia;
    }

    public LocalDateTime getDataRealizacjiZamowienia() {
        return dataRealizacjiZamowienia;
    }

    public void setDataRealizacjiZamowienia(LocalDateTime dataRealizacjiZamowienia) {
        this.dataRealizacjiZamowienia = dataRealizacjiZamowienia;
    }

    public LocalDateTime getDataWysylkiZamowienia() {
        return dataWysylkiZamowienia;
    }

    public void setDataWysylkiZamowienia(LocalDateTime dataWysylkiZamowienia) {
        this.dataWysylkiZamowienia = dataWysylkiZamowienia;
    }

    public StatusZamowienia getStatusZamowienia() {
        return statusZamowienia;
    }

    public void setStatusZamowienia(StatusZamowienia statusZamowienia) {
        this.statusZamowienia = statusZamowienia;
    }

    public List<ProduktZamowienia> getProduktZamowienia() {
        return produktZamowienia;
    }

    public void setProduktZamowienia(List<ProduktZamowienia> produktZamowienia) {
        this.produktZamowienia = produktZamowienia;
    }

    @Override
    public String toString() {
        return String.format("[%s - %s - %s - %s - %s]", idZamowienia, klient.getImie(), dataRozpoczeciaZamowienia, dataRealizacjiZamowienia, dataWysylkiZamowienia);
    }

}
