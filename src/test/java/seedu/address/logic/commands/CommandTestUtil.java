package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Classmate;
import seedu.address.model.Model;
import seedu.address.model.student.ClassCode;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialclass.ClassCodeContainsKeywordsPredicate;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.GroupNumber;
import seedu.address.model.tutorialgroup.GroupType;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_CLASSCODE_AMY = "G02";
    public static final String VALID_CLASSCODE_BOB = "G06";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_BESTCLASS = "BestClass";
    public static final String VALID_TAG_MORNING = "Morning";
    public static final String VALID_GROUPNUMBER_1 = "1";
    public static final String VALID_GROUPTYPE_OP1 = "OP1";
    public static final String VALID_GROUPNUMBER_2 = "2";
    public static final String VALID_GROUPTYPE_OP2 = "OP2";
    public static final String VALID_CLASSCODE_G01 = "G01";
    public static final String VALID_CLASSCODE_G02 = "G02";
    public static final String VALID_CLASSCODE_G06 = "G06";
    public static final String VALID_SCHEDULE_G01 = "Monday 10:00am to 12:00pm, Thursday 10:00am to 12:00pm";
    public static final String VALID_SCHEDULE_G02 = "Tuesday 10:00am to 12:00pm, Friday 10:00am to 12:00pm";
    public static final String VALID_SCHEDULE_G06 = "Tues 12:00pm to 2:00pm, Fri 12:00pm to 2:00pm";
    public static final String VALID_MARK = " " + PREFIX_MARK + "GOOD";
    public static final String VALID_MARK_2 = " " + PREFIX_MARK + "POOR";
    public static final TutorialGroup VALID_GROUP_G01_OP1 = new TutorialGroup(new GroupNumber("1"),
            new ClassCode("G01"), new GroupType("OP1"));
    public static final TutorialGroup VALID_GROUP_G06_OP1 = new TutorialGroup(new GroupNumber("3"),
            new ClassCode("G06"), new GroupType("OP1"));
    public static final TutorialGroup VALID_GROUP_G01_OP2 = new TutorialGroup(new GroupNumber("1"),
            new ClassCode("G01"), new GroupType("OP2"));

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String CLASSCODE_DESC_AMY = " " + PREFIX_CLASSCODE + VALID_CLASSCODE_AMY;
    public static final String CLASSCODE_DESC_BOB = " " + PREFIX_CLASSCODE + VALID_CLASSCODE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String TAG_DESC_BESTCLASS = " " + PREFIX_TAG + VALID_TAG_BESTCLASS;
    public static final String TAG_DESC_MORNING = " " + PREFIX_TAG + VALID_TAG_MORNING;
    public static final String GROUPNUMBER_DESC_1 = " " + PREFIX_GROUPNUMBER + VALID_GROUPNUMBER_1;
    public static final String GROUPTYPE_DESC_OP1 = " " + PREFIX_TYPE + VALID_GROUPTYPE_OP1;
    public static final String GROUPNUMBER_DESC_2 = " " + PREFIX_GROUPNUMBER + VALID_GROUPNUMBER_2;
    public static final String GROUPTYPE_DESC_OP2 = " " + PREFIX_TYPE + VALID_GROUPTYPE_OP2;
    public static final String CLASSCODE_DESC_G01 = " " + PREFIX_CLASSCODE + VALID_CLASSCODE_G01;
    public static final String CLASSCODE_DESC_G02 = " " + PREFIX_CLASSCODE + VALID_CLASSCODE_G02;
    public static final String CLASSCODE_DESC_G06 = " " + PREFIX_CLASSCODE + VALID_CLASSCODE_G06;
    public static final String SCHEDULE_DESC_GO1 = " " + PREFIX_SCHEDULE + VALID_SCHEDULE_G01;
    public static final String SCHEDULE_DESC_GO2 = " " + PREFIX_SCHEDULE + VALID_SCHEDULE_G02;
    public static final String SCHEDULE_DESC_G06 = " " + PREFIX_SCHEDULE + VALID_SCHEDULE_G06;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_SCHEDULE = " " + PREFIX_SCHEDULE + " ";
    public static final String INVALID_MARK = " " + PREFIX_MARK + "BAD"; //BAD is not a valid mark


    //Verification not yet implemented
    public static final String INVALID_CLASSCODE_DESC = " " + PREFIX_TAG + "T03"; // Classcode must start with G

    // only numbers are allowed for group name
    public static final String INVALID_GROUPNUMBER_DESC = " " + PREFIX_GROUPNUMBER + "a";
    // only OP1 and OP2 are allowed for group type
    public static final String INVALID_GROUPTYPE_DESC = " " + PREFIX_TYPE + "OP3";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withClassCode(VALID_CLASSCODE_AMY).withTags(VALID_TAG_FRIEND)
                .withMarks("LOW", "HIGH").build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withClassCode(VALID_CLASSCODE_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the ClassMATE, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Classmate expectedClassmate = new Classmate(actualModel.getClassmate());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedClassmate, actualModel.getClassmate());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s ClassMATE.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s ClassMATE.
     */
    public static void showTutorialClassAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTutorialClassList().size());

        TutorialClass tutorialClass = model.getFilteredTutorialClassList().get(targetIndex.getZeroBased());
        final String[] splitClass = tutorialClass.getClassCode().toString().split("\\s+");
        model.updateFilteredTutorialClassList(new ClassCodeContainsKeywordsPredicate(Arrays.asList(splitClass[0])));

        assertEquals(1, model.getFilteredTutorialClassList().size());
    }

}
