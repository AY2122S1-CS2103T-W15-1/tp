package classmate.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static classmate.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import classmate.logic.commands.CommandTestUtil;
import classmate.testutil.Assert;
import classmate.testutil.StudentBuilder;
import classmate.testutil.TypicalStudents;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
    }

    @Test
    public void isSameStudent() {
        // same object -> returns true
        Assertions.assertTrue(TypicalStudents.ALICE.isSameStudent(TypicalStudents.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalStudents.ALICE.isSameStudent(null));

        // same name, all other attributes different -> returns true
        Student editedAlice = new StudentBuilder(TypicalStudents.ALICE).withPhone(CommandTestUtil.VALID_PHONE_BOB).withEmail(CommandTestUtil.VALID_EMAIL_BOB)
                .withAddress(CommandTestUtil.VALID_ADDRESS_BOB).withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        Assertions.assertTrue(TypicalStudents.ALICE.isSameStudent(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withName(CommandTestUtil.VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.isSameStudent(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Student editedBob = new StudentBuilder(TypicalStudents.BOB).withName(CommandTestUtil.VALID_NAME_BOB.toLowerCase()).build();
        Assertions.assertFalse(TypicalStudents.BOB.isSameStudent(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = CommandTestUtil.VALID_NAME_BOB + " ";
        editedBob = new StudentBuilder(TypicalStudents.BOB).withName(nameWithTrailingSpaces).build();
        Assertions.assertFalse(TypicalStudents.BOB.isSameStudent(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(TypicalStudents.ALICE).build();
        Assertions.assertTrue(TypicalStudents.ALICE.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(TypicalStudents.ALICE.equals(TypicalStudents.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalStudents.ALICE.equals(null));

        // different type -> returns false
        Assertions.assertFalse(TypicalStudents.ALICE.equals(5));

        // different student -> returns false
        Assertions.assertFalse(TypicalStudents.ALICE.equals(TypicalStudents.BOB));

        // different name -> returns false
        Student editedAlice = new StudentBuilder(TypicalStudents.ALICE).withName(CommandTestUtil.VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withEmail(CommandTestUtil.VALID_EMAIL_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));
    }
}
