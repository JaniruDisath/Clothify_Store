package model.dto;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "customer")


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CustomerEntity {
    @Id
    private String id;
    private String name;
    private String phone;
    private String email;
    private String address;
}
