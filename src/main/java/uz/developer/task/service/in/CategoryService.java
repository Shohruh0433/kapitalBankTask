package uz.developer.task.service.in;

import org.springframework.data.domain.Pageable;
import uz.developer.task.models.custom.Response;

public interface CategoryService {
    Response getAllCategory(Pageable pageable);

    Response getCategoryByProductId(Long productId);


}
