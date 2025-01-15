package com.groupa.mma_moriri.service;

import com.groupa.mma_moriri.model.Payment;
import com.groupa.mma_moriri.repo.PaymentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepo repo;

    public boolean addPayment(Payment payment) {
        Optional<Payment> pmtExist = Optional.ofNullable(repo.findByAppRefNo(payment.getAppRefNo()));

        if(pmtExist.isPresent()) {
            Payment pmtYes = pmtExist.get();
            pmtYes.setTotalAmtPaid(payment.getTotalAmtPaid());
            pmtYes.setReceiptPdf(payment.getReceiptPdf());
            pmtYes.setDateTime(payment.getDateTime());
            repo.save(pmtYes);
        } else {
            repo.save(payment);
        }

        return true;
    }

    public boolean updateReceipt(String refNo, byte[] receipt, double totAmtPaid) {
        boolean updated = false;
        Payment payment = repo.findByAppRefNo(refNo);

        if(payment != null) {
            payment.setReceiptPdf(receipt);
            payment.setTotalAmtPaid(totAmtPaid);
            repo.save(payment);
            updated = true;
        }

        return updated;
    }

    public boolean updateStatusByFanOff(String refNo ,Long fanOffId, boolean status,
                                        String descStatus) {
        boolean updated = false;
        Payment payment = repo.findByAppRefNo(refNo);

        if(payment != null) {
            payment.setFanOffId(fanOffId);
            payment.setStatus(status);
            payment.setDescStatus(descStatus);
            repo.save(payment);
            updated = true;
        }

        return updated;
    }

    public List<Payment> getAllPayments() {
        return repo.findAll();
    }

    public Payment findByRefNo(String appRefNo) {
        return repo.findByAppRefNo(appRefNo);
    }
    @Transactional
    public void deletePaymentsByAppRefNo(String appRefNo) {
        repo.deleteByAppRefNo(appRefNo);
    }
    public void makeRefNoNullById(String refNo) {
        Optional<Payment> pay = Optional.ofNullable(repo.findByAppRefNo(refNo));
        if(pay.isPresent()) {
            pay.get().setAppRefNo(null);
            repo.save(pay.get());
        }
    }
    public void makeIdNullByFanOffId(Long fanOffId) {
        List<Payment> pmts = repo.getByFanOffId(fanOffId);

        for(Payment pmt: pmts) {
            pmt.setFanOffId(null);
            repo.save(pmt);
        }
    }
}

