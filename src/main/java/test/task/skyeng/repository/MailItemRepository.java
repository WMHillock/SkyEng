package test.task.skyeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.task.skyeng.entity.MailItemEntity;

public interface MailItemRepository extends JpaRepository<MailItemEntity, Long> {
}
