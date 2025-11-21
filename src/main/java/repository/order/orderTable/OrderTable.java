package repository.order.orderTable;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import repository.ClothifyDatabase;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import student.model.entity.order.OrderEntity;

import java.util.List;

public class OrderTable implements ClothifyDatabase<OrderEntity> {

    private static final SessionFactory sessionFactory =
            new Configuration()
                    .addAnnotatedClass(OrderEntity.class)
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();

    @Override
    public List<OrderEntity> getAllData() {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<OrderEntity> cq = cb.createQuery(OrderEntity.class);
            cq.from(OrderEntity.class);
            return session.createQuery(cq).getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public void insertAnItem(OrderEntity entity) {
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
    public OrderEntity getAnItem(String id) {
        Session session = sessionFactory.openSession();
        try {
            return session.find(OrderEntity.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateAnItem(OrderEntity updated) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            OrderEntity existing = session.find(OrderEntity.class, updated.getOrderId());
            if (existing != null) {
                existing.setOrderTime(updated.getOrderTime());
                existing.setCashierId(updated.getCashierId());
                existing.setLoyaltyPhone(updated.getLoyaltyPhone());
                existing.setSubtotal(updated.getSubtotal());
                existing.setDiscountTotal(updated.getDiscountTotal());
                existing.setGrandTotal(updated.getGrandTotal());
                existing.setPaymentType(updated.getPaymentType());
                session.merge(existing);
            }

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
            OrderEntity entity = session.find(OrderEntity.class, id);
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

    @Override
    public OrderEntity getLastAddedItem() {
        Session session = sessionFactory.openSession();

        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<OrderEntity> cq = cb.createQuery(OrderEntity.class);
            Root<OrderEntity> root = cq.from(OrderEntity.class);

            cq.select(root);
            cq.orderBy(cb.desc(root.get("orderId")));

            return session.createQuery(cq)
                    .setMaxResults(1)
                    .uniqueResult();

        } finally {
            session.close();
        }
    }


}