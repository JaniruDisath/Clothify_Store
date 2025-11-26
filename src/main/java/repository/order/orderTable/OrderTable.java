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

    @Override
    public List<OrderEntity> getAllData(Session session) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<OrderEntity> cq = cb.createQuery(OrderEntity.class);
            cq.from(OrderEntity.class);
            return session.createQuery(cq).getResultList();
    }

    @Override
    public void insertAnItem(Session session,OrderEntity entity) {
            session.persist(entity);
    }

    @Override
    public OrderEntity getAnItem(Session session,String id) {
            return session.find(OrderEntity.class, id);
    }

    @Override
    public void updateAnItem(Session session,OrderEntity updated) {
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
    }

    @Override
    public void deleteAnItem(Session session,String id) {
            OrderEntity entity = session.find(OrderEntity.class, id);
            if (entity != null)
                session.remove(entity);
    }

    @Override
    public OrderEntity getLastAddedItem(Session session) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<OrderEntity> cq = cb.createQuery(OrderEntity.class);
            Root<OrderEntity> root = cq.from(OrderEntity.class);

            cq.select(root);
            cq.orderBy(cb.desc(root.get("orderId")));

            return session.createQuery(cq)
                    .setMaxResults(1)
                    .uniqueResult();
    }


}