package info.maxbehr.spring.restclient.producer;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException(Exception e) {
        super(e);
    }
}
