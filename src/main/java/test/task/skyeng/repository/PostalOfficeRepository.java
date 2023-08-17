package test.task.skyeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.skyeng.entity.PostalOfficeEntity;

@Repository
public interface PostalOfficeRepository extends JpaRepository<PostalOfficeEntity, Long> {

    PostalOfficeEntity findByIndex(String index);
}
