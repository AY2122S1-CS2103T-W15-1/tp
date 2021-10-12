package classmate.logic.commands;

import static java.util.Objects.requireNonNull;

import classmate.model.Model;


/**
 * Lists all classes in ClassMate to the user.
 */
public class ListClassCommand extends Command {

    public static final String COMMAND_WORD = "listc";

    public static final String MESSAGE_SUCCESS = "Listed all tutorial classes";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updaateFilteredTutorialClassList(Model.PREDICATE_SHOW_ALL_CLASSES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
