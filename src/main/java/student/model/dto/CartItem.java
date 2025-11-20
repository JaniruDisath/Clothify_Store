package student.model.dto;

import javafx.scene.layout.HBox;
import lombok.*;
import student.controller.POSController.Elements.item.cartItem.CartItemController;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CartItem {

    private final Item item;
    private int quantity;
    private final HBox uiBox;                      // UI node loaded from FXML
    private final CartItemController controller;   // controller for the FXML

}
