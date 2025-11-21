package student.model.entity.transaction;

import jakarta.persistence.*;
import lombok.*;
import student.model.entity.order.OrderEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    private String transactionId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private LocalDateTime transactionTime;

    private double transactionAmount;

    private String paymentType; // CASH / CARD

}