package com.example.DefaultAuth.securitysettings;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * 
 * Filter responsible for the logout of the system. This filter has to do two
 * things: -> unset the token so the client does not possess the token anymore
 * -> remove the JWT from the system
 * 
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
