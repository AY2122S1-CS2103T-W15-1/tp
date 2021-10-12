package classmate.model.tutorialclass;

import static java.util.Objects.requireNonNull;
import static classmate.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import classmate.commons.util.CollectionUtil;
import classmate.model.tutorialclass.exceptions.DuplicateTutorialClassException;
import classmate.model.tutorialclass.exceptions.TutorialClassNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of tutorial classes that enforces uniqueness between its elements and does not allow nulls.
 * A tutorial class is considered unique by comparing using {@code TutorialClass#isSameTutorialClass(TutorialClass)}.
 *
 * As such, adding and updating of students uses TutorialClass#isSameTutorialClass(TutorialClass) for equality
 * to ensure that the tutorial class being added or updated is unique in terms of identity
 * in the UniqueTutorialClassList. However, the removal of a tutorial class uses TutorialClass#equals(Object) to
 * ensure that the TutorialClass with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see TutorialClass#isSameTutorialClass(TutorialClass)
 */
public class UniqueTutorialClassList implements Iterable<TutorialClass> {

    private final ObservableList<TutorialClass> internalList = FXCollections.observableArrayList();
    private final ObservableList<TutorialClass> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tutorial class as the given argument.
     */
    public boolean contains(TutorialClass toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTutorialClass);
    }

    /**
     * Adds a tutorial class to the list.
     * The tutorial class must not already exist in the list.
     */
    public void add(TutorialClass toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTutorialClassException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the tutorial class {@code target} in the list with {@code editedTutorialClass}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedTutorialClass} must not be the same as another
     * existing tutorial class in the list.
     */
    public void setTutorialClass(TutorialClass target, TutorialClass editedTutorialClass) {
        CollectionUtil.requireAllNonNull(target, editedTutorialClass);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TutorialClassNotFoundException();
        }

        if (!target.isSameTutorialClass(editedTutorialClass) && contains(editedTutorialClass)) {
            throw new DuplicateTutorialClassException();
        }

        internalList.set(index, editedTutorialClass);
    }

    /**
     * Removes the equivalent tutorial class from the list.
     * The tutorial class must exist in the list.
     */
    public void remove(TutorialClass toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TutorialClassNotFoundException();
        }
    }

    public void setTutorialClasses(UniqueTutorialClassList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tutorialClasses}.
     * {@code tutorialClasses} must not contain duplicate tutorial classes.
     */
    public void setTutorialClasses(List<TutorialClass> tutorialClasses) {
        CollectionUtil.requireAllNonNull(tutorialClasses);
        if (!tutorialClassesAreUnique(tutorialClasses)) {
            throw new DuplicateTutorialClassException();
        }

        internalList.setAll(tutorialClasses);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TutorialClass> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<TutorialClass> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTutorialClassList // instanceof handles nulls
                && internalList.equals(((UniqueTutorialClassList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tutorial classes} contains only unique tutorial classes.
     */
    private boolean tutorialClassesAreUnique(List<TutorialClass> tutorialClasses) {
        for (int i = 0; i < tutorialClasses.size() - 1; i++) {
            for (int j = i + 1; j < tutorialClasses.size(); j++) {
                if (tutorialClasses.get(i).isSameTutorialClass(tutorialClasses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
