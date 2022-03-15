package com.enigma.vica.kopnigma.controller.httpresp;

import com.enigma.vica.kopnigma.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewMemberResp {
    Member member;
    Long balance;

    @Override
    public String toString() {
        return "NewMemberResp{" +
                "member=" + member +
                ", balance=" + balance +
                '}';
    }
}
