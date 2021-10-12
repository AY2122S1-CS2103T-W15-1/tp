package classmate.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import classmate.commons.core.LogsCenter;
import classmate.commons.exceptions.DataConversionException;
import classmate.model.ReadOnlyClassmate;
import classmate.model.ReadOnlyUserPrefs;
import classmate.model.UserPrefs;

/**
 * Manages storage of Classmate data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ClassmateStorage classmateStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ClassmateStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ClassmateStorage classmateStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.classmateStorage = classmateStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Classmate methods ==============================

    @Override
    public Path getClassmateFilePath() {
        return classmateStorage.getClassmateFilePath();
    }

    @Override
    public Optional<ReadOnlyClassmate> readClassmate() throws DataConversionException, IOException {
        return readClassmate(classmateStorage.getClassmateFilePath());
    }

    @Override
    public Optional<ReadOnlyClassmate> readClassmate(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return classmateStorage.readClassmate(filePath);
    }

    @Override
    public void saveClassmate(ReadOnlyClassmate classmate) throws IOException {
        saveClassmate(classmate, classmateStorage.getClassmateFilePath());
    }

    @Override
    public void saveClassmate(ReadOnlyClassmate classmate, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        classmateStorage.saveClassmate(classmate, filePath);
    }

}
