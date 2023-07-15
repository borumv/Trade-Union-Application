package backend.services;

import backend.controllers.BaseRestController;
import backend.exceptions.DocPaymentNotFoundException;
import backend.persist.entity.DocPayment;
import backend.persist.entity.PersonEntity;
import backend.persist.models.DocPaymentModel;
import backend.persist.models.QuantityPayTradeUnion;
import backend.persist.repositories.DocPaymentRepo;
import backend.persist.repositories.QuantityPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DocPaymentService extends BaseRestController {

    @Autowired
    DocPaymentRepo paymentRepo;

    @Autowired
    QuantityPayment qantityPaymentRepo;

    public List<DocPayment> getAll() {

        return paymentRepo.findAll();
    }

    public List<QuantityPayTradeUnion> getPayCountOfTradeUnion() {

        return qantityPaymentRepo.getQuantityPay();
    }

    public List<QuantityPayTradeUnion> getDontPaied() {

        return qantityPaymentRepo.getDontPaied();
    }

    public DocPayment findById(int id) {

        return paymentRepo.findById(id)
                .orElseThrow(() -> new DocPaymentNotFoundException(id));
    }

    public DocPayment createDocPayment(DocPayment docPayment) {

        return paymentRepo.save(docPayment);
    }

    public DocPayment update(int paymentId) {

        DocPayment docPayment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new DocPaymentNotFoundException(paymentId));
        docPayment.setEndPay(new Date());
        docPayment.setId(paymentId);
        return paymentRepo.save(docPayment);
    }

    public DocPayment deletePayment(int id) {

        DocPayment docPayment = new DocPayment();
        paymentRepo.deleteById(id);
        return docPayment;
    }

    public  void deleteByPersonId(int id){
        paymentRepo.deleteByPersonId(id);
    }
}
