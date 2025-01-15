package com.groupa.mma_moriri.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private Long cust_id;
    private String firstname;
    private String lastname;
    private long phone_number;
    @Column(unique = true)
    private String username;
    private String password;

    public Customer(String firstname, String lastname, long phone_number, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone_number = phone_number;
        this.username = username;
        this.password = password;
    }
}
