package classmate.logic.commands;

import static classmate.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import classmate.model.Model;
import classmate.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT, false, false, true, null);
        CommandTestUtil.assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
