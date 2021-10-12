package classmate.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import classmate.commons.core.Messages;
import classmate.commons.core.index.Index;
import classmate.logic.commands.exceptions.CommandException;
import classmate.model.Model;
import classmate.model.student.Student;

/**
 * Views a student identified using it's displayed index from ClassMATE.
 */
public class ViewStudentCommand extends Command {

    public static final String COMMAND_WORD = "viewstu";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_STUDENT_SUCCESS = "Viewed Student: %1$s";

    private final Index targetIndex;

    public ViewStudentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToView = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_VIEW_STUDENT_SUCCESS, studentToView), false, true,
                false, studentToView);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewStudentCommand // instanceof handles nulls
                && targetIndex.equals(((ViewStudentCommand) other).targetIndex)); // state check
    }
}
