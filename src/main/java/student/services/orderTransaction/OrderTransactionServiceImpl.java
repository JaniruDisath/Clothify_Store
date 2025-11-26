package student.services.orderTransaction;

import org.hibernate.Session;

import org.hibernate.Transaction;
import repository.ClothifyDatabase;
import repository.itemsTable.ItemTable;
import repository.order.orderItemTable.OrderItemTable;
import repository.order.orderTable.OrderTable;
import repository.paymentOptions.cardTransactionTable.CardTransactionTable;
import repository.paymentOptions.cashTransactionTable.CashTransactionTable;
import repository.paymentOptions.transactionTable.PaymentTransactionTable;
import student.controller.POSController.PosControllerImpl;
import student.mappers.*;
import student.model.dto.order.Order;
import student.model.dto.order.OrderItem;
import student.model.dto.transaction.CardTransaction;
import student.model.dto.transaction.CashTransaction;
import student.model.entity.ItemEntity;
import student.model.entity.WalkInCustomerEntity;
import student.model.entity.order.OrderEntity;
import student.model.entity.order.OrderItemEntity;
import student.model.entity.transaction.CardTransactionEntity;
import student.model.entity.transaction.CashTransactionEntity;
import student.model.entity.transaction.PaymentTransactionEntity;
import student.services.db.order.OrderService;
import student.services.db.order.OrderServiceImpl;
import student.services.db.paymentTransaction.PaymentTransactionService;
import student.services.db.paymentTransaction.PaymentTransactionServiceImpl;
import student.services.db.walkinCustomer.WalkInCustomerService;
import student.services.db.walkinCustomer.WalkInCustomerServiceImpl;
import student.singleton.CartManager;
import student.util.HibernateUtil;

import java.time.LocalDateTime;

public class OrderTransactionServiceImpl implements OrderTransactionService {

    private final Session session = HibernateUtil.getSessionFactory().openSession();

    private final ClothifyDatabase<OrderEntity> orderTable = new OrderTable();
    private final ClothifyDatabase<OrderItemEntity> orderItemTable = new OrderItemTable();
    private final ClothifyDatabase<PaymentTransactionEntity> transactionEntityClothifyDatabase = new PaymentTransactionTable();
    private final ClothifyDatabase<CashTransactionEntity> cashTransactionTable = new CashTransactionTable();
    private final ClothifyDatabase<CardTransactionEntity> cardTransactionTable = new CardTransactionTable();
    private final ClothifyDatabase<ItemEntity> itemTable = new ItemTable();

    private final OrderService orderService = new OrderServiceImpl();
    private final PaymentTransactionService paymentTransactionService = new PaymentTransactionServiceImpl();
    private final WalkInCustomerService walkInCustomerService = new WalkInCustomerServiceImpl();

    public void addOrder(CashTransaction cashTransaction, CardTransaction cardTransaction) {
        Order order = getOrder();

        if (PosControllerImpl.getInstance().getLoyaltyCustomer()!=null){
            order.setLoyaltyPhone(PosControllerImpl.getInstance().getLoyaltyCustomer().getPhone());
        }else{
            order.setLoyaltyPhone(walkInCustomerService.generateWalkInCustomerId());
            session.persist(new WalkInCustomerEntity(walkInCustomerService.generateWalkInCustomerId()));
        }
        order.setPaymentType((cashTransaction != null) ? "cash" : "card ");

        OrderEntity orderEntity = OrderMapper.toEntity(order);

        PaymentTransactionEntity paymentTransactionEntity = new PaymentTransactionEntity(
                paymentTransactionService.generateTransactionId(),
                orderEntity,
                order.getOrderTime(),
                order.getGrandTotal(),
                order.getPaymentType()
        );

        if (cashTransaction != null) {
            cashTransaction.setTransactionId(paymentTransactionEntity.getTransactionId());
        } else {
            cardTransaction.setTransactionId(paymentTransactionEntity.getTransactionId());
        }
        Transaction tx = null;
        try {
            tx = session.beginTransaction();


            orderTable.insertAnItem(session, orderEntity);

            for (OrderItem orderItem : order.getItems()) {
                orderItem.setOrderId(order.getOrderId());
                orderItemTable.insertAnItem(session, OrderItemMapper.toEntity(orderItem));
            }

            transactionEntityClothifyDatabase.insertAnItem(session, paymentTransactionEntity);

            if (paymentTransactionEntity.getPaymentType().equals("cash")) {
                cashTransactionTable.insertAnItem(session, CashTransactionMapper.toEntity(cashTransaction));
            } else {
                cardTransactionTable.insertAnItem(session, CardTransactionMapper.toEntity(cardTransaction));
            }

            for (OrderItem orderItem : order.getItems()) {
                ItemEntity anItem = itemTable.getAnItem(session, orderItem.getItemCode());
                anItem.setQuantity(anItem.getQuantity() - orderItem.getQuantity());
                itemTable.updateAnItem(session, anItem);
            }



            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            CartManager.getInstance().resetCart();
            session.close();
        }
    }

    public Order getOrder() {
        return new Order(
                orderService.generateOrderId(),
                LocalDateTime.now(),
                "Temp-ID",
                "",
                CartManager.getInstance().getTotal(),
                CartManager.getInstance().getTotalDiscount(),
                CartManager.getInstance().getTotal() - CartManager.getInstance().getTotalDiscount(),
                "",
                CartManager.getInstance().getOrderItemList()
        );
    }
}
