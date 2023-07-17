package backend.exceptions;

/**
 * Exception class indicating that a specific TradeUnion instance was not found.
 * <p>
 * This exception is typically thrown when attempting to retrieve or manipulate a non-existent TradeUnion object.
 */
public class TradeUnionNotFoundException extends RuntimeException {

    /**
     * Constructs a new TradeUnionNotFoundException with the given ID.
     *
     * @param id the ID of the TradeUnion instance that was not found
     */
    public TradeUnionNotFoundException(int id) {

        super("Tradeunion id not found: " + id);
    }
}