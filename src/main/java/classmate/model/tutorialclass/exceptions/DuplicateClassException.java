package classmate.model.tutorialclass.exceptions;

public class DuplicateClassException extends RuntimeException {
    public DuplicateClassException() {
        super("Operation would result in duplicate classes");
    }
}
