package com.enigma.vica.kopnigma.controller.httpreq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositReq {
    private Long nominal;

    @Override
    public String toString() {
        return "DepositReq{" +
                "nominal=" + nominal +
                '}';
    }
}
