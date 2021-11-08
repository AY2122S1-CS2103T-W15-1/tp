package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CLASS_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_GROUP_DOES_NOT_EXIST;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;


public class AddStudentToGroupCommand extends Command {
    public static final String COMMAND_WORD = "addsg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to a group to Classmate "
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_CLASSCODE + "CLASS_CODE "
            + PREFIX_TYPE + "GROUP_TYPE "
            + PREFIX_GROUPNUMBER + "GROUP_NUMBER "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_GROUPNUMBER + "1 "
            + PREFIX_CLASSCODE + "G06 "
            + PREFIX_TYPE + "OP1 ";

    public static final String MESSAGE_SUCCESS = "Student added to Group: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "The student has already been added to an %1$s group";
    public static final String MESSAGE_NOT_SAME_CLASS = "The student and the tutorial group "
            + "do not belong to the same tutorial class";

    private final Index index;
    private final TutorialGroup toAddTutorialGroup;

    /**
     * Creates an AddClassCommand to add the specified {@code Student}
     */
    public AddStudentToGroupCommand(Index index, TutorialGroup tutorialGroup) {
        requireAllNonNull(index, tutorialGroup);
        this.index = index;
        this.toAddTutorialGroup = tutorialGroup;
        // new class with the same class code created to check whether it already exists in ClassMATE
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        // Check if index is within range of student list
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        // check if tutorial class already exists in ClassMATE
        if (!model.hasTutorialClass(TutorialClass.createTestTutorialClass(toAddTutorialGroup.getClassCode()))) {
            throw new CommandException(MESSAGE_CLASS_DOES_NOT_EXIST);
        }

        // check if tutorial group already exists in ClassMATE
        if (!model.hasTutorialGroup(toAddTutorialGroup)) {
            throw new CommandException(MESSAGE_GROUP_DOES_NOT_EXIST);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());

        // check if Tutorial Group and Student belong to the same Tutorial Class
        if (!isSameClass(studentToEdit, toAddTutorialGroup)) {
            throw new CommandException(MESSAGE_NOT_SAME_CLASS);
        }

        Student editedStudent = addTutorialGroup(studentToEdit, toAddTutorialGroup);

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAddTutorialGroup));
    }

    public boolean isSameClass(Student student, TutorialGroup tutorialGroup) {
        return student.getClassCode().equals(tutorialGroup.getClassCode());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddStudentToGroupCommand)) {
            return false;
        }

        // state check
        return index.equals(((AddStudentToGroupCommand) other).index)
                && toAddTutorialGroup.equals(((AddStudentToGroupCommand) other).toAddTutorialGroup);
    }
    /**
     * Creates and returns a {@code Student} with the {@code TutorialGroup} added.
     */
    private static Student addTutorialGroup(Student studentToEdit, TutorialGroup group) throws CommandException {
        assert studentToEdit != null;
        if (studentToEdit.hasTutorialGroupType(group.getGroupType())) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_GROUP, group.getGroupType()));
        }

        Set<TutorialGroup> updatedTutorialGroups = new HashSet<>(studentToEdit.getTutorialGroups());
        updatedTutorialGroups.add(group);
        return new Student(
                studentToEdit.getName(),
                studentToEdit.getPhone(),
                studentToEdit.getEmail(),
                studentToEdit.getAddress(),
                studentToEdit.getClassCode(),
                studentToEdit.getTags(),
                studentToEdit.getMarks(),
                updatedTutorialGroups);

    }
}
