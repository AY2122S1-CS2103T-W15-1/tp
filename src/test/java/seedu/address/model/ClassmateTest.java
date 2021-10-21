package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalClassmate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.StudentBuilder;

public class ClassmateTest {

    private final Classmate classmate = new Classmate();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), classmate.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> classmate.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyClassmate_replacesData() {
        Classmate newData = getTypicalClassmate();
        classmate.resetData(newData);
        assertEquals(newData, classmate);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        //TODO: Add sample tutorialClasses to tests. Replace new ArrayList in newData constructor line.
        Student Alice = new StudentBuilder(ALICE).build();
        List<Student> newStudents = Arrays.asList(ALICE, Alice);
        ClassmateStub newData = new ClassmateStub(newStudents, new ArrayList<>(), new ArrayList<>());

        assertThrows(DuplicateStudentException.class, () -> classmate.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> classmate.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInClassmate_returnsFalse() {
        assertFalse(classmate.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInClassmate_returnsTrue() {
        classmate.addStudent(ALICE);
        assertTrue(classmate.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithDifferentAddressAndTagFieldsInClassmate_returnsFalse() {
        classmate.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertFalse(classmate.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> classmate.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyClassmate whose students list can violate interface constraints.
     */
    private static class ClassmateStub implements ReadOnlyClassmate {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<TutorialClass> tutorialClasses = FXCollections.observableArrayList();
        private final ObservableList<TutorialGroup> tutorialGroups = FXCollections.observableArrayList();

        ClassmateStub(Collection<Student> students, Collection<TutorialClass> tutorialClasses,
                      Collection<TutorialGroup> tutorialGroups) {
            this.students.setAll(students);
            this.tutorialClasses.setAll(tutorialClasses);
            this.tutorialGroups.setAll(tutorialGroups);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<TutorialClass> getTutorialClassList() {
            return tutorialClasses;
        }

        @Override
        public ObservableList<TutorialGroup> getTutorialGroupList() {
            return tutorialGroups;
        }
    }

}
