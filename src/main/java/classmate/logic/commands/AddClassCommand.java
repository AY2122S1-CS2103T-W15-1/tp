package classmate.logic.commands;

import static java.util.Objects.requireNonNull;

import classmate.logic.commands.exceptions.CommandException;
import classmate.logic.parser.CliSyntax;
import classmate.model.Model;
import classmate.model.tutorialclass.TutorialClass;

public class AddClassCommand extends Command {

    public static final String COMMAND_WORD = "addc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a class to Classmate. "
            + "Parameters: "
            + CliSyntax.PREFIX_CLASSCODE + "CLASSCODE "
            + CliSyntax.PREFIX_SCHEDULE + "SCHEDULE "
            + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_CLASSCODE + "G06 "
            + CliSyntax.PREFIX_SCHEDULE + "Tuesday 12:00pm to 2:00pm, Friday 12:00pm to 2:00pm"
            + CliSyntax.PREFIX_TAG + "Best class woohoo!";

    public static final String MESSAGE_SUCCESS = "New class added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLASS = "This class already exists in Classmate";

    private final TutorialClass toAdd;

    /**
     * Creates an AddClassCommand to add the specified {@code Student}
     */
    public AddClassCommand(TutorialClass tutorialClass) {
        requireNonNull(tutorialClass);
        toAdd = tutorialClass;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTutorialClass(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }

        model.addTutorialClass(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddClassCommand // instanceof handles nulls
                && toAdd.equals(((AddClassCommand) other).toAdd));
    }
}
