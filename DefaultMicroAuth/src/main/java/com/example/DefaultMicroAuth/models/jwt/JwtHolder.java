package com.example.DefaultMicroAuth.models.jwt;

/*
data type to hold any jwt. It must not be changed
*/

public class JwtHolder{

    private final String jwt;

    public JwtHolder(String jwt){
        this.jwt = jwt;
    }

    public String getJwt(){
        return this.jwt;
    }
}