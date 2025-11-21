package student.controller.POSController.Elements.item.cartItem;

import student.model.dto.onlyDto.CartItem;

public interface CartItemController {
    void setValues(CartItem cartItem);
    void updateQuantity(Integer newQuantity);
}
