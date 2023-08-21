package test.task.skyeng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.task.skyeng.entity.enums.ItemType;

import javax.persistence.*;

@Entity
@Table(name = "mail_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Column(name = "recipient_index")
    private String recipientIndex;
    @Column(name = "recipient_address")
    private String recipientAddress;
    @Column(name = "recipient_name")
    private String recipientName;

}
