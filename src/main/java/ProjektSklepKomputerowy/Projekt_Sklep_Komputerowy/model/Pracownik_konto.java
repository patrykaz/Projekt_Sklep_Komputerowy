package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pracownik_konto implements UserDetails {

    private Pracownik pracownik;

    public Pracownik_konto(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(pracownik.getRola().getNazwaRoli()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return pracownik.getHaslo();
    }

    @Override
    public String getUsername() {
        return pracownik.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() { return true; }
}
