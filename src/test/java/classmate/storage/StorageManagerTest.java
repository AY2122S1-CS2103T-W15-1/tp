package classmate.storage;

import static classmate.testutil.TypicalStudents.getTypicalClassmate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import classmate.commons.core.GuiSettings;
import classmate.model.Classmate;
import classmate.model.ReadOnlyClassmate;
import classmate.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonClassmateStorage classmateStorage = new JsonClassmateStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(classmateStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void classmateReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonClassmateStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonClassmateStorageTest} class.
         */
        Classmate original = getTypicalClassmate();
        storageManager.saveClassmate(original);
        ReadOnlyClassmate retrieved = storageManager.readClassmate().get();
        assertEquals(original, new Classmate(retrieved));
    }

    @Test
    public void getClassmateFilePath() {
        assertNotNull(storageManager.getClassmateFilePath());
    }

}
