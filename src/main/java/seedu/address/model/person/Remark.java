package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Remark {
    public static final String MESSAGE_CONSTRAINTS =
            "Remarks should only contain alphanumeric characters and spaces, and it should not be blank";

    public final String remark;

    public Remark(String remark) {
        requireNonNull(remark);
        this.remark = remark;
    }

    @Override
    public String toString() {
        return remark;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other instanceof Remark) {
            return remark.equals(((Remark) other).remark);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return remark.hashCode();
    }

}
