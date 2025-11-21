package repository.order.orderItemTable;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import repository.ClothifyDatabase;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import student.model.entity.order.OrderItemEntity;

import java.util.List;

public class OrderItemTable implements ClothifyDatabase<OrderItemEntity> {

    private static final SessionFactory sessionFactory =
            new Configuration()
                    .addAnnotatedClass(OrderItemEntity.class)
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();

    @Override
    public List<OrderItemEntity> getAllData() {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<OrderItemEntity> cq = cb.createQuery(OrderItemEntity.class);
            cq.from(OrderItemEntity.class);
            return session.createQuery(cq).getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public void insertAnItem(OrderItemEntity entity) {
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
    public OrderItemEntity getAnItem(String id) {
        // detailId is Long, so parse
        Long detailId = Long.parseLong(id);

        Session session = sessionFactory.openSession();
        try {
            return session.find(OrderItemEntity.class, detailId);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateAnItem(OrderItemEntity updated) {
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
        Long detailId = Long.parseLong(id);

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            OrderItemEntity entity = session.find(OrderItemEntity.class, detailId);
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