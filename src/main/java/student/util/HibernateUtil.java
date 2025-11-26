package student.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        try {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");

            // REGISTER ALL ENTITIES HERE
            cfg.addAnnotatedClass(student.model.entity.LoyaltyCustomerEntity.class);
            cfg.addAnnotatedClass(student.model.entity.ItemEntity.class);
            cfg.addAnnotatedClass(student.model.entity.order.OrderEntity.class);
            cfg.addAnnotatedClass(student.model.entity.order.OrderItemEntity.class);
            cfg.addAnnotatedClass(student.model.entity.transaction.PaymentTransactionEntity.class);
            cfg.addAnnotatedClass(student.model.entity.transaction.CashTransactionEntity.class);
            cfg.addAnnotatedClass(student.model.entity.transaction.CardTransactionEntity.class);
            cfg.addAnnotatedClass(student.model.entity.WalkInCustomerEntity.class);

            return cfg.buildSessionFactory();

        } catch (Exception e) {
            throw new RuntimeException("Error building SessionFactory", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
