package classmate.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import classmate.model.student.Student;
import classmate.storage.Storage;
import javafx.collections.ObservableList;
import classmate.commons.core.GuiSettings;
import classmate.commons.core.LogsCenter;
import classmate.logic.commands.Command;
import classmate.logic.commands.CommandResult;
import classmate.logic.commands.exceptions.CommandException;
import classmate.logic.parser.ClassmateParser;
import classmate.logic.parser.exceptions.ParseException;
import classmate.model.Model;
import classmate.model.ReadOnlyClassmate;
import classmate.model.tutorialclass.TutorialClass;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ClassmateParser classmateParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        classmateParser = new ClassmateParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = classmateParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveClassmate(model.getClassmate());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyClassmate getClassmate() {
        return model.getClassmate();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<TutorialClass> getFilteredTutorialClassList() {
        return model.getFliteredTutorialClassList();
    }

    @Override
    public Path getClassmateFilePath() {
        return model.getClassmateFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
