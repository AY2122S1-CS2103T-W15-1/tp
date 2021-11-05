package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClassCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassCode(null));
    }

    @Test
    public void constructor_invalidClassCode_throwsIllegalArgumentException() {
        String invalidClassCode = "";
        assertThrows(IllegalArgumentException.class, () -> new ClassCode(invalidClassCode));
    }

    @Test
    public void isValidClassCode() {
        // null classCode
        assertThrows(NullPointerException.class, () -> ClassCode.isValidClassCode(null));

        // invalid ClassCode
        assertFalse(ClassCode.isValidClassCode("")); // empty string
        assertFalse(ClassCode.isValidClassCode("Today")); // must start with G, followed by 2 digits
        assertFalse(ClassCode.isValidClassCode("T99")); // first character is G/g
        assertFalse(ClassCode.isValidClassCode("g001")); // must have 3 digits

        // valid ClassCode
        assertTrue(ClassCode.isValidClassCode("G00")); // Upper-case G
        assertTrue(ClassCode.isValidClassCode("g99")); // Lower-case G
    }
}
