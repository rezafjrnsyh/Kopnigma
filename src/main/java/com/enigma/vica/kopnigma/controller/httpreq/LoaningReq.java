package com.enigma.vica.kopnigma.controller.httpreq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoaningReq {
    private Long amountSubmitted;
    private Integer installment;

    @Override
    public String toString() {
        return "LoaningReq{" +
                "amountSubmitted=" + amountSubmitted +
                ", instalment=" + installment +
                '}';
    }
}
