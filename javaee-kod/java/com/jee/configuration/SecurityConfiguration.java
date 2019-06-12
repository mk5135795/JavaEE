package com.jee.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

 @Autowired
 private BCryptPasswordEncoder bCryptPasswordEncoder;
 
 @Autowired
 private DataSource dataSource;

 private final String USERS_QUERY = "select login, password, true from Uzytkownik where login=?";
 private final String AUTH_QUERY = "select login, 'default' from Uzytkownik where login=?";
 

 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  auth.jdbcAuthentication()
  .usersByUsernameQuery(USERS_QUERY)
  .authoritiesByUsernameQuery(AUTH_QUERY)
   .dataSource(dataSource)
   .passwordEncoder(bCryptPasswordEncoder);
 }
 
 @Override
 protected void configure(HttpSecurity http) throws Exception{
  http.authorizeRequests()
   .antMatchers("/").permitAll()
   .antMatchers("/login").permitAll()
   .antMatchers("/signup").permitAll()
   .antMatchers("/home/**")
   .authenticated().and().csrf().disable()
   .formLogin().loginPage("/login").failureUrl("/login?error=true")
   .defaultSuccessUrl("/home/home")
   .usernameParameter("login")
   .passwordParameter("password")
   .and().logout()
   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
   .logoutSuccessUrl("/")
   .and().exceptionHandling().accessDeniedPage("/access_denied");
 }
 
}