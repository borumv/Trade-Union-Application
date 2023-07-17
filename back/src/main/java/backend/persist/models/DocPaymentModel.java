package backend.persist.models;

import backend.persist.entity.DocPayment;
import backend.persist.entity.PersonEntity;
import lombok.Data;

import java.util.Date;

/**
 * Model class representing a DocPayment.
 * <p>
 * This class is used to transfer data between different layers of the application.
 * @author Boris Vlasevsky
 */
@Data
public class DocPaymentModel {

    private int id;
    private Date startPay;
    private Date endPay;
    private String person;
    private String nameUnion;

    /**
     * Converts a DocPayment entity object to a DocPaymentModel object.
     *
     * @param docPayment the DocPayment object to be converted
     * @return the corresponding DocPaymentModel object
     */
    public static DocPaymentModel toModel(DocPayment docPayment) {

        DocPaymentModel docPaymentModel = new DocPaymentModel();
        docPaymentModel.setId(docPayment.getId());
        docPaymentModel.setStartPay(docPayment.getStartPay());
        docPaymentModel.setEndPay(docPayment.getEndPay());
        docPaymentModel.setPerson(docPayment.getPerson().getLastName());
        docPaymentModel.setNameUnion(docPayment.getTradeUnion().getNameUnion());
        return docPaymentModel;
    }

}