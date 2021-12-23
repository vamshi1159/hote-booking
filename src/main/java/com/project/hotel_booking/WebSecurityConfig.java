package com.project.hotel_booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource securityDataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/rooms/create").hasRole("ADMIN")
                .antMatchers("/rooms/update").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/rooms/**").hasAnyRole("ADMIN","EMPLOYEE","MANAGER")
                .antMatchers("/bookings/**").hasAnyRole("ADMIN","EMPLOYEE","MANAGER")
                .antMatchers("/rooms/update").hasAnyRole("ADMIN","MANAGER")
                .and()
                .formLogin()
                .loginPage("/showLogin")
                .loginProcessingUrl("/authenticateTheUser")
                .and()
                .logout()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(securityDataSource)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
