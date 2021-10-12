package classmate.logic.parser;

import classmate.commons.core.Messages;
import classmate.commons.core.index.Index;
import classmate.logic.commands.DeleteStudentCommand;
import classmate.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteStudentCommandParser implements Parser<DeleteStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteStudentCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteStudentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentCommand.MESSAGE_USAGE), pe);
        }
    }

}
