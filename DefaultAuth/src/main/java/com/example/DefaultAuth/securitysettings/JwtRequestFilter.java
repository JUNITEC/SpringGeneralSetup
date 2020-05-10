package com.example.DefaultAuth.securitysettings;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.DefaultAuth.service.LoginService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


/*
Extra spring security filter to add a verification filter for the JWT
*/

public class JwtRequestFilter extends BasicAuthenticationFilter {
    
    private LoginService loginService;
    
    private JwtUtil jwtUtil;
    
    public JwtRequestFilter(AuthenticationManager authenticationManager,LoginService loginService, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String username = null;
        String jwt = null;

        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                if(cookies[i].getName().equals("sessionToken")){
                    jwt = cookies[i].getValue();
                    username = jwtUtil.extractUsername(jwt);
                    break;
                }
            }
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.loginService.loadUserByUsername(username);
            if(this.jwtUtil.validateToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken usernamePassAuthToken = 
                    new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
                usernamePassAuthToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePassAuthToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}