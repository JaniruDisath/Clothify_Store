package student.services.db.walkinCustomer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.ClothifyDatabase;
import repository.walkInCustomerTable.WalkInCustomerTable;
import student.model.entity.WalkInCustomerEntity;
import student.singleton.CartManager;
import student.util.HibernateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WalkInCustomerServiceImpl implements WalkInCustomerService {

    private final Session session = HibernateUtil.getSessionFactory().openSession();
    private final ClothifyDatabase<WalkInCustomerEntity> database = new WalkInCustomerTable();

    public String generateWalkInCustomerId() {

        WalkInCustomerEntity last = database.getLastAddedItem(session);

        if (last == null) {
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
            return "WIC-" + today + "-0001";
        }

        String lastId = last.getCustomerId();
        String[] parts = lastId.split("-");

        String lastDate = parts[1];
        int lastNumber = Integer.parseInt(parts[2]);

        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        int newCounter = today.equals(lastDate) ? lastNumber + 1 : 1;

        return String.format("WIC-%s-%04d", today, newCounter);
    }


}
