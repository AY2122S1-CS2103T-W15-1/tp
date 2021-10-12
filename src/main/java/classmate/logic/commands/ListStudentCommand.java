package classmate.logic.commands;

import static java.util.Objects.requireNonNull;

import classmate.model.Model;

/**
 * Lists all students in ClassMATE to the user.
 */
public class ListStudentCommand extends Command {

    public static final String COMMAND_WORD = "liststu";

    public static final String MESSAGE_SUCCESS = "Listed all students";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
