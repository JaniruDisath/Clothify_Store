package student.mappers;

import student.model.dto.WalkInCustomer;
import student.model.entity.WalkInCustomerEntity;

public class WalkInCustomerMapper {

    public static WalkInCustomer toDto(WalkInCustomerEntity entity) {
        if (entity == null) return null;
        return new WalkInCustomer(
                entity.getCustomerId()
        );
    }

    public static WalkInCustomerEntity toEntity(WalkInCustomer dto) {
        return new WalkInCustomerEntity(
                dto.getCustomerId()
        );
    }
}