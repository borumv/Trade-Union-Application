package backend.exceptions;

public class DocPaymentNotFoundException extends RuntimeException {
    public DocPaymentNotFoundException(int id){
        super("DocPayment id not found : " + id);
    }
}
