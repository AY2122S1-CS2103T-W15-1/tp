package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Classmate;
import seedu.address.model.ReadOnlyClassmate;
import seedu.address.model.student.Address;
import seedu.address.model.student.ClassCode;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentMark;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Contains utility methods for populating {@code Classmate} with sample data.
 */
public class SampleDataUtil {

    public static final ClassCode CLASS_CODE = new ClassCode("G00");

    public static final Set<TutorialGroup> EMPTY_TUTORIAL_GROUP = getTutorialGroupSet();

    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), CLASS_CODE,
                getTagSet("friends"), getMarkList("LOW", "EXCELLENT"), EMPTY_TUTORIAL_GROUP),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), CLASS_CODE,
                getTagSet("colleagues", "friends"), getMarkList("LOW", "HIGH"), EMPTY_TUTORIAL_GROUP),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), CLASS_CODE,
                getTagSet("neighbours"), getMarkList("HIGH", "AVG"), EMPTY_TUTORIAL_GROUP),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), CLASS_CODE,
                getTagSet("family"), getMarkList("LOW", "EXCELLENT"), EMPTY_TUTORIAL_GROUP),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), CLASS_CODE,
                getTagSet("classmates"), getMarkList("HIGH", "POOR"), EMPTY_TUTORIAL_GROUP),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), CLASS_CODE,
                getTagSet("colleagues"), getMarkList("LOW", "POOR"), EMPTY_TUTORIAL_GROUP)
        };
    }

    public static ReadOnlyClassmate getSampleClassmate() {
        Classmate sampleAb = new Classmate();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a mark list containing the list of strings given.
     */
    public static List<StudentMark> getMarkList(String... strings) {
        return Arrays.stream(strings)
                .map(StudentMark::valueOf)
                .collect(Collectors.toList());
    }

    /**
     * Returns a tutorial group set containing a default list of tutorial groups.
     */

    public static Set<TutorialGroup> getTutorialGroupSet(TutorialGroup... tutorialGroups) {
        return Arrays.stream(tutorialGroups).collect(Collectors.toSet());
    }

}

