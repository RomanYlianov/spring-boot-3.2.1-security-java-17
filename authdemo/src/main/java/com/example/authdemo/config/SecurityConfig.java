package com.example.authdemo.config;

import com.example.authdemo.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsService service;

    @Autowired
    public SecurityConfig(UserDetailsService service) {
        this.service = service;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/auth/logout", "POST").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/auth/login")
                        .permitAll()
                        .defaultSuccessUrl("/")
                )
                .logout((logout) -> logout.logoutUrl("/auth/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST")).invalidateHttpSession(true)
                        .clearAuthentication(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/auth/login"));

        return http.build();
    }

    @Bean
   public UserDetailsService service()
   {
       return new UserDetailsServiceImpl();
   }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(encoder());
        daoAuthenticationProvider.setUserDetailsService(service);
        return daoAuthenticationProvider;
    }
    @Bean("pass")
    @Primary
    public PasswordEncoder encoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        return encoder;
    }


}
