package student.model.entity.order;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    private String orderId;

    private LocalDateTime orderTime;

    private String cashierId;

    private String loyaltyPhone;

    private double subtotal;

    private double discountTotal;

    private double grandTotal;

    private String paymentType;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItemEntity> items;

}