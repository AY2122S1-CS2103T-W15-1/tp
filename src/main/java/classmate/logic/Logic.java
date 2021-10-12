package classmate.logic;

import java.nio.file.Path;

import classmate.model.Model;
import classmate.model.student.Student;
import javafx.collections.ObservableList;
import classmate.commons.core.GuiSettings;
import classmate.logic.commands.CommandResult;
import classmate.logic.commands.exceptions.CommandException;
import classmate.logic.parser.exceptions.ParseException;
import classmate.model.ReadOnlyClassmate;
import classmate.model.tutorialclass.TutorialClass;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the Classmate.
     *
     * @see Model#getClassmate()
     */
    ReadOnlyClassmate getClassmate();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered list of Tutprial Classes */
    ObservableList<TutorialClass> getFilteredTutorialClassList();

    /**
     * Returns the user prefs' ClassMATE file path.
     */
    Path getClassmateFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
