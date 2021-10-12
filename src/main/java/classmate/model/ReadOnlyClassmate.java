package classmate.model;

import classmate.model.student.Student;
import javafx.collections.ObservableList;
import classmate.model.tutorialclass.TutorialClass;
//import TutorialClass;

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
