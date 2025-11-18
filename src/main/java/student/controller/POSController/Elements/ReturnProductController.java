package student.controller.POSController.Elements;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.controlsfx.control.SearchableComboBox;

public class ReturnProductController {

    @FXML
    private Label brandNameLabel;

    @FXML
    private Label codeLabel;

    @FXML
    private Label colorLabel;

    @FXML
    private Text discountLabel;

    @FXML
    private Pane itemIcon;

    @FXML
    private Label itemNameLabel;

    @FXML
    private Text loyalCustomerName;

    @FXML
    private Label priceLabel;

    @FXML
    private SearchableComboBox<?> searchableComboBox;

    @FXML
    private Label sizeLabel;

    @FXML
    private Text subtotalLabel;

    @FXML
    void onSendForReturn(ActionEvent event) {

    }

}
