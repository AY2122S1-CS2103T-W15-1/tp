package classmate.model;

import java.nio.file.Path;

import classmate.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getClassmateFilePath();

}
