package com.example.DefaultAuth.service;

import com.example.DefaultAuth.models.usertype.UserType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService{
    
    @Autowired
    PasswordEncoder passEncoder;

    //TODO replace with actual database connection
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
            UserType res = new UserType("abcd1234",passEncoder.encode("abcd1234"));
            return res;

    }

}