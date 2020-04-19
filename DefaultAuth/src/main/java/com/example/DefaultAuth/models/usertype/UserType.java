package com.example.DefaultAuth.models.usertype;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/* 
User from the database
Used only for service purposes
*/

public class UserType implements UserDetails{
    
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    
    /*should use only this method inside and outside the class to set the password*/
    
    public UserType(){}
    
    public UserType(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public UserType(String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getUsername(){return this.username;}
    @Override
    public String getPassword(){return this.password;}
    
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    
    
    public void setUsername(String username){this.username = username;}
    public void setPassword(String password){this.password = password;}  
    public void setFirstName(String firstName){this.firstName = firstName;}
    public void setLastName(String lastName){this.lastName = lastName;}
}