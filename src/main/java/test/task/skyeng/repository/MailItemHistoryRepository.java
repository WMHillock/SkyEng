package test.task.skyeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.skyeng.entity.MailItemHistoryEntity;

import java.util.List;

@Repository
public interface MailItemHistoryRepository extends JpaRepository<MailItemHistoryEntity, Long> {

    List<MailItemHistoryEntity> findByMailItemIdOrderByTimestampDesc(Long mailItemId);
}
