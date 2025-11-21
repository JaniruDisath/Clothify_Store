package student.model.dto.transaction;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CashTransaction {
    private String transactionId;
    private double amountGiven;
    private double changeReturned;

}