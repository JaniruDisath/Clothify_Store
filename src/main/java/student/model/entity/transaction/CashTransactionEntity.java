package student.model.entity.transaction;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "cash_transactions")
public class CashTransactionEntity {

    @Id
    private String cashId;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transaction;

    private double amountGiven;

    private double changeReturned;


}