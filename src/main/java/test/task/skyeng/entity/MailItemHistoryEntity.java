package test.task.skyeng.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Enumerated(EnumType.STRING)
    private InteractionType interactionType;
    private LocalDateTime timestamp;

    private Long currentOffice;

    private String officeToDelivery;

}