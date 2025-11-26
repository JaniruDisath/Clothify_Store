package student.services.db.paymentTransaction;

import org.hibernate.Session;
import repository.ClothifyDatabase;
import repository.paymentOptions.transactionTable.PaymentTransactionTable;
import student.model.entity.transaction.PaymentTransactionEntity;
import student.util.HibernateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaymentTransactionServiceImpl implements PaymentTransactionService {

    private final Session session = HibernateUtil.getSessionFactory().openSession();
    private final  ClothifyDatabase <PaymentTransactionEntity> database = new PaymentTransactionTable();

    @Override
    public String generateTransactionId() {

        PaymentTransactionEntity lastTx = database.getLastAddedItem(session);

        if (lastTx == null) {
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
            return "TX-" + today + "-0001";
        }

        String lastId = lastTx.getTransactionId();  // Example: TX-251120-0032
        String[] parts = lastId.split("-");

        String lastDate = parts[1];                 // "251120"
        int lastNumber = Integer.parseInt(parts[2]); // 32

        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));

        int newCounter = today.equals(lastDate) ? lastNumber + 1 : 1;

        return String.format("TX-%s-%04d", today, newCounter);
    }

}
