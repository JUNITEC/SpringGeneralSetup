package com.example.DefaultAuth.securitysettings;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Cookie;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.DefaultAuth.models.jwt.JwtHolder;
import com.example.DefaultAuth.models.usertype.UserType;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *Filter that allows the login to be made  
 * 
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter{
    private AuthenticationManager authenticationManager;

    private JwtUtil jwtTokenUtil;

    public JwtLoginFilter(AuthenticationManager authManager, String url, JwtUtil jwt){
        this.authenticationManager = authManager;
        this.setFilterProcessesUrl(url);
        this.jwtTokenUtil = jwt;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        UserType user = null;
        try{
            user = new ObjectMapper().readValue(request.getInputStream(), UserType.class);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken = 
            new UsernamePasswordAuthenticationToken(user.getUsername(), 
                                                    user.getPassword(), 
                                                    new ArrayList<>());
        
        return this.authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        
        UserType user = (UserType)authResult.getPrincipal();
        
        JwtHolder jwt = null;
        try{
            jwt = new JwtHolder(this.jwtTokenUtil.generateToken(user));

        }
        catch(RuntimeException e){
            e.printStackTrace();
        }
        

        Cookie cookie = new Cookie("sessionToken",jwt.getJwt());
        cookie.setHttpOnly(true);
        //cookie.setSecure(true);  //TODO uncomment this section so it remains secure if the https is being used
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");

        response.addCookie(cookie);
    }
}