package student.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoyaltyCustomer {
    private String phone;
    private String name;
    private String email;
}
