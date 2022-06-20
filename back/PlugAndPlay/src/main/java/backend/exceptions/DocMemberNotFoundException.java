package backend.exceptions;

public class DocMemberNotFoundException extends RuntimeException{
    public DocMemberNotFoundException(int num){
        super("DocMem with num = " + num + " not found");
    }

}
