package com.next.app.api.user.repository;

import com.next.app.api.user.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsReopsitory extends JpaRepository<Payments, Long> {



}
