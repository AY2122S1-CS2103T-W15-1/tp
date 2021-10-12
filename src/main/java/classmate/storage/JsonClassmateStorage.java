package classmate.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import classmate.commons.core.LogsCenter;
import classmate.commons.exceptions.DataConversionException;
import classmate.commons.exceptions.IllegalValueException;
import classmate.commons.util.FileUtil;
import classmate.commons.util.JsonUtil;
import classmate.model.ReadOnlyClassmate;

/**
 * A class to access Classmate data stored as a json file on the hard disk.
 */
public class JsonClassmateStorage implements ClassmateStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonClassmateStorage.class);

    private Path filePath;

    public JsonClassmateStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getClassmateFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyClassmate> readClassmate() throws DataConversionException {
        return readClassmate(filePath);
    }

    /**
     * Similar to {@link #readClassmate()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyClassmate> readClassmate(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableStudent> jsonClassmate = JsonUtil.readJsonFile(
                filePath, JsonSerializableStudent.class);
        if (!jsonClassmate.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonClassmate.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveClassmate(ReadOnlyClassmate classmate) throws IOException {
        saveClassmate(classmate, filePath);
    }

    /**
     * Similar to {@link #saveClassmate(ReadOnlyClassmate)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveClassmate(ReadOnlyClassmate classmate, Path filePath) throws IOException {
        requireNonNull(classmate);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableStudent(classmate), filePath);
    }

}
