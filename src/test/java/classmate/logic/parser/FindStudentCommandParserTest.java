package classmate.logic.parser;

import static classmate.logic.parser.CommandParserTestUtil.assertParseFailure;
import static classmate.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import classmate.commons.core.Messages;
import classmate.model.student.NameContainsKeywordsPredicate;
import classmate.logic.commands.FindStudentCommand;


public class FindStudentCommandParserTest {

    private FindStudentCommandParser parser = new FindStudentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                FindStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindStudentCommand expectedFindStudentCommand =
                new FindStudentCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindStudentCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindStudentCommand);
    }

}
