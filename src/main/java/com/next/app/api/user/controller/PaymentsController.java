package com.next.app.api.user.controller;

import com.next.app.api.user.entity.Payments;
import com.next.app.api.user.service.PaymentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payments Controller", description = "Payments Controller API")
@CrossOrigin(origins = "http://localhost")
public class PaymentsController {
    private final PaymentsService paymentsService;

    @Autowired
    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping
    @Operation(summary = "Find All Payments", description = "Find all payments in table.")
    public List<Payments> getAllPayments() {
        return paymentsService.getAllPayments();
    }

    @GetMapping("/{payment_id}")
    @Operation(summary = "Find Payment", description = "Find payment by payment_id.")
    public Payments getPaymentById(@PathVariable int id) {
        return paymentsService.getPaymentById(id);
    }

    @PostMapping
    @Operation(summary = "Create Payment", description = "Create new payment.")
    public Payments createPayment(@RequestBody Payments payment) {
        return paymentsService.createPayment(payment);
    }

    @PutMapping("/{payment_id}")
    @Operation(summary = "Update Payment", description = "Update current payment information.")
    public Payments updatePayment(@PathVariable int paymentId, @RequestBody Payments payment) {
        return paymentsService.updatePayment(paymentId, payment);
    }

    @DeleteMapping("/{payment_id}")
    @Operation(summary = "Delete Payment", description = "Delete current payment information.")
    public void deletePayment(@PathVariable int paymentId) {
        paymentsService.deletePayment(paymentId);
    }

}
