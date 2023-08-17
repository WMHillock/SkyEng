package test.task.skyeng.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.skyeng.entity.enums.InteractionType;
import test.task.skyeng.entity.enums.ItemType;
import test.task.skyeng.listener.MailItemListener;

import javax.persistence.*;

@EntityListeners(MailItemListener.class)
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

    @Enumerated(EnumType.STRING)
    private InteractionType state;

    @Column(name = "recipient_index")
    private String recipientIndex;
    @Column(name = "recipient_address")
    private String recipientAddress;
    @Column(name = "recipient_name")
    private String recipientName;

    @ManyToOne
    private PostalOfficeEntity currentOffice;

}
