package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.entity.Payment;
import com.jsframe.wadizit.repository.PaymentRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
@Log
public class PaymentService {
    @Autowired
    private PaymentRepository pRepo;

    public void save(String oNum, String oName, Date date, Member member) {
        Payment payment = new Payment();

        try {
            payment.setOrderNum(oNum);
            payment.setMemberNum(member);

            pRepo.save(payment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
