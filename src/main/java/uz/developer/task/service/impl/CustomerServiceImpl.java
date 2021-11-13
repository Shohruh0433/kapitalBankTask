package uz.developer.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.task.models.custom.Response;
import uz.developer.task.models.custom.Status;
import uz.developer.task.models.entity.Customer;
import uz.developer.task.models.projection.forTask.CustomersLastOrdersProjection;
import uz.developer.task.repository.CustomerRepository;
import uz.developer.task.service.in.CustomerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Response findAllCustomersWithoutOrders(Integer year, Pageable pageable) {
        Response response = new Response();
        Status status;
        Page<Customer> allCustomersWithoutOrders = customerRepository.findAllCustomersWithoutOrders(year,pageable);
        if (!allCustomersWithoutOrders.isEmpty()) {
            status = new Status(0, "natija");
            response.setStatus(status);
            response.setData(allCustomersWithoutOrders);
        } else {
            status = new Status(-1, "hech narsa topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response findCustomersLastOrders(Pageable pageable) {
        Response response = new Response();
        Status status;
        Page<CustomersLastOrdersProjection> customersLastOrders = customerRepository.findCustomersLastOrders(pageable);
        if (!customersLastOrders.isEmpty()) {
            status = new Status(0, "natija");
            response.setStatus(status);
            response.setData(customersLastOrders);
        } else {
            status = new Status(-1, "hech narsa topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }
}
