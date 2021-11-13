package uz.developer.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.task.models.custom.Response;
import uz.developer.task.models.custom.Status;
import uz.developer.task.models.projection.ProductProjection;
import uz.developer.task.models.projection.forTask.BulkProductsProjection;
import uz.developer.task.models.projection.forTask.HighDemandProductsProjection;
import uz.developer.task.repository.ProductRepository;
import uz.developer.task.service.in.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Response findAllHighDemandProducts(Integer value,Pageable pageable) {
        Response response = new Response();
        Status status;
        Boolean checkValue = checkValue(value);
        if (!checkValue) {
            status = new Status(-3, "qiymat xato kiritildi");
            response.setStatus(status);
            response.setData(null);
            return response;
        }
        Page<HighDemandProductsProjection> allHighDemandProducts = productRepository.findAllHighDemandProducts(value,pageable);
        if (!allHighDemandProducts.isEmpty()) {
            status = new Status(0, "natija");
            response.setStatus(status);
            response.setData(allHighDemandProducts);
        } else {
            status = new Status(-1, "hech narsa topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response findAllBulkProducts(Integer quantity, Pageable pageable) {
        Response response = new Response();
        Status status;
        Boolean checkValue = checkValue(quantity);
        if (!checkValue) {
            status = new Status(-3, "qiymat xato kiritildi");
            response.setStatus(status);
            response.setData(null);
            return response;
        }
        Page<BulkProductsProjection> allBulkProducts = productRepository.findAllBulkProducts(quantity,pageable);
        if (!allBulkProducts.isEmpty()) {
            status = new Status(0, "natija");
            response.setStatus(status);
            response.setData(allBulkProducts);
        } else {
            status = new Status(-1, "hech narsa topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response getAllProduct(Pageable pageable) {
        Response response = new Response();
        Status status;
        Page<ProductProjection> all = productRepository.getAll(pageable);
        if (!all.isEmpty()) {
            status = new Status(0, "natija");
            response.setStatus(status);
            response.setData(all);
        } else {
            status = new Status(-1, "hech narsa topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response getByProductId(Long productId) {
        Response response = new Response();
        Status status;
        ProductProjection productProjection = productRepository.getByProductId(productId);
        if (productProjection != null) {
            status = new Status(0, "natija");
            response.setStatus(status);
            response.setData(productProjection);
        } else {
            status = new Status(-1, "hech narsa topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }

    private Boolean checkValue(Integer value) {
        if (value != null && value > 0) return true;
        else return false;
    }

}
