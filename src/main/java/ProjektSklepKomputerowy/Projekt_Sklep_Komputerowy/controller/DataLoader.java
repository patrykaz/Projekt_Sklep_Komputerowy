//package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.controller;
//
//import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.Respository.*;
//import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements ApplicationRunner {
//
//
//    @Autowired
//    Kategoria_produktuRespository kategoria_produktuRespository;
//
//    @Autowired
//    Status_zamowieniaRespository status_zamowieniaRespository;
//
//    @Autowired
//    ProduktRespository produktRespository;
//
//    @Autowired
//    ZamowienieRespository zamowienieRespository;
//
//    @Autowired
//    Produkt_zamowieniaRespository produkt_zamowieniaRespository;
//
//    @Autowired
//    RolaRespository rolaRespository;
//
//    @Autowired
//    AdresRespository adresRespository;
//
//    @Autowired
//    PracownikRespository pracownikRespository;
//
//    @Autowired
//    KlientRespository klientRespository;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//
//        KategoriaProduktu kategoria_produktu1 = new KategoriaProduktu("Myszki");
//        KategoriaProduktu kategoria_produktu2 = new KategoriaProduktu("Klawiatury");
//        KategoriaProduktu kategoria_produktu3 = new KategoriaProduktu("Słuchawki");
//
//        kategoria_produktuRespository.save(kategoria_produktu1);
//        kategoria_produktuRespository.save(kategoria_produktu2);
//        kategoria_produktuRespository.save(kategoria_produktu3);
//
//        StatusZamowienia status_zamowienia1 = new StatusZamowienia("Otwarte");
//        StatusZamowienia status_zamowienia2 = new StatusZamowienia("Zapłacone");
//        StatusZamowienia status_zamowienia3 = new StatusZamowienia("Wysłane");
//
//        status_zamowieniaRespository.save(status_zamowienia1);
//        status_zamowieniaRespository.save(status_zamowienia2);
//        status_zamowieniaRespository.save(status_zamowienia3);
//
//
//        Produkt produkt1 = new Produkt("A4Tech Bloody J95 RGB", kategoria_produktu1, "Typ myszy:Dla graczy\n" +
//                "Sensor:Optyczny\n" +
//                "Liczba przycisków:9\n" +
//                "Rozdzielczość:5000 dpi", 109.99, 38);
//        Produkt produkt2 = new Produkt("Razer Ornata Chroma", kategoria_produktu2, "Przeznaczenie:Gaming\n" +
//                "Przełączniki:Mechaniczno-membranowe - Razer Mecha-Membrane\n" +
//                "Łączność:Przewodowa\n" +
//                "Podświetlenie:Wielokolorowe", 299.00, 37);
//        Produkt produkt3 = new Produkt("Logitech G PRO GAMING HEADSET", kategoria_produktu3, "Łączność:Przewodowe\n" +
//                "Budowa słuchawek:Nauszne, Zamknięte\n" +
//                "Mikrofon:Tak\n" +
//                "Redukcja hałasu:Pasywna", 399.00, 36);
//
//        produktRespository.save(produkt1);
//        produktRespository.save(produkt2);
//        produktRespository.save(produkt3);
//
//        Klient klient1 = new Klient("Kamila" , "Pietrzak", "123456101", "kamila123@onet.pl", "kamila","$2a$10$gXHLDHclaG5sm8y2rT2J.etQvKPj5vfTx2VzoOYJLZQTDjjODkKtW");
//        Klient klient2 = new Klient("Anna" , "Nowak", "123456102", "anna123@onet.pl", "anna", "$2a$10$gXHLDHclaG5sm8y2rT2J.etQvKPj5vfTx2VzoOYJLZQTDjjODkKtW");
//        Klient klient3 = new Klient("Izabela" , "Duda", "123456103", "izabela123@onet.pl", "izabela", "$2a$10$gXHLDHclaG5sm8y2rT2J.etQvKPj5vfTx2VzoOYJLZQTDjjODkKtW");
//        Klient klient4 = new Klient("Sabina" , "Stasik", "123456104", "sabina123@onet.pl", "sabina", "$2a$10$gXHLDHclaG5sm8y2rT2J.etQvKPj5vfTx2VzoOYJLZQTDjjODkKtW");
//        Klient klient5 = new Klient("Paweł" , "Głód", "123456105", "pawell123@onet.pl", "pawel", "$2a$10$gXHLDHclaG5sm8y2rT2J.etQvKPj5vfTx2VzoOYJLZQTDjjODkKtW");
//
//        klientRespository.save(klient1);
//        klientRespository.save(klient2);
//        klientRespository.save(klient3);
//        klientRespository.save(klient4);
//        klientRespository.save(klient5);
//
//        Pracownik pracownik1 = new Pracownik("Patryk" , "Staroń", "123456201", "12345678910", 5600, "admin", "$2a$10$3degu/U8z3Y1u68T7kBTBuAglmhHVWev46aXI0gnmIL.pCZsC.icK");
//        Pracownik pracownik2 = new Pracownik("Jakub" , "Nowak", "123456202","12345678911", 2700, "jakub", "$2a$10$gXHLDHclaG5sm8y2rT2J.etQvKPj5vfTx2VzoOYJLZQTDjjODkKtW");
//        Pracownik pracownik3= new Pracownik("Michał" , "Borek", "123456203", "12345678912", 2800, "michal", "$2a$10$gXHLDHclaG5sm8y2rT2J.etQvKPj5vfTx2VzoOYJLZQTDjjODkKtW");
//        Pracownik pracownik4 = new Pracownik("Adam" , "Głód", "123456204", "12345678913", 2900, "adam", "$2a$10$gXHLDHclaG5sm8y2rT2J.etQvKPj5vfTx2VzoOYJLZQTDjjODkKtW");
//
//
//        pracownikRespository.save(pracownik1);
//        pracownikRespository.save(pracownik2);
//        pracownikRespository.save(pracownik3);
//        pracownikRespository.save(pracownik4);
//
//
//        Adres adres1 = new Adres("Akacjowa","2B","38-400","Krosno" );
//        Adres adres2 = new Adres("Akacjowa","45C","38-400","Krosno" );
//        Adres adres3 = new Adres("Akacjowa","123D","38-400","Krosno" );
//        Adres adres4 = new Adres("Akacjowa","145A","38-400","Krosno" );
//        Adres adres5 = new Adres("Akacjowa","15B","38-400","Krosno" );
//
//        adresRespository.save(adres1);
//        adresRespository.save(adres2);
//        adresRespository.save(adres3);
//        adresRespository.save(adres4);
//        adresRespository.save(adres5);
//
//        klient1.setAdres(adres1);
//        klient2.setAdres(adres2);
//        klient3.setAdres(adres3);
//        klient4.setAdres(adres4);
//        klient5.setAdres(adres5);
//
//        klientRespository.save(klient1);
//        klientRespository.save(klient2);
//        klientRespository.save(klient3);
//        klientRespository.save(klient4);
//        klientRespository.save(klient5);
//
//
//        Rola user = new Rola("ROLE_USER");
//        Rola employer = new Rola("ROLE_EMPLOYER");
//        Rola admin = new Rola("ROLE_ADMIN");
//
//        rolaRespository.save(user);
//        rolaRespository.save(employer);
//        rolaRespository.save(admin);
//
//
//        pracownik1.setRola(admin);
//        pracownik2.setRola(employer);
//        pracownik3.setRola(employer);
//        pracownik4.setRola(employer);
//
//        klient1.setRola(user);
//        klient2.setRola(user);
//        klient3.setRola(user);
//        klient4.setRola(user);
//        klient5.setRola(user);
//
//
//        klientRespository.save(klient1);
//        klientRespository.save(klient2);
//        klientRespository.save(klient3);
//        klientRespository.save(klient4);
//        klientRespository.save(klient5);
//
//        pracownikRespository.save(pracownik1);
//        pracownikRespository.save(pracownik2);
//        pracownikRespository.save(pracownik3);
//        pracownikRespository.save(pracownik4);
//
//
//        Zamowienie zamowienie1 = new Zamowienie();
//        zamowienie1.setKlient(klient1);
//        zamowienie1.setStatusZamowienia(status_zamowienia1);
//
//        zamowienieRespository.save(zamowienie1);
//
//
//        ProduktZamowienia produkt_zamowienia1 = new ProduktZamowienia(zamowienie1, produkt1, 2);
//        ProduktZamowienia produkt_zamowienia2 = new ProduktZamowienia(zamowienie1, produkt2, 3);
//        ProduktZamowienia produkt_zamowienia3 = new ProduktZamowienia(zamowienie1, produkt3, 4);
//
//        produkt_zamowieniaRespository.save(produkt_zamowienia1);
//        produkt_zamowieniaRespository.save(produkt_zamowienia2);
//        produkt_zamowieniaRespository.save(produkt_zamowienia3);
//    }
//}
