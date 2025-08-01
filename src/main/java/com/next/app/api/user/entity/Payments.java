package com.next.app.api.user.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "payments")
public class Payments {

    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = IDENTITY)
    private int paymentId;

    @Column(name = "client_id", nullable = false)
    private int clientId;

    @Column(name = "amount", precision = 12, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "method", nullable = false)
    private String method;

    @Column(name = "paid_at")
    private java.time.LocalDateTime paidAt;

    public int validateAmount(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO);
    }

    @PrePersist
    protected void onPaid() {
        paidAt = java.time.LocalDateTime.now();
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getClientId() {
        return clientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public java.time.LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setMethod(String method) {
        this.method = method;
    }



}
