package classmate.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static classmate.logic.commands.CommandTestUtil.assertCommandFailure;
import static classmate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static classmate.logic.commands.CommandTestUtil.showStudentAtIndex;
import static classmate.testutil.TypicalStudents.getTypicalClassmate;

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
 * {@code DeleteCommand}.
 */
public class DeleteStudentCommandTest {

    private Model model = new ModelManager(getTypicalClassmate(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToDelete = model.getFilteredStudentList().get(TypicalIndexes.INDEX_FIRST_STUDENT.getZeroBased());
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(TypicalIndexes.INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);

        ModelManager expectedModel = new ModelManager(model.getClassmate(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        DeleteStudentCommand deleteCommand = new DeleteStudentCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStudentAtIndex(model, TypicalIndexes.INDEX_FIRST_STUDENT);


        Student studentToDelete = model.getFilteredStudentList().get(TypicalIndexes.INDEX_FIRST_STUDENT.getZeroBased());
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(TypicalIndexes.INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);

        Model expectedModel = new ModelManager(model.getClassmate(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);
        showNoStudent(expectedModel);

        assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, TypicalIndexes.INDEX_FIRST_STUDENT);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of ClassMATE list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getClassmate().getStudentList().size());

        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(outOfBoundIndex);

        assertCommandFailure(deleteStudentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {

        DeleteStudentCommand deleteFirstCommand = new DeleteStudentCommand(TypicalIndexes.INDEX_FIRST_STUDENT);
        DeleteStudentCommand deleteSecondCommand = new DeleteStudentCommand(TypicalIndexes.INDEX_SECOND_STUDENT);


        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteStudentCommand deleteFirstCommandCopy = new DeleteStudentCommand(TypicalIndexes.INDEX_FIRST_STUDENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStudent(Model model) {
        model.updateFilteredStudentList(p -> false);

        assertTrue(model.getFilteredStudentList().isEmpty());
    }
}
