package student.controller.POSController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.controlsfx.control.SearchableComboBox;

public class PosController {

    @FXML
    private Label availableLabel;

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
    private Label quantityCountLabel;

    @FXML
    private SearchableComboBox<?> searchableComboBox;

    @FXML
    private Label sizeLabel;

    @FXML
    private Text subtotalLabel;

    @FXML
    void onAddToCart(ActionEvent event) {

    }

    @FXML
    void onDecreaseQuantity(MouseEvent event) {

    }

    @FXML
    void onIncreaseQuantity(MouseEvent event) {

    }

    @FXML
    void onProceedPayment(ActionEvent event) {

    }

}
