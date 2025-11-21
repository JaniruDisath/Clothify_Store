package repository.transaction.transactionTable;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import repository.ClothifyDatabase;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import student.model.entity.transaction.TransactionEntity;

import java.util.List;

public class TransactionTable implements ClothifyDatabase<TransactionEntity> {

    private static final SessionFactory sessionFactory =
            new Configuration()
                    .addAnnotatedClass(TransactionEntity.class)
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();

    @Override
    public List<TransactionEntity> getAllData() {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TransactionEntity> cq = cb.createQuery(TransactionEntity.class);
            cq.from(TransactionEntity.class);
            return session.createQuery(cq).getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public void insertAnItem(TransactionEntity entity) {
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
    public TransactionEntity getAnItem(String id) {
        Session session = sessionFactory.openSession();
        try {
            return session.find(TransactionEntity.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateAnItem(TransactionEntity updated) {
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
            TransactionEntity entity = session.find(TransactionEntity.class, id);
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