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
@Table(name = "trx_installment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TxInstallment {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(name = "installment_order")
    private Integer installmentOrder;

    @Column(name = "installment_interest")
    private Long installmentInterest;

    @Column(name = "installment_underlying")
    private Long installmentUnderlying;

    @Column(name = "status")
    private Integer status;

    @ManyToOne()
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Override
    public String toString() {
        return "TxInstallment{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", installmentOrder=" + installmentOrder +
                ", installmentInterest=" + installmentInterest +
                ", installmentUnderlying=" + installmentUnderlying +
                ", status=" + status +
                ", member=" + member +
                '}';
    }
}
