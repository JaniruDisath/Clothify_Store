package student.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    private String itemCode; // Example: DRS-004-M-BLU (unique for size+color)

    private String name;     // Item Name: "Casual Dress"
    private String brand;    // Optional: "H&M", “G-Five”
    private String category; // Example: "Dress", "Shirt"

    private String size;     // Example: "M", "L", "XL"
    private String color;    // Example: "Blue", "Black"

    private double unitPrice; // Price per item
    private int quantity;     // Current stock

    private double discount;  // Discount percentage for loyalty customers (e.g., 10 = 10%)

    private String imagePath; // Relative path for displaying the image
}
