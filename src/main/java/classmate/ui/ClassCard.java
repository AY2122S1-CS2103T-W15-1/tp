package classmate.ui;

import java.util.Comparator;

import classmate.model.tutorialclass.TutorialClass;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class ClassCard extends UiPart<Region> {
    private static final String FXML = "ClassListCard.fxml";

    public final TutorialClass tutorialClass;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label classCode;
    @FXML
    private Label schedule;
    @FXML
    private FlowPane tags;


    /**
     * Creates a {@code ClassCard} with the given {@code TutorialClass} and index to display.
     */
    public ClassCard(TutorialClass tutorialClass, int displayedIndex) {
        super(FXML);
        this.tutorialClass = tutorialClass;
        id.setText(displayedIndex + ". ");
        classCode.setText(tutorialClass.getClassCode().value);
        schedule.setText(tutorialClass.getSchedule().schedule);
        tutorialClass.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new javafx.scene.control.Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassCard)) {
            return false;
        }

        // state check
        ClassCard card = (ClassCard) other;
        return id.getText().equals(card.id.getText())
                && tutorialClass.equals(card.tutorialClass);
    }
}
