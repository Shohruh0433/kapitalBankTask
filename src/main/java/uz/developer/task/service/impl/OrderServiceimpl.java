package uz.developer.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.task.models.custom.Response;
import uz.developer.task.models.custom.Status;
import uz.developer.task.models.dto.PostOrderDto;
import uz.developer.task.models.entity.*;
import uz.developer.task.models.projection.DetailsByOrderIdProjection;
import uz.developer.task.models.projection.forTask.NumberOfProductsInYearProjection;
import uz.developer.task.models.projection.forTask.OrdersWithoutDetailsProjection;
import uz.developer.task.models.projection.forTask.OrdersWithoutInvoiceProjection;
import uz.developer.task.repository.*;
import uz.developer.task.service.in.OrderService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceimpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final DetailRepository detailRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public Response findAllOrdersWithoutDetails(Pageable pageable) {
        Response response = new Response();
        Status status;
        String sDate = "6/09/2016";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date date;
        try {
            date = formatter.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
            status = new Status(-2, "vaqtni convert qilishf=da xato");
            response.setStatus(status);
            return response;
        }
        Page<OrdersWithoutDetailsProjection> allOrdersWithoutDetails = orderRepository.findAllOrdersWithoutDetails(date,pageable);
        if (!allOrdersWithoutDetails.isEmpty()) {
            status = new Status(0, "natija");
            response.setStatus(status);
            response.setData(allOrdersWithoutDetails);
        } else {
            status = new Status(-1, "hech narsa topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response findAllNumberOfProductsInYear(Integer year,Pageable pageable) {
        Response response = new Response();
        Status status;
        Page<NumberOfProductsInYearProjection> allNumberOfProductsInYear = orderRepository.findAllNumberOfProductsInYear(year,pageable);
        if (!allNumberOfProductsInYear.isEmpty()) {
            status = new Status(0, "natija");
            response.setStatus(status);
            response.setData(allNumberOfProductsInYear);
        } else {
            status = new Status(-1, "hech narsa topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response addOrder(PostOrderDto postOrderDto) {
        Response response = new Response();
        Status status;
        Optional<Customer> optionalCustomer = customerRepository.findById(postOrderDto.getCustomerId());
        if (!optionalCustomer.isPresent()) {
            status = new Status(-1, "customer topilmadi");
            response.setStatus(status);
            return response;
        }
        Optional<Product> optionalProduct = productRepository.findById(postOrderDto.getProductId());
        if (!optionalProduct.isPresent()) {
            status = new Status(-1, "bunday mahsulot topilmadi");
            response.setStatus(status);
            return response;
        }
        if (postOrderDto.getQuantity() == null || postOrderDto.getQuantity() <= 0) {
            status = new Status(-1, "bunday qiymat mumkin emas");
            response.setStatus(status);
            return response;
        }

        Customer customer = optionalCustomer.get();
        Product product = optionalProduct.get();
        Order order = new Order();
        order.setCustomer(customer);
        order.setDate(new Date());
        Order savedOrder = orderRepository.save(order);

        Detail detail = new Detail();
        detail.setOrder(savedOrder);
        detail.setProduct(product);
        detail.setQuantity(postOrderDto.getQuantity());
        Detail savedDetail = detailRepository.save(detail);

        Invoice invoice = new Invoice();
        invoice.setOrder(savedOrder);
        invoice.setAmount(product.getPrice() * postOrderDto.getQuantity());
        invoice.setIssued(new Date());
        long day = 24 * 60 * 60 * 1000;
        invoice.setDue(new Date(new Date().getTime() + 5 * day));
        Invoice savedInvoice = invoiceRepository.save(invoice);

        status = new Status(0, "muvaffaqiyatli saqlandi");
        response.setStatus(status);
        response.setData(savedInvoice.getId());
        return response;
    }

    @Override
    public Response getOrderById(Long orderId) {
        Response response = new Response();
        Status status;
        DetailsByOrderIdProjection detailsByOrderId = orderRepository.getDetailsByOrderId(orderId);
        if (detailsByOrderId != null) {
            status = new Status(0, "topilgan order");
            response.setStatus(status);
            response.setData(detailsByOrderId);
        } else {
            status = new Status(-1, "hechnarsa topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response findAllOrdersWithoutInvoices(Pageable pageable) {
        Response response = new Response();
        Status status;
        Page<OrdersWithoutInvoiceProjection> allOrdersWithoutInvoices = orderRepository.findAllOrdersWithoutInvoices(pageable);
        if (allOrdersWithoutInvoices!= null) {
            status = new Status(0, "topilgan order");
            response.setStatus(status);
            response.setData(allOrdersWithoutInvoices);
        } else {
            status = new Status(-1, "hechnarsa topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }
}
