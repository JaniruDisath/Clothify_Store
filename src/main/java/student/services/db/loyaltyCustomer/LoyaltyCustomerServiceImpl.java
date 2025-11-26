package student.services.db.loyaltyCustomer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.ClothifyDatabase;
import repository.loyaltyCustomerTable.LoyaltyCustomerTable;
import student.model.dto.LoyaltyCustomer;
import student.model.entity.LoyaltyCustomerEntity;
import student.util.HibernateUtil;

public class LoyaltyCustomerServiceImpl implements LoyaltyCustomerService {

    private final ClothifyDatabase<LoyaltyCustomerEntity> database = new LoyaltyCustomerTable();
    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public LoyaltyCustomer getLoyaltyCustomer(String id) {
        LoyaltyCustomerEntity entity = database.getAnItem(session,id);
        return mapToModel(entity);
    }


    public boolean customerExists(String phone) {
        return database.getAnItem(session,phone) != null;
    }

    @Override
    public LoyaltyCustomer addLoyaltyCustomer(LoyaltyCustomer model) {
        Transaction tx = null;
        if (customerExists(model.getPhone())) {
            return getLoyaltyCustomer(model.getPhone());
        }
        try {
            tx = session.beginTransaction();
            database.insertAnItem(session,mapToEntity(model));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return model;
    }

    private LoyaltyCustomer mapToModel(LoyaltyCustomerEntity entity) {
        if (entity == null) return null;
        return new LoyaltyCustomer(
                entity.getPhone(),
                entity.getName(),
                entity.getEmail()
        );
    }

    private LoyaltyCustomerEntity mapToEntity(LoyaltyCustomer model) {
        if (model == null) return null;
        return new LoyaltyCustomerEntity(
                model.getPhone(),
                model.getName(),
                model.getEmail()
        );
    }
}

