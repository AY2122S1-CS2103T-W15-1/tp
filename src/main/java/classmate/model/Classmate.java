package classmate.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import classmate.model.student.Student;
import classmate.model.student.UniqueStudentList;
import javafx.collections.ObservableList;
import classmate.model.tutorialclass.TutorialClass;
import classmate.model.tutorialclass.UniqueTutorialClassList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class Classmate implements ReadOnlyClassmate {

    private final UniqueStudentList students;
    private final UniqueTutorialClassList tutorialClasses;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        tutorialClasses = new UniqueTutorialClassList();
    }

    public Classmate() {}

    /**
     * Creates an Classmate using the Students in the {@code toBeCopied}
     */
    public Classmate(ReadOnlyClassmate toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the tutorial class list with {@code tutorialClasses}.
     * {@code tutorialClasses} must not contain duplicate students.
     */
    public void setTutorialClasses(List<TutorialClass> tutorialClasses) {
        this.tutorialClasses.setTutorialClasses(tutorialClasses);
    }

    /**
     * Resets the existing data of this {@code Classmate} with {@code newData}.
     */
    public void resetData(ReadOnlyClassmate newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setTutorialClasses(newData.getTutorialClassList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in ClassMATE.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to ClassMATE.
     * The student must not already exist in ClassMATE.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in ClassMATE.
     * The student identity of {@code editedStudent} must not be the same as another existing student in ClassMATE.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code Classmate}.
     * {@code key} must exist in ClassMATE.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// tutorialclass-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in ClassMATE.
     */
    public boolean hasTutorialClass(TutorialClass tutorialClass) {
        requireNonNull(tutorialClass);
        return tutorialClasses.contains(tutorialClass);
    }

    /**
     * Adds a student to ClassMATE.
     * The student must not already exist in ClassMATE.
     */
    public void addTutorialClass(TutorialClass c) {
        tutorialClasses.add(c);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in ClassMATE.
     * The student identity of {@code editedStudent} must not be the same as another existing student in ClassMATE.
     */
    public void setTutorialClass(TutorialClass target, TutorialClass editedTutorialClass) {
        requireNonNull(editedTutorialClass);

        tutorialClasses.setTutorialClass(target, editedTutorialClass);
    }

    /**
     * Removes {@code key} from this {@code Classmate}.
     * {@code key} must exist in ClassMATE.
     */
    public void removeTutorialClass(TutorialClass key) {
        tutorialClasses.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<TutorialClass> getTutorialClassList() {
        return tutorialClasses.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Classmate // instanceof handles nulls
                && students.equals(((Classmate) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
