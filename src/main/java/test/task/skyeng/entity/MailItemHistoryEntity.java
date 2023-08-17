package test.task.skyeng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "item_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailItemHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MailItemEntity mailItem;

    @ManyToOne
    private PostalOfficeEntity sourceOffice;

    @ManyToOne
    private PostalOfficeEntity destinationOffice;

    private Date interactionDate;

    private InteractionType interactionType;


}