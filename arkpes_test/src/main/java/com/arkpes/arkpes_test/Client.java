package com.arkpes.arkpes_test;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Clients")
public class Client {

    public Client(){}

    public Client(String name, String description, String phoneNumber, clientType type, List<Investor> investors) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.investors = investors;
    }

    enum clientType {
        DOMESTIC,
        INTERNATIONAL
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    @Pattern(regexp = "^(?![+-=]).*")
    private String name;
    private String description;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private clientType type;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
        name = "Clients_Investors",
        joinColumns = { @JoinColumn(name = "client_id") },
        inverseJoinColumns = { @JoinColumn(name = "investor_id") }
    )
    private List<Investor> investors;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Client)) {
            return false;
        }

        Client client = (Client) o;

        return client.getPhoneNumber().equals(this.phoneNumber)
                && client.getType().equals(this.getType())
                && client.getName().equals(this.getName())
                && client.getDescription().equals(this.getDescription())
                && client.getId().equals(this.getId())
                && client.getInvestors().equals(this.getInvestors());
    }
}
