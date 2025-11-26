package student.model.entity.transaction;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "card_transactions")
public class CardTransactionEntity {

    @Id
    private String cardId;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private PaymentTransactionEntity transaction;

    private String cardNumberMasked;

    private String bankName;

    private String approvalCode;

}