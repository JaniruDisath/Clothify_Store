package student.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Item {

    private String code;
    private String name;
    private String brand;
    private String category;

    private String size;
    private String color;

    private Double price;
    private int qty;           // stock
    private Double discount;   // percentage

    private String imagePath;

}
