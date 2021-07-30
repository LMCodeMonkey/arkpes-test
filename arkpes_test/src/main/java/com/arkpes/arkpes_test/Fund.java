package com.arkpes.arkpes_test;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Funds")
public class Fund {

    public Fund(){}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private double amount;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Fund)) {
            return false;
        }

        Fund fund = (Fund) o;

        return fund.getAmount() == this.getAmount()
                && fund.getId().equals(this.getId());
    }
}