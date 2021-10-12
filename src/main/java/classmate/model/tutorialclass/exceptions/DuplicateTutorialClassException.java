package classmate.model.tutorialclass.exceptions;

/**
 * Signals that the operation will result in duplicate Students (Students are considered duplicates if they have the
 * same identity).
 */
public class DuplicateTutorialClassException extends RuntimeException {
    public DuplicateTutorialClassException() {
        super("Operation would result in duplicate tutorial classes");
    }
}
