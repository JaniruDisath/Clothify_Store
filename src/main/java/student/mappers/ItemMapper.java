package student.mappers;

import student.model.dto.Item;
import student.model.entity.ItemEntity;

public class ItemMapper {

    public static Item itemToModel(ItemEntity entity) {
        if (entity == null) return null;

        return new Item(
                entity.getItemCode(),
                entity.getName(),
                entity.getBrand(),
                entity.getCategory(),
                entity.getSize(),
                entity.getColor(),
                entity.getUnitPrice(),
                entity.getQuantity(),
                entity.getDiscount(),
                entity.getImagePath()
        );
    }

    public static ItemEntity itemToEntity(Item model) {
        return new ItemEntity(
                model.getCode(),
                model.getName(),
                model.getBrand(),
                model.getCategory(),
                model.getSize(),
                model.getColor(),
                model.getPrice(),
                model.getQty(),
                model.getDiscount(),
                model.getImagePath()
        );
    }
}
