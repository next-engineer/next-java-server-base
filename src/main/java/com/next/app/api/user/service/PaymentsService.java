package com.next.app.api.user.service;

import com.next.app.api.user.entity.Payments;
import com.next.app.api.user.repository.PaymentsReopsitory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PaymentsService {

    @Autowired
    private PaymentsReopsitory paymentsReopsitory;

    // Read All Payments
    public List<Payments> getAllPayments() {
        return paymentsReopsitory.findAll();
    }

    // Find Payment by payment_id
    public Payments getPaymentById(int paymentId) {
        return paymentsReopsitory.findById((long) paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + paymentId));
    }

    // Create Payment
    public Payments createPayment(Payments payments) {
        return paymentsReopsitory.save(payments);
    }

    // Edit Payment
    public Payments updatePayment(int id, Payments updatePayment) {
        Payments payment = getPaymentById(id);
        payment.setClientId(updatePayment.getClientId());
        payment.setAmount(updatePayment.getAmount());
        payment.setMethod(updatePayment.getMethod());

        return paymentsReopsitory.save(payment)
    }

    // Delete Payment
    public void deletePayment(int id) {
        paymentsReopsitory.deleteById((long) id);
    }

}
