package repository.paymentOptions.cardTransactionTable;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import repository.ClothifyDatabase;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import student.model.entity.transaction.CardTransactionEntity;
import student.util.HibernateUtil;

import java.util.List;

public class CardTransactionTable implements ClothifyDatabase<CardTransactionEntity> {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<CardTransactionEntity> getAllData(Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<CardTransactionEntity> cq = cb.createQuery(CardTransactionEntity.class);
        cq.from(CardTransactionEntity.class);
        return session.createQuery(cq).getResultList();

    }

    @Override
    public void insertAnItem(Session session, CardTransactionEntity entity) {
        session.persist(entity);
    }

    @Override
    public CardTransactionEntity getAnItem(Session session, String id) {
        return session.find(CardTransactionEntity.class, id);
    }

    @Override
    public void updateAnItem(Session session, CardTransactionEntity updated) {
        session.merge(updated);
    }

    @Override
    public void deleteAnItem(Session session, String id) {

        CardTransactionEntity entity = session.find(CardTransactionEntity.class, id);
        if (entity != null)
            session.remove(entity);
    }

    @Override
    public CardTransactionEntity getLastAddedItem(Session session) {
        return null;
    }
}