package uz.developer.task.service.in;

import org.springframework.data.domain.Pageable;
import uz.developer.task.models.custom.Response;

public interface ProductService {

    Response findAllHighDemandProducts(Integer value, Pageable pageable);

    Response findAllBulkProducts(Integer quantity,Pageable pageable);

    Response getAllProduct(Pageable pageable);

    Response getByProductId(Long productId);
}
