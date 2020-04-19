package com.example.DefaultAuth.securitysettings;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * 
 * Handler responsible for unsetting the cookie for logout
 * It removes the cookie from the client 
 */

public class JwtLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Cookie cookie = new Cookie("sessionToken","");
        cookie.setHttpOnly(true);
        //cookie.setSecure(true);  //TODO uncomment this section so it remains secure if the https is being used
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    
}
