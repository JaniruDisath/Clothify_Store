package repository.itemsTable;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repository.ClothifyDatabase;
import student.model.entity.ItemEntity;
import student.model.entity.LoyaltyCustomerEntity;
import student.util.HibernateUtil;

import java.util.List;

public class ItemTable implements ClothifyDatabase<ItemEntity> {


    @Override
    public List<ItemEntity> getAllData(Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ItemEntity> cq = cb.createQuery(ItemEntity.class);
        cq.from(ItemEntity.class);
        return session.createQuery(cq).getResultList();

    }

    @Override
    public void insertAnItem(Session session, ItemEntity entity) {
        session.persist(entity);
    }

    @Override
    public ItemEntity getAnItem(Session session, String id) {
        return session.find(ItemEntity.class, id);
    }

    @Override
    public void updateAnItem(Session session, ItemEntity updated) {
        ItemEntity existing = session.find(ItemEntity.class, updated.getItemCode());
        if (existing != null) {
            existing.setName(updated.getName());
            existing.setBrand(updated.getBrand());
            existing.setCategory(updated.getCategory());
            existing.setColor(updated.getColor());
            existing.setSize(updated.getSize());
            existing.setQuantity(updated.getQuantity());
            existing.setUnitPrice(updated.getUnitPrice());
            existing.setDiscount(updated.getDiscount());
            existing.setImagePath(updated.getImagePath());

            session.merge(existing);
        }
    }

    @Override
    public void deleteAnItem(Session session, String id) {
        ItemEntity entity = session.find(ItemEntity.class, id);
        if (entity != null)
            session.remove(entity);
    }

    @Override
    public ItemEntity getLastAddedItem(Session session) {
        return null;
    }
}
