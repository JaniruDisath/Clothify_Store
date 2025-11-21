package repository.transaction.cashTransactionTable;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import repository.ClothifyDatabase;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import student.model.entity.transaction.CashTransactionEntity;

import java.util.List;

public class CashTransactionTable implements ClothifyDatabase<CashTransactionEntity> {

    private static final SessionFactory sessionFactory =
            new Configuration()
                    .addAnnotatedClass(CashTransactionEntity.class)
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();

    @Override
    public List<CashTransactionEntity> getAllData() {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CashTransactionEntity> cq = cb.createQuery(CashTransactionEntity.class);
            cq.from(CashTransactionEntity.class);
            return session.createQuery(cq).getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public void insertAnItem(CashTransactionEntity entity) {
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
    public CashTransactionEntity getAnItem(String id) {
        Session session = sessionFactory.openSession();
        try {
            return session.find(CashTransactionEntity.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateAnItem(CashTransactionEntity updated) {
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
            CashTransactionEntity entity = session.find(CashTransactionEntity.class, id);
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