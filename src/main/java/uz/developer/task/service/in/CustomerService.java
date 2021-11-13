package uz.developer.task.service.in;

import org.springframework.data.domain.Pageable;
import uz.developer.task.models.custom.Response;

public interface CustomerService {
    Response findAllCustomersWithoutOrders(Integer year, Pageable pageable);

    Response findCustomersLastOrders(Pageable pageable);

}
