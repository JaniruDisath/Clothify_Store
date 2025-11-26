package repository.loyaltyCustomerTable;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import student.model.entity.LoyaltyCustomerEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repository.ClothifyDatabase;

import java.util.List;

public class LoyaltyCustomerTable implements ClothifyDatabase<LoyaltyCustomerEntity> {

    @Override
    public List<LoyaltyCustomerEntity> getAllData(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<LoyaltyCustomerEntity> cq = cb.createQuery(LoyaltyCustomerEntity.class);
        cq.from(LoyaltyCustomerEntity.class);
        return session.createQuery(cq).getResultList();
    }

    @Override
    public void insertAnItem(Session session, LoyaltyCustomerEntity entity) {
        session.persist(entity);
    }

    @Override
    public LoyaltyCustomerEntity getAnItem(Session session, String id) {

        return session.find(LoyaltyCustomerEntity.class, id);

    }

    @Override
    public void updateAnItem(Session session, LoyaltyCustomerEntity updated) {
        LoyaltyCustomerEntity existing =
                session.find(LoyaltyCustomerEntity.class, updated.getPhone());
        if (existing != null) {
            existing.setName(updated.getName());
            existing.setEmail(updated.getEmail());
            session.merge(existing);
        }
    }

    @Override
    public void deleteAnItem(Session session, String id) {
        LoyaltyCustomerEntity entity =
                session.find(LoyaltyCustomerEntity.class, id);
        if (entity != null)
            session.remove(entity);
    }

    @Override
    public LoyaltyCustomerEntity getLastAddedItem(Session session) {
        return null;
    }
}

