package com.arkpes.arkpes_test;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Investors")
public class Investor implements Serializable {

    public Investor() {}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true)
    private String name;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name="investorId")
    private List<Fund> funds;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Investor)) {
            return false;
        }

        Investor investor = (Investor) o;

        return investor.getName().equals(this.getName())
                && investor.getId().equals(this.getId())
                && investor.getFunds().equals(this.getFunds());
    }
}
