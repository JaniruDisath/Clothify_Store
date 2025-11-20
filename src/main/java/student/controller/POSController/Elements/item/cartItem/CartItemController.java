package student.controller.POSController.Elements.item.cartItem;

import javafx.scene.layout.HBox;
import student.model.dto.CartItem;

public interface CartItemController {
    void setValues(CartItem cartItem);
    void updateQuantity(Integer newQuantity);
}
