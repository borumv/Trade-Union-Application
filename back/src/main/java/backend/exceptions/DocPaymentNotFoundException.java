package backend.exceptions;

/**
 * Exception class indicating that a specific DocPayment instance was not found.
 * <p>
 * This exception is typically thrown when attempting to retrieve or manipulate a non-existent DocPayment object.]
 *
 * @author Boris Vlasevsky
 */
public class DocPaymentNotFoundException extends RuntimeException {

    /**
     * Constructs a new DocPaymentNotFoundException with the given ID.
     *
     * @param id the ID of the DocPayment instance that was not found
     */
    public DocPaymentNotFoundException(int id) {

        super("DocPayment id not found: " + id);
    }
}