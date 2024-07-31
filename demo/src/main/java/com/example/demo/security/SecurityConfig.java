package com.example.demo.security;

import com.example.demo.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
//    @Bean
//    GrantedAuthorityDefaults grantedAuthorityDefaults(){ // Для отключения приставки ROLE_ из роли для аннотаций в контроллере
//        return new GrantedAuthorityDefaults("");
//    }

    @Bean
    SecurityFilterChain noSecurity(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(requests -> requests.anyRequest().permitAll()).build();
    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/timesheets/**").hasAnyAuthority(Role.USER.getName()) // каталог закрыт
//                        .requestMatchers("/projects/**").hasAnyAuthority(Role.ADMIN.getName()) // каталог закрыт
//                        .requestMatchers("/api/**").hasAuthority(Role.REST.getName()) // каталог закрыт
////                        .requestMatchers("/timesheets/**").hasAnyRole() // каталог закрыт нужна одна из ролей
////                        .requestMatchers("/timesheets/**").hasRole() // каталог закрыт нужна роль
////                        .anyRequest().denyAll() // все заблокировано
//                        .anyRequest().permitAll()) // все открыто
////                        .anyRequest().authenticated()) // нужна авторизация
////                .formLogin(it -> it.loginPage("/login.html")) //ссылка на страницу входа
////                .formLogin(it -> it.disable()) // страницы входа нет
//                .formLogin(Customizer.withDefaults()) // стандартная форма
//                .build();
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
