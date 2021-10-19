package seedu.address.model.tutorialgroup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class GroupType {

    public static final String MESSAGE_CONSTRAINTS = "GroupType can only either be OP1 or OP2";

    public final String value;
    /**
     * Constructs an {@code GroupType}.
     *
     * @param groupType A valid groupType.
     */
    public GroupType (String groupType) {
        requireNonNull(groupType);
        checkArgument(isValidGroupType(groupType), MESSAGE_CONSTRAINTS);
        value = groupType;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidGroupType(String test) {
        return test.equals("OP1") || test.equals("OP2");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupType // instanceof handles nulls
                && value.equals(((GroupType) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
