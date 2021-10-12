package classmate.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import classmate.commons.exceptions.DataConversionException;
import classmate.model.ReadOnlyClassmate;
import classmate.model.ReadOnlyUserPrefs;
import classmate.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ClassmateStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getClassmateFilePath();

    @Override
    Optional<ReadOnlyClassmate> readClassmate() throws DataConversionException, IOException;

    @Override
    void saveClassmate(ReadOnlyClassmate classmate) throws IOException;

}
