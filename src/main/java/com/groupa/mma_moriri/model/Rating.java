package com.groupa.mma_moriri.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue
    @Column(name = "rate_id")
    private Long rateId;
    @Column(name = "cust_id")
    private Long customerId;
    @Column(name = "stylist_id")
    private  Long stylistId;
    private Integer rate;

    public Rating( Long customerId, Long stylistId, Integer rate) {
        this.customerId = customerId;
        this.stylistId = stylistId;
        this.rate = rate;
    }
}
