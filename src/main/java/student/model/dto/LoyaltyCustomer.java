package student.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoyaltyCustomer {
    private String id;
    private String name;
    private String phone;
    private String email;
}
