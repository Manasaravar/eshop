package com.korniushin.eshop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig   {
    private final UserDetailsService userDetailsService;
    private final DataSource dataSource;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(request ->
                        request

                                .requestMatchers("/", "/products/**", "/products", "/productdetails/**", "/index", "/login", "/contacts", "/404").permitAll()
                                .requestMatchers("/registration").hasRole("ANONYMOUS")
//                                .requestMatchers(new AntPathRequestMatcher("/admin/users", "GET")).authenticated()
//                                .requestMatchers(new AntPathRequestMatcher("/admin/users", "POST")).hasAnyRole("MANAGER", "ADMIN")
                                //.requestMatchers("/logout", "/admin/users", "/account").authenticated()
                                .requestMatchers("/admin/users").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers("/admin/users/delete/**").hasRole("ADMIN")
                                .requestMatchers("/cart", "/cart/**").hasRole("CLIENT")
                                .anyRequest().authenticated()

                )
                .formLogin(form ->
                        form
                                .loginPage("/login").permitAll()
                                .failureUrl("/login")
                                .defaultSuccessUrl("/index")
                )
                .logout(form ->
                                form
                                        .logoutUrl("/logout")
                                        .logoutSuccessUrl("/index")
                                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .requestMatchers("/css/*.css", "/fonts/**", "/images/**");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encoder());
        return provider;
    }

    @Bean
    public UserDetailsManager userDetailsManager(HttpSecurity http) throws Exception {
        AuthenticationManager authManager = http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder())
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .build();
        JdbcUserDetailsManager jdbcManager = new JdbcUserDetailsManager(dataSource);
        jdbcManager.setAuthenticationManager(authManager);
        return jdbcManager;
    }

    @Bean
    protected PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }
}

