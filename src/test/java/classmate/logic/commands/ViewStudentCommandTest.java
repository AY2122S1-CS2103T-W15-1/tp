package classmate.logic.commands;

import static classmate.logic.commands.CommandTestUtil.assertCommandFailure;
import static classmate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static classmate.logic.commands.CommandTestUtil.showStudentAtIndex;
import static classmate.testutil.TypicalStudents.getTypicalClassmate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classmate.commons.core.Messages;
import classmate.commons.core.index.Index;
import classmate.model.Model;
import classmate.model.ModelManager;
import classmate.model.UserPrefs;
import classmate.model.student.Student;
import classmate.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewStudentCommand}.
 */
class ViewStudentCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClassmate(), new UserPrefs());
        expectedModel = new ModelManager(model.getClassmate(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToView = model.getFilteredStudentList().get(TypicalIndexes.INDEX_FIRST_STUDENT.getZeroBased());
        ViewStudentCommand viewStudentCommand = new ViewStudentCommand(TypicalIndexes.INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(ViewStudentCommand.MESSAGE_VIEW_STUDENT_SUCCESS, studentToView);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, true,
                false, studentToView);

        assertCommandSuccess(viewStudentCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ViewStudentCommand viewStudentCommand = new ViewStudentCommand(outOfBoundIndex);

        assertCommandFailure(viewStudentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStudentAtIndex(model, TypicalIndexes.INDEX_FIRST_STUDENT);


        Student studentToView = model.getFilteredStudentList().get(TypicalIndexes.INDEX_FIRST_STUDENT.getZeroBased());
        ViewStudentCommand viewStudentCommand = new ViewStudentCommand(TypicalIndexes.INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(ViewStudentCommand.MESSAGE_VIEW_STUDENT_SUCCESS, studentToView);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, true,
                false, studentToView);
        showStudentAtIndex(expectedModel, TypicalIndexes.INDEX_FIRST_STUDENT);

        assertCommandSuccess(viewStudentCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, TypicalIndexes.INDEX_FIRST_STUDENT);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of ClassMATE list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getClassmate().getStudentList().size());

        ViewStudentCommand viewStudentCommand = new ViewStudentCommand(outOfBoundIndex);

        assertCommandFailure(viewStudentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {

        ViewStudentCommand viewFirstCommand = new ViewStudentCommand(TypicalIndexes.INDEX_FIRST_STUDENT);
        ViewStudentCommand viewSecondCommand = new ViewStudentCommand(TypicalIndexes.INDEX_SECOND_STUDENT);


        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewStudentCommand viewFirstCommandCopy = new ViewStudentCommand(TypicalIndexes.INDEX_FIRST_STUDENT);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
