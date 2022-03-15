package com.enigma.vica.kopnigma.controller.httpreq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {
    String email;
    String password;

    @Override
    public String toString() {
        return "LoginReq{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


