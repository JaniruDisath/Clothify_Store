package student.controller.POSController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.SearchableComboBox;
import student.model.dto.CartItem;
import student.model.dto.Item;
import student.model.dto.LoyaltyCustomer;
import student.services.item.ItemService;
import student.services.item.ItemServiceImpl;
import student.singleton.CartManager;

import java.net.URL;
import java.util.*;

public class PosControllerImpl implements PosController, Initializable {

    private static PosControllerImpl instance;

    public static PosControllerImpl getInstance() {
        if (instance == null) {
            instance = new PosControllerImpl();
        }
        return instance;
    }

    private LoyaltyCustomer loyaltyCustomer;

    @FXML
    private Text loyalCustomerName;

    @Override
    public void setLoyaltyCustomer(LoyaltyCustomer loyaltyCustomer) {
        this.loyaltyCustomer = loyaltyCustomer;
        loyalCustomerName.setText(loyaltyCustomer.getName());
    }

    @FXML
    private SearchableComboBox<String> searchableComboBox;

    private final ItemService itemService = new ItemServiceImpl();

    private Map<String, Item> itemLookup = new HashMap<>();

    private void loadComboBoxItems() {
        List<Item> list = itemService.getAllItems();
        List<String> displayList = new ArrayList<>();

        for (Item item : list) {

            String display = item.getName() + " - " +
                    item.getCode() + " - " +
                    item.getColor() + " - " +
                    item.getSize();

            displayList.add(display);
            itemLookup.put(display, item);
        }

        searchableComboBox.getItems().setAll(displayList);

        searchableComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) return;

            Item selected = itemLookup.get(newVal);
            if (selected == null) return;

            loadItemToInfoCard(selected);
        });
    }

    @FXML
    private Label availableLabel;

    @FXML
    private Label brandNameLabel;

    @FXML
    private Label codeLabel;

    @FXML
    private Label colorLabel;

    @FXML
    private Pane itemIcon;

    @FXML
    private Label itemNameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label quantityCountLabel;

    @FXML
    private Label sizeLabel;

    @FXML
    private ImageView imageIcon;


    private void loadItemToInfoCard(Item item) {
        if (item == null) return;

        selectedItem = item;                     // <-- save selected item
        currentQuantity = 1;                     // reset quantity
        availableStock = item.getQty();          // save stock
        selectedItemPrice = item.getPrice();     // save unit price

        brandNameLabel.setText(item.getBrand());
        itemNameLabel.setText(item.getName());
        codeLabel.setText("Code : " + item.getCode());
        sizeLabel.setText("Size : " + item.getSize());
        colorLabel.setText("Colour : " + item.getColor());
        availableLabel.setText(item.getQty() + " Available");

        quantityCountLabel.setText(String.valueOf(currentQuantity));
        priceLabel.setText("Rs. " + String.format("%.2f", item.getPrice()));

        loadItemImage(item.getImagePath());
    }


    private void loadItemImage(String path) {
        try {
            Image image = new Image(path);
            imageIcon.setImage(image);
        } catch (Exception e) {
            System.out.println("Image not found: " + path);
        }
    }

    private int currentQuantity = 1;
    private int availableStock = 0;
    private double selectedItemPrice = 0.0;
    private Item selectedItem = null;

    @FXML
    void onDecreaseQuantity(MouseEvent event) {
        if (selectedItem == null) return;

        if (currentQuantity > 1) {
            currentQuantity--;
            quantityCountLabel.setText(String.valueOf(currentQuantity));
        } else {
            System.out.println("Quantity cannot be less than 1");
        }
    }

    @FXML
    void onIncreaseQuantity(MouseEvent event) {
        if (selectedItem == null) return;

        if (currentQuantity < availableStock) {
            currentQuantity++;
            quantityCountLabel.setText(String.valueOf(currentQuantity));
        } else {
            System.out.println("Reached maximum stock amount");
        }
    }


    private void addToCart() {
        if (selectedItem == null) return;
        CartManager.getInstance().addCartItem(selectedItem,currentQuantity);
        updateCartVbox();
    }





    @FXML
    private Text finalTotalLabel;

    @FXML
    private Text subtotalLabel;

    @FXML
    private Text discountLabel;


    @FXML
    private VBox cartVBox;

    public void updateCartVbox(){
        cartVBox.getChildren().clear();
        cartVBox.setSpacing(20);
        for (CartItem elements : CartManager.getInstance().getCartList()) {
            if (elements==null) continue;
            cartVBox.getChildren().add(elements.getUiBox());
        }
        updateCalculations();
    }

    private void updateCalculations() {
        Double subTotalTemp = CartManager.getInstance().getTotal();
        Double discountTemp = CartManager.getInstance().getDiscount();
        subtotalLabel.setText(String.valueOf(subTotalTemp));
        discountLabel.setText(String.valueOf(discountTemp));
        finalTotalLabel.setText(String.valueOf(subTotalTemp-discountTemp));
    }



    @FXML
    void onAddToCart(ActionEvent event) {
        addToCart();
    }



    @FXML
    void onProceedPayment(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance = this;
        loadComboBoxItems();
    }


}
