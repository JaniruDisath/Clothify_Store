package student.services.db.loyaltyCustomer;

import student.model.dto.LoyaltyCustomer;

import java.io.IOException;

public interface LoyaltyCustomerService {
    LoyaltyCustomer getLoyaltyCustomer(String id);
    LoyaltyCustomer addLoyaltyCustomer(LoyaltyCustomer loyaltyCustomer) throws IOException;
}
