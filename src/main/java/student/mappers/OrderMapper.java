package student.mappers;



import student.model.dto.order.Order;
import student.model.entity.order.OrderEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static Order toModel(OrderEntity entity) {
        if (entity == null) return null;

        return new Order(
                entity.getOrderId(),
                entity.getOrderTime(),
                entity.getCashierId(),
                entity.getLoyaltyPhone(),
                entity.getSubtotal(),
                entity.getDiscountTotal(),
                entity.getGrandTotal(),
                entity.getPaymentType(),
                OrderItemMapper.toModelList(entity.getItems())
        );
    }

    public static OrderEntity toEntity(Order model) {
        if (model == null) return null;

        OrderEntity entity = new OrderEntity();

        entity.setOrderId(model.getOrderId());
        entity.setOrderTime(model.getOrderTime());
        entity.setCashierId(model.getCashierId());
        entity.setLoyaltyPhone(model.getLoyaltyPhone());
        entity.setSubtotal(model.getSubtotal());
        entity.setDiscountTotal(model.getDiscountTotal());
        entity.setGrandTotal(model.getGrandTotal());
        entity.setPaymentType(model.getPaymentType());

        // Map order items separately (service layer will attach relationships)
        return entity;
    }
}
