package student.controller.POSController.Elements.item.cartItem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import student.model.dto.onlyDto.CartItem;
import student.model.dto.Item;
import student.singleton.CartManager;

public class CartItemForm implements CartItemController {

    private Item item;
    private Integer quantity;

    @FXML
    private Label colorLabel;

    @FXML
    private Label itemNameLabel;

    @FXML
    private Label quantityCountLabel;

    @FXML
    private Label sizeLetterLabel;

    @FXML
    private Label totalAmountLabel;

    @FXML
    void onDecreaseQuantity(MouseEvent event) {
        updateQuantityInfo(--quantity);
    }

    @FXML
    void onIncreaseQuantity(MouseEvent event) {
        updateQuantityInfo(++quantity);
    }

    @FXML
    void onRemoveCartItem(MouseEvent event) {
        CartManager.getInstance().removeCartItem(item);
    }

    @Override
    public void setValues(CartItem cartItem) {
        this.item=cartItem.getItem();
        this.quantity=cartItem.getQuantity();

        colorLabel.setText(item.getColor());
        itemNameLabel.setText(item.getName());
        quantityCountLabel.setText(String.valueOf(cartItem.getQuantity()));
        sizeLetterLabel.setText(item.getSize());
        totalAmountLabel.setText(String.valueOf(item.getPrice()*quantity));
    }

    private void updateQuantityLabel() {
        quantityCountLabel.setText(String.valueOf(quantity));
    }

    private void updateTotalAmountLabel() {
        totalAmountLabel.setText(String.valueOf(item.getPrice()*quantity));
    }

    private void updateQuantityInfo(Integer newQuantity) {
        this.quantity=newQuantity;
        updateQuantityLabel();
        updateTotalAmountLabel();
        CartManager.getInstance().updateCartItemQuantity(item,quantity);
    }

    @Override
    public void updateQuantity(Integer newQuantity) {
        updateQuantityInfo(newQuantity);
    }
}
