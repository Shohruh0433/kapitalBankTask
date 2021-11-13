package uz.developer.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.developer.task.models.custom.Response;
import uz.developer.task.service.in.CategoryService;
import uz.developer.task.service.in.ProductService;
import uz.developer.task.utils.CommonUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public Response getAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                           @RequestParam(value = "size", defaultValue = "10", required = false) Integer size
                           ) {
        return productService.getAllProduct( CommonUtils.getPageableNew(page,size));
    }

    @GetMapping()
    public Response getByProductId(@RequestParam(value = "productId") Long productId) {
        return productService.getByProductId(productId);
    }

}
