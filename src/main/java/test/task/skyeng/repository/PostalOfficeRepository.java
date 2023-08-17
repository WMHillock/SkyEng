package test.task.skyeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.task.skyeng.entity.PostalOfficeEntity;

public interface PostalOfficeRepository extends JpaRepository<PostalOfficeEntity, Long> {

    PostalOfficeEntity findByIndex(String index);
}
