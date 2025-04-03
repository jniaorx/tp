package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import seedu.address.model.person.Patient;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends PersonCard {

    private static final String FXML = "PatientListCard.fxml"; // Update to the correct FXML file for patient cards

    public final Patient patient;

    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PatientCard} with the given {@code Person} to display.
     */
    public PatientCard(Patient patient, int displayedIndex) {
        super(patient, displayedIndex, FXML);
        this.patient = patient;

        // Add label only if it's not "NA"
        addIfNotNA(patient.getDepartment().toString());
    }
}
