package student.services.loyaltyCustomer;

import repository.ClothifyDatabase;
import repository.loyaltyCustomerTable.LoyaltyCustomerTable;
import student.model.dto.LoyaltyCustomer;
import student.model.entity.LoyaltyCustomerEntity;

public class LoyaltyCustomerServiceImpl implements LoyaltyCustomerService {

    private final ClothifyDatabase<LoyaltyCustomerEntity> database = new LoyaltyCustomerTable();

    @Override
    public LoyaltyCustomer getLoyaltyCustomer(String id) {
        LoyaltyCustomerEntity entity = database.getAnItem(id);
        return mapToModel(entity);
    }


    public boolean customerExists(String phone) {
        return database.getAnItem(phone) != null;
    }

    @Override
    public LoyaltyCustomer addLoyaltyCustomer(LoyaltyCustomer model) {

        // Already exists → return existing customer
        if (customerExists(model.getPhone())) {
            return getLoyaltyCustomer(model.getPhone());
        }

        // Create new
        database.insertAnItem(mapToEntity(model));
        return model;
    }

    private LoyaltyCustomer mapToModel(LoyaltyCustomerEntity entity) {
        if (entity == null) return null;
        return new LoyaltyCustomer(
                entity.getPhone(),
                entity.getName(),
                entity.getEmail()
        );
    }

    private LoyaltyCustomerEntity mapToEntity(LoyaltyCustomer model) {
        if (model == null) return null;
        return new LoyaltyCustomerEntity(
                model.getPhone(),
                model.getName(),
                model.getEmail()
        );
    }
}

