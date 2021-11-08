package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G02;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNUMBER_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNUMBER_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPTYPE_OP1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPTYPE_OP2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Classmate;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * A utility class containing a list of {@code TutorialGroup} objects to be used in tests.
 */
public class TypicalTutorialGroups {
    public static final TutorialGroup TUT_01 = new TutorialGroupBuilder().withClassCode(VALID_CLASSCODE_G01)
            .withGroupNumber(VALID_GROUPNUMBER_1).withGroupType(VALID_GROUPTYPE_OP1).build();
    public static final TutorialGroup TUT_02 = new TutorialGroupBuilder().withClassCode(VALID_CLASSCODE_G02)
            .withGroupNumber(VALID_GROUPNUMBER_2).withGroupType(VALID_GROUPTYPE_OP2).build();
    public static final TutorialGroup TUT_03 = new TutorialGroupBuilder().withClassCode(VALID_CLASSCODE_G01)
            .withGroupNumber(VALID_GROUPNUMBER_1).withGroupType(VALID_GROUPTYPE_OP2).build();

    // Prevent instantiation
    private TypicalTutorialGroups() {}

    /**
     * Returns an {@code Classmate} with all the typical tutorial gruops.
     */
    public static Classmate getTypicalClassmate() {
        Classmate ab = new Classmate();
        for (TutorialGroup tutorialGroup : getTypicalTutorialGroups()) {
            ab.addTutorialGroup(tutorialGroup);
        }
        return ab;
    }

    public static List<TutorialGroup> getTypicalTutorialGroups() {
        return new ArrayList<>(Arrays.asList(TUT_01, TUT_02, TUT_03));
    }

}
