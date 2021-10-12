package classmate.logic.parser;

import classmate.commons.core.Messages;
import classmate.commons.core.index.Index;
import classmate.logic.commands.ViewStudentCommand;
import classmate.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewStudentCommandParser implements Parser<ViewStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewStudentCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewStudentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ViewStudentCommand.MESSAGE_USAGE), pe);
        }
    }

}
