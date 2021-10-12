package classmate.logic.commands;

import static classmate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static classmate.logic.commands.CommandTestUtil.showStudentAtIndex;
import static classmate.testutil.TypicalStudents.getTypicalClassmate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classmate.model.Model;
import classmate.model.ModelManager;
import classmate.model.UserPrefs;
import classmate.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListStudentCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClassmate(), new UserPrefs());
        expectedModel = new ModelManager(model.getClassmate(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListStudentCommand(), model, ListStudentCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showStudentAtIndex(model, TypicalIndexes.INDEX_FIRST_STUDENT);
        assertCommandSuccess(new ListStudentCommand(), model, ListStudentCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
