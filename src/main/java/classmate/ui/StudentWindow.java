package classmate.ui;

import java.util.logging.Logger;

import classmate.commons.core.LogsCenter;
import classmate.model.student.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller for a student display page
 */
public class StudentWindow extends UiPart<Stage> {

    public static final String VIEW_MESSAGE = "You are currently viewing: ";

    private static final Logger logger = LogsCenter.getLogger(StudentWindow.class);
    private static final String FXML = "StudentWindow.fxml";

    @FXML
    private Label showMessage;

    @FXML
    private Label studentName;

    @FXML
    private Label studentAddress;

    @FXML
    private Label studentEmail;

    @FXML
    private Label studentPhone;

    @FXML
    private Label studentTags;

    /**
     * Creates a new StudentWindow.
     *
     * @param root Stage to use as the root of the StudentWindow.
     */
    public StudentWindow(Stage root) {
        super(FXML, root);
        showMessage.setText(VIEW_MESSAGE);
    }

    /**
     * Creates a new StudentWindow.
     */
    public StudentWindow() {
        this(new Stage());
    }

    /**
     * Shows the student window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show(Student student) {
        logger.fine("Showing student information.");
        studentName.setText(student.getName().toString());
        studentAddress.setText(student.getAddress().toString());
        studentEmail.setText(student.getEmail().toString());
        studentPhone.setText(student.getPhone().toString());
        Object[] tags = student.getTags().toArray();
        String tagList = "";
        for (int i = 0; i < tags.length; i++) {
            tagList += tags[i];
        }
        studentTags.setText(tagList);
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the student window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the student window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the student window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
