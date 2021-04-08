package com.vaadin.tutorial.crm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@EnableWebSecurity
@EnableAspectJAutoProxy
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

      private static final String LOGIN_PROCESSING_URL = "/login";
      private static final String LOGIN_FAILURE_URL = "/login?error";
      private static final String LOGIN_URL = "/login";
      private static final String LOGOUT_SUCCESS_URL = "/login";

    /**
     * Require login to access internal pages and configure login form.
     */
      @Override
      protected void configure(HttpSecurity http) throws Exception {

      // Not using Spring CSRF here to be able to use plain HTML for the login page
          http.csrf().disable()

      // Register our CustomRequestCache, that saves unauthorized access attempts, so
      // the user is redirected after login.
      .requestCache().requestCache(new CustomRequestCache())

      // Restrict access to our application.
      .and().authorizeRequests()

      // Allow all flow internal requests.
      .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()

      // Allow all requests by logged in users.
      .anyRequest().authenticated()

      // Configure the login page.
      .and().formLogin()
      .loginPage(LOGIN_URL).permitAll().loginProcessingUrl(LOGIN_PROCESSING_URL)
      .failureUrl(LOGIN_FAILURE_URL)

      // Configure logout
      .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);

      }

    /**
     * Implements the {@link UserDetailsService}.
     *
     * Create an user for login
     * Note: This is for practice, do not use on real app
     */
      @Bean
      @Override
      public UserDetailsService userDetailsService() {

        UserDetails user = User.withUsername("user")
                        .password("{noop}u")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
      }

      @Override
      public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/VAADIN/**",
                "/favicon.ico",
                "/robots.txt",
                "/manifest.webmanifest",
                "/sw.js",
                "/offline.html",
                "/icons/**",
                "/images/**",
                "/styles/**",
                "/h2-console/**");
  }

}
