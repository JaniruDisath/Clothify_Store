package student.model.dto.transaction;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PaymentTransactionDTO {
    private String transactionId;
    private String orderId;
    private LocalDateTime time;
    private double amount;
    private String paymentType;

}