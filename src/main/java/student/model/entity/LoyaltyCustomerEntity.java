package student.model.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "customer")


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class LoyaltyCustomerEntity {
    @Id
    private String phone;
    private String name;
    private String email;
}
