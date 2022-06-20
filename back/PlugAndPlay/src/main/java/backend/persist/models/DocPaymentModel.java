package backend.persist.models;

import backend.persist.entity.DocPayment;
import backend.persist.entity.PersonEntity;
import lombok.Data;

import java.util.Date;

@Data
public class DocPaymentModel {
    int id;
    Date startPay;
    Date endPay;
    String person;
    String nameUnion;

    public static DocPaymentModel toModel(DocPayment docPayment){
        DocPaymentModel docPaymentModel = new DocPaymentModel();
        docPaymentModel.setId(docPayment.getId());
        docPaymentModel.setStartPay(docPayment.getStartPay());
        docPaymentModel.setEndPay(docPayment.getEndPay());
        docPaymentModel.setPerson(docPayment.getPerson().getLastName());
        docPaymentModel.setNameUnion(docPayment.getTradeUnion().getNameUnion());
        return docPaymentModel;

    }

}
