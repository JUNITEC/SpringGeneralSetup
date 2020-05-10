package com.example.DefaultMicroAuth.service;

import com.example.DefaultMicroAuth.models.usertype.UserType;

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
        if(!username.equals("abcd1234")){
            throw new UsernameNotFoundException(username + " not found");
        }
        UserType res = new UserType("abcd1234",this.passEncoder.encode("abcd1234"));
        return res;

    }

}