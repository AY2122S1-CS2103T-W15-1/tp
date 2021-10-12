package classmate.logic.parser;

import static classmate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static classmate.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static java.util.Objects.requireNonNull;

import classmate.commons.core.index.Index;
import classmate.commons.exceptions.IllegalValueException;
import classmate.logic.commands.ClassCodeCommand;
import classmate.logic.parser.exceptions.ParseException;
import classmate.model.student.ClassCode;

public class ClassCodeCommandParser implements Parser<ClassCodeCommand> {

    @Override
    public ClassCodeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_CLASSCODE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ClassCodeCommand.MESSAGE_USAGE), ive);
        }

        String classCode = argMultimap.getValue(PREFIX_CLASSCODE).orElse("");

        return new ClassCodeCommand(index, new ClassCode(classCode));
    }

}
