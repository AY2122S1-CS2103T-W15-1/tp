package classmate.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import classmate.commons.exceptions.DataConversionException;
import classmate.model.Classmate;
import classmate.model.ReadOnlyClassmate;

/**
 * Represents a storage for {@link Classmate}.
 */
public interface ClassmateStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getClassmateFilePath();

    /**
     * Returns Classmate data as a {@link ReadOnlyClassmate}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyClassmate> readClassmate() throws DataConversionException, IOException;

    /**
     * @see #getClassmateFilePath()
     */
    Optional<ReadOnlyClassmate> readClassmate(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyClassmate} to the storage.
     * @param classmate cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveClassmate(ReadOnlyClassmate classmate) throws IOException;

    /**
     * @see #saveClassmate(ReadOnlyClassmate)
     */
    void saveClassmate(ReadOnlyClassmate classmate, Path filePath) throws IOException;

}
