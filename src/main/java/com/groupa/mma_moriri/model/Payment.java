package com.groupa.mma_moriri.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue
    @Column(name = "pmt_id")
    private Long pmtId;
    @Column(name = "receipt_pdf")
    private byte[] receiptPdf;
    private boolean status;
    @Column(name = "desc_status")
    private String descStatus;
    @Column(name = "fan_off_id")
    private Long fanOffId;
    @Column(name = "app_ref_no", nullable = true)
    private String appRefNo;
    @Column(name = "total_amt_paid")
    private Double totalAmtPaid;
    @Column(name = "date_time")
    private Timestamp dateTime;

    public Payment(byte[] receiptPdf, boolean status, String descStatus,
                   Long fanOffId, String appRefNo,
                   Double totalAmtPaid, Timestamp dateTime) {
        this.receiptPdf = receiptPdf;
        this.status = status;
        this.descStatus = descStatus;
        this.fanOffId = fanOffId;
        this.appRefNo = appRefNo;
        this.totalAmtPaid = totalAmtPaid;
        this.dateTime = dateTime;
    }

    public Payment(String appRefNo) {
        this.appRefNo = appRefNo;
    }

    public Payment(boolean status, String statusDesc, Long fanOffId, String appRefNo) {
        this.status = status;
        this.descStatus = statusDesc;
        this.fanOffId = fanOffId;
        this.appRefNo = appRefNo;
    }
}
