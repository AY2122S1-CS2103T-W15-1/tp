package classmate.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import classmate.model.Classmate;
import classmate.model.ReadOnlyClassmate;
import classmate.model.student.Address;
import classmate.model.student.ClassCode;
import classmate.model.student.Email;
import classmate.model.student.Name;
import classmate.model.student.Phone;
import classmate.model.student.Student;
import classmate.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Classmate} with sample data.
 */
public class SampleDataUtil {

    public static final ClassCode CLASS_CODE = new ClassCode("");

    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), CLASS_CODE,
                getTagSet("friends")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), CLASS_CODE,
                getTagSet("colleagues", "friends")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), CLASS_CODE,
                getTagSet("neighbours")),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), CLASS_CODE,
                getTagSet("family")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), CLASS_CODE,
                getTagSet("classmates")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), CLASS_CODE,
                getTagSet("colleagues"))
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

}
