package repository.transaction.cardTransactionTable;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import repository.ClothifyDatabase;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import student.model.entity.transaction.CardTransactionEntity;

import java.util.List;

public class CardTransactionTable implements ClothifyDatabase<CardTransactionEntity> {

    private static final SessionFactory sessionFactory =
            new Configuration()
                    .addAnnotatedClass(CardTransactionEntity.class)
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();

    @Override
    public List<CardTransactionEntity> getAllData() {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CardTransactionEntity> cq = cb.createQuery(CardTransactionEntity.class);
            cq.from(CardTransactionEntity.class);
            return session.createQuery(cq).getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public void insertAnItem(CardTransactionEntity entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public CardTransactionEntity getAnItem(String id) {
        Session session = sessionFactory.openSession();
        try {
            return session.find(CardTransactionEntity.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateAnItem(CardTransactionEntity updated) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.merge(updated);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteAnItem(String id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            CardTransactionEntity entity = session.find(CardTransactionEntity.class, id);
            if (entity != null)
                session.remove(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}