package classmate.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static classmate.testutil.Assert.assertThrows;
import static classmate.testutil.TypicalStudents.ALICE;
import static classmate.testutil.TypicalStudents.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import classmate.commons.core.GuiSettings;
import classmate.model.student.NameContainsKeywordsPredicate;
import classmate.testutil.ClassmateBuilder;
import classmate.testutil.Assert;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        Assertions.assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Classmate(), new Classmate(modelManager.getClassmate()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setClassmateFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setClassmateFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setClassmateFilePath_nullPath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setClassmateFilePath(null));
    }

    @Test
    public void setClassmateFilePath_validPath_setsClassmateFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setClassmateFilePath(path);
        assertEquals(path, modelManager.getClassmateFilePath());
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInClassmate_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInClassmate_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void equals() {
        Classmate classmate = new ClassmateBuilder().withStudent(ALICE).withStudent(BENSON).build();
        Classmate differentClassmate = new Classmate();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(classmate, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(classmate, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different classmate -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentClassmate, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(classmate, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setClassmateFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(classmate, differentUserPrefs)));
    }
}
