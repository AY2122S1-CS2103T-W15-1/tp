package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_G01_OP1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_G01_OP2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_G06_OP1;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalClassmate.getTypicalClassmate;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Classmate;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class AddStudentToGroupCommandTest {

    private Model model = new ModelManager(getTypicalClassmate(), new UserPrefs());

    @Test
    public void execute_tutorialGroupSpecifiedUnfilteredList_success() {
        Student editedStudent = new StudentBuilder(ALICE).withTutorialGroups(VALID_GROUP_G01_OP1).build();
        AddStudentToGroupCommand addStudentToGroupCommand = new AddStudentToGroupCommand(INDEX_FIRST_STUDENT,
                VALID_GROUP_G01_OP2);

        String expectedMessage = String.format(AddStudentToGroupCommand.MESSAGE_SUCCESS, VALID_GROUP_G01_OP2);

        Model expectedModel = new ModelManager(new Classmate(model.getClassmate()), new UserPrefs());

        assertCommandSuccess(addStudentToGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withTutorialGroups(VALID_GROUP_G01_OP1).build();

        AddStudentToGroupCommand addStudentToGroupCommand = new AddStudentToGroupCommand(INDEX_FIRST_STUDENT,
                VALID_GROUP_G01_OP2);

        String expectedMessage = String.format(AddStudentToGroupCommand.MESSAGE_SUCCESS, VALID_GROUP_G01_OP2);

        Model expectedModel = new ModelManager(new Classmate(model.getClassmate()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(addStudentToGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AddStudentToGroupCommand addStudentToGroupCommand = new AddStudentToGroupCommand(outOfBoundIndex,
                VALID_GROUP_G06_OP1);

        assertCommandFailure(addStudentToGroupCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
}
