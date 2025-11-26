package student.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "walkin_customer")
public class WalkInCustomerEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String customerId;   // WIC-YYMMDD-XXXX

}