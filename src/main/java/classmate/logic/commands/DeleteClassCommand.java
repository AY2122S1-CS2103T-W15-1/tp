package classmate.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import classmate.commons.core.Messages;
import classmate.commons.core.index.Index;
import classmate.logic.commands.exceptions.CommandException;
import classmate.model.Model;
import classmate.model.tutorialclass.TutorialClass;

public class DeleteClassCommand extends Command {

    public static final String COMMAND_WORD = "deletec";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the class identified by the index number used in the displayed class list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";

    private final Index targetIndex;

    /**
     * Constructor for DeleteClassCommand
     *
     * @param targetIndex Index to be deleted.
     */
    public DeleteClassCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TutorialClass> lastShownList = model.getFliteredTutorialClassList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        TutorialClass classToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTutorialClass(classToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, classToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteClassCommand
                && targetIndex.equals(((DeleteClassCommand) other).targetIndex));
    }
}
