package uz.developer.task.service.in;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import uz.developer.task.models.custom.Response;
import uz.developer.task.models.dto.PostOrderDto;

import java.util.Date;

public interface OrderService {

    Response addOrder(PostOrderDto postOrderDto);

    Response getOrderById(Long orderId);

    Response findAllOrdersWithoutDetails(Pageable pageable);

    Response findAllNumberOfProductsInYear(Integer year,Pageable pageable);

    Response findAllOrdersWithoutInvoices(Pageable pageable);
}
