package student.singleton;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import student.controller.POSController.Elements.item.cartItem.CartItemController;
import student.controller.POSController.PosControllerImpl;
import student.model.dto.onlyDto.CartItem;
import student.model.dto.Item;
import student.model.dto.order.OrderItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static CartManager instance;

    private final List<CartItem> cartList = new ArrayList<>();

    private CartManager() {
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addCartItem(Item item, Integer quantity) {
        if (!cartList.isEmpty()) {
            for (CartItem cartItem : cartList) {
                if (cartItem.getItem().equals(item)) {
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                    cartItem.getController().updateQuantity(cartItem.getQuantity());
                    return;
                }
            }
        }
        FXMLLoader loader = getUICard();
        HBox hBox = getTheUI(loader);
        CartItemController controller = loader.getController();
        CartItem cartItem = new CartItem(item,quantity,hBox,controller);
        controller.setValues(cartItem);
        cartList.add(cartItem);
    }
    //Load the UI
    private HBox getTheUI(FXMLLoader loader) {
        HBox orderCard;
        try {
            orderCard = loader.load();  // load the loader that was passed in
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return orderCard;
    }

    //Load the UI
    private FXMLLoader getUICard(){
        return new FXMLLoader(getClass().getResource("/view/pages/cashier/pos/elements/CartItems.fxml"));
    }

    public List<CartItem> getCartList() {
        return cartList;
    }

    public Boolean isCartEmpty() {
        return cartList.isEmpty();
    }

    public List<OrderItem> getOrderItemList(){
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartItem cartItem : cartList) {
            orderItemList.add(new OrderItem(
                    cartItem.getItem().getCode(),
                    "",
                    cartItem.getItem().getName(),
                    cartItem.getQuantity(),
                    cartItem.getItem().getPrice(),
                    cartItem.getItem().getPrice()/100*cartItem.getItem().getDiscount()
            ));
        }
        return orderItemList;
    }

    public void updateCartItemQuantity(Item item,Integer newQuantity) {
        for (CartItem cartItem : cartList) {
            if (cartItem.getItem().getCode().equals(item.getCode())) {
                cartItem.setQuantity(newQuantity);
            }
        }
    }

    public void removeCartItem(Item item) {
        if (item == null || cartList.isEmpty()) return;

        cartList.removeIf(cartItem ->
                cartItem.getItem().getCode().equals(item.getCode())
        );

        PosControllerImpl.getInstance().updateCartVbox();
    }

    public Double getTotal(){
        Double total = 0.0;
        for (CartItem cartItem : cartList) {
            total += cartItem.getQuantity() * cartItem.getItem().getPrice();
        }
        return total;
    }

    public Double getTotalDiscount(){
        Double discount = 0.0;
        for (CartItem cartItem : cartList) {
            if(cartItem.getItem().getDiscount()==0) continue;
            discount += ((cartItem.getQuantity() * cartItem.getItem().getPrice())/100*cartItem.getItem().getDiscount());

        }
        return discount;
    }

    public void resetCart(){
        instance = null;
    }
}