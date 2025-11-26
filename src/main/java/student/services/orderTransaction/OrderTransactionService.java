package student.services.orderTransaction;

import student.model.dto.order.Order;
import student.model.dto.transaction.CardTransaction;
import student.model.dto.transaction.CashTransaction;
import student.model.dto.transaction.PaymentTransactionDTO;

public interface OrderTransactionService {
    void addOrder(CashTransaction cashTransaction, CardTransaction cardTransaction);

    }
