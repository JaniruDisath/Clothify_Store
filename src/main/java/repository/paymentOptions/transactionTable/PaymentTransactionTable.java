package repository.paymentOptions.transactionTable;

import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import repository.ClothifyDatabase;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import student.model.entity.transaction.PaymentTransactionEntity;

import java.util.List;

public class PaymentTransactionTable implements ClothifyDatabase<PaymentTransactionEntity> {

    @Override
    public List<PaymentTransactionEntity> getAllData(Session session) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<PaymentTransactionEntity> cq = cb.createQuery(PaymentTransactionEntity.class);
            cq.from(PaymentTransactionEntity.class);
            return session.createQuery(cq).getResultList();
    }

    @Override
    public void insertAnItem(Session session, PaymentTransactionEntity entity) {
            session.persist(entity);
    }

    @Override
    public PaymentTransactionEntity getAnItem(Session session, String id) {
            return session.find(PaymentTransactionEntity.class, id);
    }

    @Override
    public void updateAnItem(Session session, PaymentTransactionEntity updated) {
            session.merge(updated);
    }

    @Override
    public void deleteAnItem(Session session,String id) {
            PaymentTransactionEntity entity = session.find(PaymentTransactionEntity.class, id);
            if (entity != null)
                session.remove(entity);
    }

    @Override
    public PaymentTransactionEntity getLastAddedItem(Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<PaymentTransactionEntity> cq = cb.createQuery(PaymentTransactionEntity.class);
        Root<PaymentTransactionEntity> root = cq.from(PaymentTransactionEntity.class);

        cq.select(root);
        cq.orderBy(cb.desc(root.get("transactionId")));

        return session.createQuery(cq)
                .setMaxResults(1)
                .uniqueResult();
    }

}