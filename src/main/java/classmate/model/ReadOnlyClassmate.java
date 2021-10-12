package classmate.model;

import classmate.model.student.Student;
import classmate.model.tutorialclass.TutorialClass; //import TutorialClass;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of ClassMATE
 */
public interface ReadOnlyClassmate {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of the tutorial classes list.
     * This list will not contain any duplicate tutorial classes.
     */
    ObservableList<TutorialClass> getTutorialClassList();

}
