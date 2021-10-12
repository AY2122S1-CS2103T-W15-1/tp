package classmate.ui;

import java.util.logging.Logger;

import classmate.commons.core.LogsCenter;
import classmate.model.tutorialclass.TutorialClass;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;


/**
 * Panel containting the list of classes
 */
public class ClassListPanel extends UiPart<Region> {
    private static final String FXML = "ClassListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClassListPanel.class);

    @FXML
    private ListView<TutorialClass> classListView;

    /**
     * Creates a {@code ClassListPanel} with the given {@code ObservableList}.
     */
    public ClassListPanel(ObservableList<TutorialClass> classList) {
        super(FXML);
        this.classListView.setItems(classList);
        this.classListView.setCellFactory(listView -> new ClassListViewCell());
    }

    class ClassListViewCell extends ListCell<TutorialClass> {
        @Override
        protected void updateItem(TutorialClass tutorialClass, boolean empty) {
            super.updateItem(tutorialClass, empty);

            if (empty || tutorialClass == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ClassCard(tutorialClass, getIndex() + 1).getRoot());
            }
        }
    }
}
