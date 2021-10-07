package ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy;

import ProjektSklepKomputerowy.Projekt_Sklep_Komputerowy.model.Uzytkownik_konto_serwis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService(){
        return new Uzytkownik_konto_serwis();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());


        return authenticationProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                //.anyRequest().permitAll();  // zezwala  wszystko
                .antMatchers("/").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/wynik_operacji_wiadomosc").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/rejestracja").permitAll()
                .antMatchers("/lista_produktow").permitAll()
                .antMatchers("/szczegoly_produktu").permitAll()
                .antMatchers("/lista_produktow_po_wybranej_kategorii").permitAll()
                .antMatchers("/koszyk_klienta").hasAuthority("ROLE_USER")
                .antMatchers("/usun_produkt_z_koszyka").hasAuthority("ROLE_USER")
                .antMatchers("/dodaj_produkt_do_koszyka").hasAuthority("ROLE_USER")
                .antMatchers("/zloz_zamowienie").hasAuthority("ROLE_USER")
                .antMatchers("/szczegoly_zamowienia").hasAuthority("ROLE_USER")
                //.antMatchers("/szczegoly_produktu").hasAnyAuthority("ROLE_USER", "ROLE_EMPLOYER", "ROLE_ADMIN")
                .antMatchers("/edytuj_Pracownika").hasAuthority("ROLE_ADMIN")
                .antMatchers("/usun_Pracownika").hasAuthority("ROLE_ADMIN")
                .antMatchers("/dodaj_nowego_pracownika").hasAuthority("ROLE_ADMIN")
                .antMatchers("/usuwanie_edycja_pracownikow").hasAuthority("ROLE_ADMIN")
                .antMatchers("/lista_zrealizowanych_zamowien_klienta").hasAnyAuthority("ROLE_USER","ROLE_EMPLOYER", "ROLE_ADMIN")
                .anyRequest().hasAnyAuthority("ROLE_EMPLOYER","ROLE_ADMIN")
                .and()
                .formLogin().defaultSuccessUrl("/home").permitAll()
                    .loginPage("/login")
                    .usernameParameter("login")
                    .passwordParameter("haslo")
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");


    }
}

// Konfiguracja testowa

//    authorizeRequests()
//                .anyRequest().authenticated()
//                        .and()
//                        .formLogin().permitAll()
//                        .and()
//                        .logout().permitAll();