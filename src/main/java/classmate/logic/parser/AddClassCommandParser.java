package classmate.logic.parser;

import java.util.Set;
import java.util.stream.Stream;

import classmate.commons.core.Messages;
import classmate.logic.commands.AddClassCommand;
import classmate.logic.parser.exceptions.ParseException;
import classmate.model.student.ClassCode;
import classmate.model.tag.Tag;
import classmate.model.tutorialclass.Schedule;
import classmate.model.tutorialclass.TutorialClass;

public class AddClassCommandParser implements Parser<AddClassCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddClassCommand
     * and returns an AddClassCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddClassCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, CliSyntax.PREFIX_CLASSCODE, CliSyntax.PREFIX_SCHEDULE,CliSyntax.PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_CLASSCODE, CliSyntax.PREFIX_SCHEDULE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,AddClassCommand.MESSAGE_USAGE));
        }

        ClassCode classCode = ParserUtil.parseClassCode(argMultimap.getValue(CliSyntax.PREFIX_CLASSCODE).get());
        Schedule schedule = ParserUtil.parseSchedule(argMultimap.getValue(CliSyntax.PREFIX_SCHEDULE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));

        TutorialClass tutorialClass = new TutorialClass(classCode, schedule, tagList);

        return new AddClassCommand(tutorialClass);

    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
