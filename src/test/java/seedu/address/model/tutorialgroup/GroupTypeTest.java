package seedu.address.model.tutorialgroup;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GroupTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupType(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidGroupType = "";
        assertThrows(IllegalArgumentException.class, () -> new GroupType(invalidGroupType));
    }

    @Test
    public void isValidName() {
        // null group name
        assertThrows(NullPointerException.class, () -> GroupType.isValidGroupType(null));

        // invalid group name
        assertFalse(GroupType.isValidGroupType("")); // empty string
        assertFalse(GroupType.isValidGroupType(" ")); // spaces only
        assertFalse(GroupType.isValidGroupType("^")); // only non-alphanumeric characters
        assertFalse(GroupType.isValidGroupType("peter*")); // contains non-alphanumeric characters
        assertFalse(GroupType.isValidGroupType("peter")); // contains alphabets
        assertFalse(GroupType.isValidGroupType("12")); // contains more than one digit
        assertFalse(GroupType.isValidGroupType("OP3")); // invalid group type

        // valid group name
        assertTrue(GroupType.isValidGroupType("OP1")); // alphabets only
        assertTrue(GroupType.isValidGroupType("OP2")); // numbers only
    }
}