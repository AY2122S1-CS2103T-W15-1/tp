package classmate.logic.commands;

import static classmate.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import classmate.model.student.Student;
import classmate.commons.core.Messages;
import classmate.commons.core.index.Index;
import classmate.logic.commands.exceptions.CommandException;
import classmate.model.Model;
import classmate.model.student.ClassCode;


public class ClassCodeCommand extends Command {

    public static final String COMMAND_WORD = "classcode";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added class code to student: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed class code from student: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the class code of the student identified "
            + "by the index number used in the last student listing. "
            + "Existing class code will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "c/ [CLASSCODE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "c/ G06.";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Remark command not implemented yet";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, ClassCode: %2$s";

    private final Index index;
    private final ClassCode classCode;

    /**
     * Adds a ClassCode to a specific {@code Student}
     * @param index Index of the student in the list
     * @param classCode ClassCode of the {@code Student} to be added
     */
    public ClassCodeCommand(Index index, ClassCode classCode) {
        requireAllNonNull(index, classCode);

        this.index = index;
        this.classCode = classCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = new Student(
                studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getAddress(), classCode, studentToEdit.getTags());

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(generateSuccessMessage(editedStudent));
    }

    private String generateSuccessMessage(Student studentToEdit) {
        String message = !classCode.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, studentToEdit);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassCodeCommand)) {
            return false;
        }

        // state check
        ClassCodeCommand e = (ClassCodeCommand) other;
        return index.equals(e.index)
                && classCode.equals(e.classCode);
    }
}
