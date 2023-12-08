package com.clawson.restful.config;

import com.clawson.restful.model.Manager;
import com.clawson.restful.service.SpringDataJpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) //deprecated replaced
public class SecurityConfiguration { //base class for writing a security policy

        @Autowired
        private SpringDataJpaUserDetailsService userDetailsService;

        @Autowired
        public void configure(AuthenticationManagerBuilder auth) throws Exception { // All I needed to do was autowire this
                auth
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(Manager.PASSWORD_ENCODER);
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //ended up changing it since this is what it was replaced with

        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(antMatcher("/built/**")).permitAll()
                        .requestMatchers(antMatcher("/main.css")).permitAll()// I don't think this is what I want
                        //.requestMatchers(antMatcher("/api/**")).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .httpBasic(Customizer.withDefaults())
                .csrf((forge) -> forge.disable())
                .logout((logout) -> logout
                                .logoutSuccessUrl("/")
                                //.permitAll()
                        );
        return http.build();
        }
}
