package uz.developer.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.task.models.custom.Response;
import uz.developer.task.models.custom.Status;
import uz.developer.task.models.projection.CategoryProjection;
import uz.developer.task.repository.CategoryRepository;
import uz.developer.task.repository.ProductRepository;
import uz.developer.task.service.in.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public Response getAllCategory(Pageable pageable) {
        Response response = new Response();
        Status status;
       Page<CategoryProjection> allCategory = categoryRepository.findAllCategory(pageable);
        if (!allCategory.isEmpty()) {
            status = new Status(0, "topilgan kategoriyalar");
            response.setStatus(status);
            response.setData(allCategory);
        } else {
            status = new Status(-1, "hech qanday kategory topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response getCategoryByProductId(Long productId) {
        Response response = new Response();
        Status status;
        boolean existsById = productRepository.existsById(productId);
        if (!existsById) {
            status = new Status(-1, "hech qanday kategory topilmadi");
            response.setStatus(status);
            response.setData(null);
            return response;
        }
        CategoryProjection category = categoryRepository.findByProductId(productId);
        if (category != null) {
            status = new Status(0, "topilgan kategoriyalar");
            response.setStatus(status);
            response.setData(category);
        } else {
            status = new Status(-1, "hech qanday kategory topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }
}
