package classmate.model;

import static classmate.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static classmate.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static classmate.testutil.TypicalStudents.ALICE;
import static classmate.testutil.TypicalStudents.getTypicalClassmate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import classmate.model.student.Student;
import classmate.model.student.exceptions.DuplicateStudentException;
import classmate.model.tutorialclass.TutorialClass;
import classmate.testutil.Assert;
import classmate.testutil.StudentBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClassmateTest {

    private final Classmate classmate = new Classmate();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), classmate.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> classmate.resetData(null));
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
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        ClassmateStub newData = new ClassmateStub(newStudents, new ArrayList<>());

        Assert.assertThrows(DuplicateStudentException.class, () -> classmate.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> classmate.hasStudent(null));
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
    public void hasStudent_studentWithSameIdentityFieldsInClassmate_returnsTrue() {
        classmate.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(classmate.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> classmate.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyClassmate whose students list can violate interface constraints.
     */
    private static class ClassmateStub implements ReadOnlyClassmate {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<TutorialClass> tutorialClasses = FXCollections.observableArrayList();

        ClassmateStub(Collection<Student> students, Collection<TutorialClass> tutorialClasses) {
            this.students.setAll(students);
            this.tutorialClasses.setAll(tutorialClasses);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<TutorialClass> getTutorialClassList() {
            return tutorialClasses;
        }
    }

}
