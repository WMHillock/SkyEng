package test.task.skyeng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "mail_iltem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ItemType type;

    @Column(name = "recepient_index")
    private String recipientIndex;
    @Column(name = "recepient_address")
    private String recipientAddress;
    @Column(name = "recepient_name")
    private String recipientName;

}
