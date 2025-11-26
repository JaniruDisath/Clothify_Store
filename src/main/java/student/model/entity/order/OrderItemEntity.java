package student.model.entity.order;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private String itemCode;

    private String name;

    private int quantity;

    private double unitPrice;

    private double discountPerUnit;

    private String orderId;

}
