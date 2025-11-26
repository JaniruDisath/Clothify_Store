package repository.order.orderItemTable;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import repository.ClothifyDatabase;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import student.model.entity.order.OrderItemEntity;

import java.util.List;

public class OrderItemTable implements ClothifyDatabase<OrderItemEntity> {

    @Override
    public List<OrderItemEntity> getAllData(Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<OrderItemEntity> cq = cb.createQuery(OrderItemEntity.class);
        cq.from(OrderItemEntity.class);
        return session.createQuery(cq).getResultList();
    }

    @Override
    public void insertAnItem(Session session, OrderItemEntity entity) {
        session.persist(entity);
    }

    @Override
    public OrderItemEntity getAnItem(Session session, String id) {
        Long detailId = Long.parseLong(id);
        return session.find(OrderItemEntity.class, detailId);

    }

    @Override
    public void updateAnItem(Session session, OrderItemEntity updated) {
        session.merge(updated);
    }

    @Override
    public void deleteAnItem(Session session, String id) {
        Long detailId = Long.parseLong(id);
        OrderItemEntity entity = session.find(OrderItemEntity.class, detailId);
        if (entity != null)
            session.remove(entity);
    }

    @Override
    public OrderItemEntity getLastAddedItem(Session session) {
        return null;
    }
}