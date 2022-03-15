package com.enigma.vica.kopnigma.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "document")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String name;
    private String type;
    @Lob
    private byte[] data;
    @OneToOne(targetEntity = Member.class)
    private Member member;

    public Document(String name, String type, byte[] data, Member member) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.member = member;
    }
}
