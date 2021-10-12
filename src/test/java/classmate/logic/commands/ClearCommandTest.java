package classmate.logic.commands;

import static classmate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static classmate.testutil.TypicalStudents.getTypicalClassmate;

import org.junit.jupiter.api.Test;

import classmate.model.Classmate;
import classmate.model.Model;
import classmate.model.ModelManager;
import classmate.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyClassmate_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyClassmate_success() {
        Model model = new ModelManager(getTypicalClassmate(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalClassmate(), new UserPrefs());
        expectedModel.setClassmate(new Classmate());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
