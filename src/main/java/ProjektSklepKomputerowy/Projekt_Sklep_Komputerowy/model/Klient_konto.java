package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Klient_konto implements UserDetails {

    private Klient klient;

    public Klient_konto(Klient klient) {
        this.klient = klient;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(klient.getRola().getNazwaRoli()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return klient.getHaslo();
    }

    @Override
    public String getUsername() {
        return klient.getLogin();
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
