package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.HelperClass;

import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.KategoriaProduktu;

public class WybierzKategorie {

    private KategoriaProduktu kategoriaProduktu;

    public WybierzKategorie(){}

    public WybierzKategorie(KategoriaProduktu kategoriaProduktu) {
        this.kategoriaProduktu = kategoriaProduktu;
    }

    public KategoriaProduktu getKategoriaProduktu() {
        return kategoriaProduktu;
    }

    public void setKategoriaProduktu(KategoriaProduktu kategoriaProduktu) {
        this.kategoriaProduktu = kategoriaProduktu;
    }
}
