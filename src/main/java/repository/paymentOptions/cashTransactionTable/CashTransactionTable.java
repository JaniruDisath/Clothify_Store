package repository.paymentOptions.cashTransactionTable;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import repository.ClothifyDatabase;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import student.model.entity.transaction.CashTransactionEntity;

import java.util.List;

public class CashTransactionTable implements ClothifyDatabase<CashTransactionEntity> {

    @Override
    public List<CashTransactionEntity> getAllData(Session session) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CashTransactionEntity> cq = cb.createQuery(CashTransactionEntity.class);
            cq.from(CashTransactionEntity.class);
            return session.createQuery(cq).getResultList();
    }

    @Override
    public void insertAnItem(Session session, CashTransactionEntity entity) {
            session.persist(entity);
    }

    @Override
    public CashTransactionEntity getAnItem(Session session,String id) {
            return session.find(CashTransactionEntity.class, id);

    }

    @Override
    public void updateAnItem(Session session,CashTransactionEntity updated) {
            session.merge(updated);
    }

    @Override
    public void deleteAnItem(Session session,String id) {
            CashTransactionEntity entity = session.find(CashTransactionEntity.class, id);
            if (entity != null)
                session.remove(entity);
    }

    @Override
    public CashTransactionEntity getLastAddedItem(Session session) {
        return null;
    }
}