package classmate.logic.parser;

import classmate.commons.core.Messages;
import classmate.commons.core.index.Index;
import classmate.logic.commands.DeleteClassCommand;
import classmate.logic.parser.exceptions.ParseException;

public class DeleteClassCommandParser implements Parser<DeleteClassCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteClassCommand
     * and returns a DeleteClassCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteClassCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteClassCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE), pe);
        }
    }
}
