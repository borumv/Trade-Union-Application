package backend.exceptions;

/**
 * Signals that a DocMember object with the specified number could not be found.
 * <p>
 * This exception is thrown when an operation expects the existence of a DocMember instance but fails to locate it.
 */
public class DocMemberNotFoundException extends RuntimeException {

    /**
     * Constructs a new DocMemberNotFoundException with the given number.
     *
     * @param num the number of the DocMember instance that was not found
     */
    public DocMemberNotFoundException(int num) {

        super("DocMem with num = " + num + " not found");
    }

}