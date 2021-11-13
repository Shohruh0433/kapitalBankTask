package uz.developer.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.task.models.entity.Detail;

public interface DetailRepository extends JpaRepository<Detail,Long> {
}
