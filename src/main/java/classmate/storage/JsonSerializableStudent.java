package classmate.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import classmate.commons.exceptions.IllegalValueException;
import classmate.model.Classmate;
import classmate.model.ReadOnlyClassmate;
import classmate.model.student.Student;
import classmate.model.tutorialclass.TutorialClass;

/**
 * An Immutable Classmate that is serializable to JSON format.
 */
@JsonRootName(value = "classmate")
class JsonSerializableStudent {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_TUTORIAL_CLASS =
            "Tutorial Classes list contains duplicate tutorial class(es).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedTutorialClass> tutorialClasses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableStudent} with the given students.
     */
    @JsonCreator
    public JsonSerializableStudent(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                   @JsonProperty("tutorialClasses") List<JsonAdaptedTutorialClass> tutorialClasses) {
        this.students.addAll(students);
        this.tutorialClasses.addAll(tutorialClasses);
    }

    /**
     * Converts a given {@code ReadOnlyClassmate} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableStudent}.
     */
    public JsonSerializableStudent(ReadOnlyClassmate source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        tutorialClasses.addAll(source
                .getTutorialClassList()
                .stream()
                .map(JsonAdaptedTutorialClass::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this ClassMATE into the model's {@code Classmate} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Classmate toModelType() throws IllegalValueException {
        Classmate classmate = new Classmate();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (classmate.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            classmate.addStudent(student);
        }
        for (JsonAdaptedTutorialClass jsonAdaptedTutorialClass : tutorialClasses) {
            TutorialClass tutorialClass = jsonAdaptedTutorialClass.toModelType();
            if (classmate.hasTutorialClass(tutorialClass)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TUTORIAL_CLASS);
            }
            classmate.addTutorialClass(tutorialClass);
        }
        return classmate;
    }

}
