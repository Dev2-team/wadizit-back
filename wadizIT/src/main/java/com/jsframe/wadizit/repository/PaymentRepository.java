package com.jsframe.wadizit.repository;

import com.jsframe.wadizit.entity.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
