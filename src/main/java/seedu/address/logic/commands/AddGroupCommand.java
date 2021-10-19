package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.HashSet;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialclass.Schedule;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class AddGroupCommand extends Command {
    public static final String COMMAND_WORD = "addcg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a group to a class to Classmate. "
            + "Parameters: "
            + PREFIX_GROUPNAME + "GROUPNAME "
            + PREFIX_CLASSCODE + "CLASSCODE "
            + PREFIX_TYPE + "TYPE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUPNAME + "1 "
            + PREFIX_CLASSCODE + "G06 "
            + PREFIX_TYPE + "OP1 ";

    public static final String MESSAGE_SUCCESS = "New group added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLASS = "This group already exists in Classmate";
    public static final String MESSAGE_CLASS_NOT_EXIST = "The class does not exist in Classmate";

    private final TutorialGroup toAdd;
    private final TutorialClass toAddTutorialClass;

    /**
     * Creates an AddClassCommand to add the specified {@code Student}
     */
    public AddGroupCommand(TutorialGroup tutorialGroup) {
        requireNonNull(tutorialGroup);
        toAdd = tutorialGroup;
        toAddTutorialClass = new TutorialClass(tutorialGroup.getClassCode(), new Schedule("dummy"), new HashSet<Tag>());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // check if tutorial class already exists in ClassMATE
        if (!model.hasTutorialClass(toAddTutorialClass)) {
            throw new CommandException(MESSAGE_CLASS_NOT_EXIST);
        }

        if (model.hasTutorialGroup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }

        model.addTutorialGroup(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddGroupCommand // instanceof handles nulls
                && toAdd.equals(((AddGroupCommand) other).toAdd));
    }
}
