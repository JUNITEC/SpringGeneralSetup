package com.example.DefaultMicroAuth.models.usertype;

import java.util.ArrayList;
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
    private Collection<Authority> authorities;

    /**
     * Represents the authority of a user
     */
    private class Authority implements GrantedAuthority{

        private String authority;

        public Authority(String authority){
            this.authority = authority;
        }

        @Override
        public String getAuthority() {
            return this.authority;
        }
        
    }


    /*should use only this method inside and outside the class to set the password*/
    
    public UserType(){
        this.authorities = new ArrayList<>();
        this.authorities.add(new Authority("USER"));
    }
    
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
        return this.authorities;
    }
    
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    
    
    public void setUsername(String username){this.username = username;}
    public void setPassword(String password){this.password = password;}  
    public void setFirstName(String firstName){this.firstName = firstName;}
    public void setLastName(String lastName){this.lastName = lastName;}
}