package student.model.dto.order;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class Order {
    private String orderId;
    private LocalDateTime orderTime;
    private String cashierId;
    private String loyaltyPhone;
    private double subtotal;
    private double discountTotal;
    private double grandTotal;
    private String paymentType;
    private List<OrderItem> items;

}
