package classmate.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static classmate.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import classmate.commons.core.Messages;
import classmate.logic.commands.AddStudentCommand;
import classmate.logic.commands.ClearCommand;
import classmate.logic.commands.DeleteStudentCommand;
import classmate.logic.commands.EditCommand;
import classmate.logic.commands.ExitCommand;
import classmate.logic.commands.FindStudentCommand;
import classmate.logic.commands.HelpCommand;
import classmate.logic.commands.ListStudentCommand;
import classmate.logic.parser.exceptions.ParseException;
import classmate.model.student.NameContainsKeywordsPredicate;
import classmate.model.student.Student;
import classmate.testutil.Assert;
import classmate.testutil.TypicalIndexes;
import classmate.testutil.EditStudentDescriptorBuilder;
import classmate.testutil.StudentBuilder;
import classmate.testutil.StudentUtil;

public class ClassmateParserTest {

    private final ClassmateParser parser = new ClassmateParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        AddStudentCommand command = (AddStudentCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddStudentCommand(student), command);

    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteStudentCommand command = (DeleteStudentCommand) parser.parseCommand(
                DeleteStudentCommand.COMMAND_WORD + " " + TypicalIndexes.INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteStudentCommand(TypicalIndexes.INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + TypicalIndexes.INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(TypicalIndexes.INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindStudentCommand command = (FindStudentCommand) parser.parseCommand(
                FindStudentCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindStudentCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD) instanceof ListStudentCommand);
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD + " 3") instanceof ListStudentCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        Assert.assertThrows(ParseException.class, String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        Assert.assertThrows(ParseException.class, Messages.MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
