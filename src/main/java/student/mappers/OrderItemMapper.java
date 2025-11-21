package student.mappers;


import student.model.dto.order.OrderItem;
import student.model.entity.order.OrderItemEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderItemMapper {

    public static OrderItem toModel(OrderItemEntity entity) {
        if (entity == null) return null;

        return new OrderItem(
                entity.getItemCode(),
                entity.getName(),
                entity.getQuantity(),
                entity.getUnitPrice(),
                entity.getDiscountPerUnit(),
                entity.getLineTotal()
        );
    }

    public static OrderItemEntity toEntity(OrderItem model) {
        if (model == null) return null;

        OrderItemEntity entity = new OrderItemEntity();

        entity.setItemCode(model.getItemCode());
        entity.setQuantity(model.getQuantity());
        entity.setUnitPrice(model.getUnitPrice());
        entity.setDiscountPerUnit(model.getDiscountPerUnit());
        entity.setLineTotal(model.getLineTotal());

        return entity;
    }

    // Helpers for list conversions
    public static List<OrderItem> toModelList(List<OrderItemEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(OrderItemMapper::toModel).collect(Collectors.toList());
    }

    public static List<OrderItemEntity> toEntityList(List<OrderItem> models) {
        if (models == null) return null;
        return models.stream().map(OrderItemMapper::toEntity).collect(Collectors.toList());
    }
}
