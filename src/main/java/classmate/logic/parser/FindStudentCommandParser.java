package classmate.logic.parser;

import java.util.Arrays;

import classmate.commons.core.Messages;
import classmate.logic.commands.FindStudentCommand;
import classmate.logic.parser.exceptions.ParseException;
import classmate.model.student.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindStudentCommandParser implements Parser<FindStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindStudentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindStudentCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindStudentCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
