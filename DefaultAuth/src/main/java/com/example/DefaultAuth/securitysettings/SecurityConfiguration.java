package com.example.DefaultAuth.securitysettings;

import java.util.Arrays;

import com.example.DefaultAuth.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/*
Spring security configuration class
*/
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private LoginService loginService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtToken;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationProvider());
    }

    // TODO add new endpoints
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
            .csrf()
            .disable()
            .addFilter(new JwtRequestFilter(this.authenticationManager(),this.loginService,this.jwtToken))
            .addFilter(new JwtLoginFilter(this.authenticationManager(),"/login/authenticate",this.jwtToken))
            .logout().logoutUrl("/login/logout").addLogoutHandler(new JwtLogoutHandler())
            .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)).and()
            .authorizeRequests()
            .antMatchers("/login/authenticate").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProv = new DaoAuthenticationProvider();
        authProv.setPasswordEncoder(this.passwordEncoder);
        authProv.setUserDetailsService(this.loginService);

        return authProv;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    /*Configuration only added for the localhost server */
    public CorsConfigurationSource corsConfigurationSource(){
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("*"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
