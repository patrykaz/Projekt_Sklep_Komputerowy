package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.HelperClass;

import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Produkt;

public class DodajProduktDoKoszyka {

    private Produkt produkt;
    private int liczba_wybranych_sztuk = 1;


    public DodajProduktDoKoszyka() {}

    public DodajProduktDoKoszyka(Produkt produkt, int liczba_wybranych_sztuk) {
        this.produkt = produkt;
        this.liczba_wybranych_sztuk = liczba_wybranych_sztuk;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public int getLiczba_wybranych_sztuk() {
        return liczba_wybranych_sztuk;
    }

    public void setLiczba_wybranych_sztuk(int liczba_wybranych_sztuk) {
        this.liczba_wybranych_sztuk = liczba_wybranych_sztuk;
    }
}
