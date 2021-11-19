package com.rango.alere.config;

import com.rango.alere.services.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                    .frameOptions().disable()
                    .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**")
                    .authenticated()
                .antMatchers("/alimentos/new")
                    .hasRole("DOADOR")
                .antMatchers("/alimentos/**/solicitar")
                    .hasRole("RECEPTOR")
                .antMatchers("/solicitacoes/**")
                    .authenticated()
                .antMatchers("/solicitacoes/*/responder")
                    .hasRole("DOADOR")
                .antMatchers("/doacoes/**")
                    .permitAll()
                .anyRequest()
                    .permitAll()
                    .and()
                .formLogin()
                    .loginPage("/auth/login")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/auth/logout");
    }
}
