package com.zlagoda.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private EmployeeDetailsService employeeDetailsService;

    @Autowired
    public SecurityConfig(EmployeeDetailsService employeeDetailsService) {
        this.employeeDetailsService = employeeDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/products/add").hasAnyAuthority("MANAGER")
                        .requestMatchers("/products/edit/**").hasAnyAuthority("MANAGER")
                        .requestMatchers("/products/delete/**").hasAnyAuthority("MANAGER")
                        .requestMatchers("/products/totalSales/**").hasAnyAuthority("MANAGER")
                        .requestMatchers("/store-products/edit/**").hasAnyAuthority("MANAGER")
                        .requestMatchers("/store-products/add").hasAnyAuthority("MANAGER")
                        .requestMatchers("/store-products/add/**").hasAnyAuthority("MANAGER")
                        .requestMatchers("/store-products/delete/**").hasAnyAuthority("MANAGER")
                        .requestMatchers("/store-products/remove/**").hasAnyAuthority("MANAGER")
                        .requestMatchers("/categories/delete/**").hasAnyAuthority("MANAGER")
                        .requestMatchers("/categories/add").hasAnyAuthority("MANAGER")
                        .requestMatchers("/categories/edit/**").hasAnyAuthority("MANAGER")
                        .requestMatchers("/employees/**").hasAnyAuthority("MANAGER")
                        .requestMatchers("/checks/add").hasAnyAuthority("CASHIER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true"))
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll());
        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(employeeDetailsService).passwordEncoder(passwordEncoder());
    }
}
