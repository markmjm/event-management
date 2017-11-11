package com.mjm.eventmanagment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // and in event repositor, add method level security
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{


    // used to configure users and roles?
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        //define 2 users, Mark and admin
        auth.inMemoryAuthentication()
                .withUser("mark")
                .password("mark")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");
    }

    //Used to define authentication and authorization?
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/events").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/events/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/events/**").hasRole("ADMIN")
        .and()
        .csrf().disable();
    }
}
