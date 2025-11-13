package model.entity;


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
    private String id;
    private String name;
    private String phone;
    private String email;
}
