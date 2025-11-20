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

import java.util.List;

public class ItemTable implements ClothifyDatabase<ItemEntity> {

    private static final SessionFactory sessionFactory =
            new Configuration()
                    .addAnnotatedClass(ItemEntity.class)
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();

    @Override
    public List<ItemEntity> getAllData() {
        Session session = sessionFactory.openSession();

        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ItemEntity> cq = cb.createQuery(ItemEntity.class);
            cq.from(ItemEntity.class);
            return session.createQuery(cq).getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public void insertAnItem(ItemEntity entity) {
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
    public ItemEntity getAnItem(String id) {
        Session session = sessionFactory.openSession();

        try {
            return session.find(ItemEntity.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateAnItem(ItemEntity updated) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

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
            ItemEntity entity = session.find(ItemEntity.class, id);
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
