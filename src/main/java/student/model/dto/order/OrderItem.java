package student.model.dto.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class OrderItem {
    private String itemCode;
    private String name;
    private int quantity;
    private double unitPrice;
    private double discountPerUnit;
    private double lineTotal;

}