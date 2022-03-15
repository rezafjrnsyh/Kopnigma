package com.enigma.vica.kopnigma.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "savings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Savings {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "saldo")
    private Long Balance;

    @OneToOne(targetEntity = Member.class)
    private Member member;
}
