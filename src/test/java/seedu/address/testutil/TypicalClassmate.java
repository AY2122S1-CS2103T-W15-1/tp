package seedu.address.testutil;

import java.util.List;

import seedu.address.model.Classmate;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;


/**
 * A utility class containing a list of {@code TutorialClass} objects to be used in tests.
 */
public class TypicalClassmate {

    private static final List<TutorialClass> TYPICAL_TUTORIAL_CLASS_LIST =
            TypicalTutorialClasses.getTypicalTutorialClasses();
    private static final List<Student> TYPICAL_STUDENT_LIST = TypicalStudents.getTypicalStudents();
    private static final List<TutorialGroup> TYPICAL_TUTORIAL_GROUP_LIST =
            TypicalTutorialGroups.getTypicalTutorialGroups();

    // Prevent instantiation
    private TypicalClassmate() {}

    /**
     * Returns an {@code Classmate} with all the typical students.
     */
    public static Classmate getTypicalClassmate() {
        Classmate ab = new Classmate();
        for (TutorialClass tutorialClass : TYPICAL_TUTORIAL_CLASS_LIST) {
            ab.addTutorialClass(tutorialClass);
        }
        for (Student student : TYPICAL_STUDENT_LIST) {
            ab.addStudent(student);
        }
        for (TutorialGroup tutorialGroup : TYPICAL_TUTORIAL_GROUP_LIST) {
            ab.addTutorialGroup(tutorialGroup);
        }
        return ab;
    }


}
