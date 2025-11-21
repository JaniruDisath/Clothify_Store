package student.model.dto.transaction;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CardTransaction {
    private String transactionId;
    private String cardNumberMasked;
    private String bankName;
    private String approvalCode;

}