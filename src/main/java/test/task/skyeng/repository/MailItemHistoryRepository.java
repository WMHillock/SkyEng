package test.task.skyeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.MailItemHistoryEntity;

import java.util.List;

public interface MailItemHistoryRepository extends JpaRepository<MailItemHistoryEntity, Long> {

    List<MailItemHistoryEntity> findByMailItemOrderByInteractionDate(MailItemEntity mailItemEntity);
}
