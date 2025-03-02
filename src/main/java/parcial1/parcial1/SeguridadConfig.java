package parcial1.parcial1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SeguridadConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/publico").permitAll()  // Ruta pública
                        .requestMatchers("/privado").hasRole("USER") // Solo USER puede acceder
                        .anyRequest().authenticated()
                )
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .csrf().disable(); // Deshabilitado para permitir llamadas API sin problemas

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails usuario = User.withUsername("usuario")
                .password(passwordEncoder().encode("contraseña"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(usuario, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
