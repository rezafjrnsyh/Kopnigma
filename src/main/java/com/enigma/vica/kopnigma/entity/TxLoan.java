package com.enigma.vica.kopnigma.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trx_loan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TxLoan {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(name = "amount_submitted")
    private Long amountSubmitted;

    @Column(name = "installment")
    private Integer installment;

    @ManyToOne()
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
