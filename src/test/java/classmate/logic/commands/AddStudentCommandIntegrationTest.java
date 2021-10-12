package classmate.logic.commands;

import static classmate.logic.commands.CommandTestUtil.assertCommandFailure;
import static classmate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static classmate.testutil.TypicalStudents.getTypicalClassmate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classmate.model.Model;
import classmate.model.ModelManager;
import classmate.model.UserPrefs;
import classmate.model.student.Student;
import classmate.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddStudentCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClassmate(), new UserPrefs());
    }

    @Test
    public void execute_newStudent_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getClassmate(), new UserPrefs());
        expectedModel.addStudent(validStudent);

        assertCommandSuccess(new AddStudentCommand(validStudent), model,
                String.format(AddStudentCommand.MESSAGE_SUCCESS, validStudent), expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student studentInList = model.getClassmate().getStudentList().get(0);
        assertCommandFailure(new AddStudentCommand(studentInList), model, AddStudentCommand.MESSAGE_DUPLICATE_STUDENT);

    }

}
