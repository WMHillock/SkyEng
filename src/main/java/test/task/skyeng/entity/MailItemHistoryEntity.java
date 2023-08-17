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
    @Column(name = "mail_item_id")
    private Long mailItemId;
    @Column(name = "interaction_type")
    private InteractionType interactionType;
    private LocalDateTime timestamp;
    @ManyToOne
    private PostalOfficeEntity currentOffice;
    @ManyToOne
    private PostalOfficeEntity officeToDelivery;

    public static MailItemHistoryEntity fromMailItemEntity(MailItemEntity mailItemEntity) {
        return MailItemHistoryEntity.builder()
                .mailItemId(mailItemEntity.getId())
                .interactionType(mailItemEntity.getState())
                .timestamp(LocalDateTime.now())
                .currentOffice(mailItemEntity.getCurrentOffice())
                .officeToDelivery(null)
                .build();
    }
}