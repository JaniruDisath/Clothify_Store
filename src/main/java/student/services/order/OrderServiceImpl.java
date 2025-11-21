package student.services.order;

import repository.ClothifyDatabase;
import repository.order.orderTable.OrderTable;
import student.model.entity.order.OrderEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderServiceImpl implements OrderService {

    ClothifyDatabase<OrderEntity> service = new OrderTable();

    @Override
    public String generateOrderId() {

        OrderEntity lastOrder = service.getLastAddedItem();

        if (lastOrder == null) {
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
            return "ORD-" + today + "-0001";
        }

        String lastId = lastOrder.getOrderId();
        String[] parts = lastId.split("-");

        String lastDate = parts[1];
        int lastNumber = Integer.parseInt(parts[2]);

        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        int newCounter = today.equals(lastDate) ? lastNumber + 1 : 1;

        return String.format("ORD-%s-%04d", today, newCounter);
    }



}
