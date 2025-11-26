package repository.walkInCustomerTable;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import repository.ClothifyDatabase;
import student.model.entity.WalkInCustomerEntity;

import java.util.List;

public class WalkInCustomerTable implements ClothifyDatabase<WalkInCustomerEntity> {

    @Override
    public List<WalkInCustomerEntity> getAllData(Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<WalkInCustomerEntity> cq = cb.createQuery(WalkInCustomerEntity.class);
        cq.from(WalkInCustomerEntity.class);
        return session.createQuery(cq).getResultList();
    }

    @Override
    public void insertAnItem(Session session, WalkInCustomerEntity entity) {
        session.persist(entity);
    }

    @Override
    public WalkInCustomerEntity getAnItem(Session session, String id) {
        return session.find(WalkInCustomerEntity.class, id);
    }

    @Override
    public void updateAnItem(Session session, WalkInCustomerEntity updated) {
        WalkInCustomerEntity existing = session.find(WalkInCustomerEntity.class, updated.getCustomerId());
        if (existing != null) {
            existing.setCustomerId(updated.getCustomerId());
            session.merge(existing);
        }
    }

    @Override
    public void deleteAnItem(Session session, String id) {
        WalkInCustomerEntity entity = session.find(WalkInCustomerEntity.class, id);
        if (entity != null)
            session.remove(entity);
    }

    @Override
    public WalkInCustomerEntity getLastAddedItem(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<WalkInCustomerEntity> cq = cb.createQuery(WalkInCustomerEntity.class);
        Root<WalkInCustomerEntity> root = cq.from(WalkInCustomerEntity.class);

        cq.select(root);
        cq.orderBy(cb.desc(root.get("customerId")));

        return session.createQuery(cq)
                .setMaxResults(1)
                .uniqueResult();
    }


}
