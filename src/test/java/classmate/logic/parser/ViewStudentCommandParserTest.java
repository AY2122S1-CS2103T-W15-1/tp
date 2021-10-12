package classmate.logic.parser;

import static classmate.logic.parser.CommandParserTestUtil.assertParseFailure;
import static classmate.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import classmate.commons.core.Messages;
import classmate.logic.commands.ViewStudentCommand;
import classmate.testutil.TypicalIndexes;

class ViewStudentCommandParserTest {
    private ViewStudentCommandParser parser = new ViewStudentCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new ViewStudentCommand(TypicalIndexes.INDEX_FIRST_STUDENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                ViewStudentCommand.MESSAGE_USAGE));
    }
}
