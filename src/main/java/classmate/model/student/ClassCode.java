package classmate.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Student's class code in ClassMATE.
 * Guarantees: immutable.
 */

public class ClassCode {

    public static final String MESSAGE_CONSTRAINTS = "ClassCode can take any values, and it should not be blank";


    public final String value;

    /**
     * Constructs an {@code ClassCode}.
     *
     * @param classCode A valid classcode.
     */
    public ClassCode (String classCode) {
        requireNonNull(classCode);
        value = classCode;
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassCode // instanceof handles nulls
                && value.equals(((ClassCode) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
