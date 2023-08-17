package test.task.skyeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.skyeng.entity.MailItemEntity;

import java.util.Optional;

@Repository
public interface MailItemRepository extends JpaRepository<MailItemEntity, Long> {

    Optional<MailItemEntity> findById(Long id);
}
