package test.task.skyeng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "postal_office")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostalOfficeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String index;
    private String name;
    private String address;
}
