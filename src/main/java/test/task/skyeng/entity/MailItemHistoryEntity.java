package test.task.skyeng.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.task.skyeng.entity.enums.InteractionType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailItemHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "mail_item_id")
    private MailItemEntity mailItem;
    @Column(name = "interaction_type")
    private InteractionType interactionType;
    private LocalDateTime timestamp;
    @ManyToOne
    private PostalOfficeEntity currentOffice;
    @ManyToOne
    private PostalOfficeEntity officeToDelivery;

}