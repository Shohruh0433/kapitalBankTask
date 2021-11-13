package uz.developer.task.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.developer.task.models.entity.Category;
import uz.developer.task.models.projection.CategoryProjection;

import java.util.Date;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query(value = "select id as id,name as name from category ", nativeQuery = true)
    Page<CategoryProjection> findAllCategory(Pageable pageable);

    @Query(value = "select c.id as id, c.name as name " +
            "from category c " +
            "         join product p on c.id = p.category_id " +
            "where p.id = :productId ", nativeQuery = true)
    CategoryProjection findByProductId(@Param("productId") Long productId);

}
