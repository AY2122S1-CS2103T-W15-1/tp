package classmate.model;

import static classmate.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import classmate.testutil.Assert;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        Assert.assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setClassmateFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        Assert.assertThrows(NullPointerException.class, () -> userPrefs.setClassmateFilePath(null));
    }

}
