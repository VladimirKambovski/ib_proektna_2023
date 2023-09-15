package com.example.ib_proektna_2023.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests(authorize ->
                        authorize
                                .antMatchers("/EmployeeManagement/**").hasRole("ADMIN")
                                .antMatchers("/CustomerManagement/**").hasRole("EMPLOYEE")
                                .antMatchers("/PersonalTransactionHistory/**").hasRole("CUSTOMER")
                                .anyRequest().authenticated()
                )
                .x509(x509 -> {
                    x509
                            .subjectPrincipalRegex("CN=(.*?)(?:,|$)");
                })
                .logout(logout -> {
                    logout
                            .clearAuthentication(true)
                            .deleteCookies("JSESSIONID")
                            .invalidateHttpSession(true)
                            .logoutSuccessUrl("/");
                })
                .csrf().disable();

        return http.build();
    }
}