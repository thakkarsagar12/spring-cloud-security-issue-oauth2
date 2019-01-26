package com.welove.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userDetailService")
    private UserDetailsService userDetailsService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth    .parentAuthenticationManager(authenticationManagerBean())
                .userDetailsService(userDetailsService).passwordEncoder(encoder())
                .and()
                .inMemoryAuthentication()
                    .withUser("user1")
                    .password("password1")
                    .roles("ADMIN")

        ;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/api-docs/**").permitAll()
                .anyRequest().authenticated()
                .and().anonymous().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Allow swagger to be accessed without authentication
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/v2/api-docs")//
                .antMatchers("/swagger-resources/**")//
                .antMatchers("/swagger-ui.html")//
                .antMatchers("/configuration/**")//
                .antMatchers("/webjars/**")//
                .antMatchers("**/templates/**")//
                .antMatchers("/public")
                .antMatchers("/api/upload/**");
    }

    @Bean
    public PasswordEncoder encoder(){
        return  NoOpPasswordEncoder.getInstance();
    }

}
