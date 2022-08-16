package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.utility.enums.PaymentType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "customer_payment")
public class CustomerPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

//    @JsonIdentityReference(alwaysAsId=true)
//    @OneToOne
//    @JoinColumn (name = "customer_name",nullable = false)
//    private Customer customer;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "date")
    private String date;


    @Column(name = "note")
    private String note;



}