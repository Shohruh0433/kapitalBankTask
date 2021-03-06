package uz.developer.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.developer.task.models.custom.Response;
import uz.developer.task.service.in.CustomerService;
import uz.developer.task.service.in.InvoiceService;
import uz.developer.task.service.in.OrderService;
import uz.developer.task.service.in.ProductService;
import uz.developer.task.utils.CommonUtils;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class GeneralController {
    private final InvoiceService invoiceService;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final ProductService productService;

    /*
        1.	IInvoices issued after their due date. Return all attributes.
    */
    @GetMapping("expired_invoices")
    public Response allExpiredInvoices(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                       @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        return invoiceService.findAllExpiredInvoices(new Date(), CommonUtils.getPageableNew(page,size));
    }

    /*
        2.	Invoices that were issued before the date in which the order they refer to was placed.
       Return the ID of the invoice, the date it was issued, the ID of the order associated with it
       and the date the order was placed
      */
    @GetMapping("wrong_date_invoices")
    public Response allWrongDateInvoices(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                         @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        return invoiceService.findAllWrongDateInvoices( CommonUtils.getPageableNew(page,size));
    }

    /*
   3. Orders that do not have a detail and were placed before 6 September 2016. Return all
        attributes.
       */
    @GetMapping("orders_without_details")
    public Response allOrdersWithoutDetails(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        return orderService.findAllOrdersWithoutDetails(CommonUtils.getPageableNew(page,size));
    }


    /*
        4.Customers who have not placed any orders in 2016. Return all attributes.
         */
    @GetMapping("customers_without_orders")
    public Response allCustomersWithoutOrders(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                              @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                              @RequestParam(value = "year", defaultValue = "2016", required = false) Integer year
                                              ) {
        return customerService.findAllCustomersWithoutOrders(year,CommonUtils.getPageableNew(page,size));
    }

    /*
     5.	ID and name of customers and the date of their last order. For customers who did not
        place any orders, no rows must be returned. For each customer who placed more than
        one order on the date of their most recent order, only one row must be returned.
      */
    @GetMapping("customers_last_orders")
    public Response allCustomersLastOrders(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                           @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        return customerService.findCustomersLastOrders(CommonUtils.getPageableNew(page,size));
    }

    /*
       6.	Invoices that have been overpaid. Observe that there may be more than one payment
           referring to the same invoice. Return the invoice number and the amount that should be
           reimbursed.
      */
    @GetMapping("overpaid_invoices")
    public Response allOverpaidInvoices(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                        @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        return invoiceService.findAllOverpaidInvoices(CommonUtils.getPageableNew(page,size));
    }

    /*
     7.	Products that were ordered more than 10 times in total, by taking into account the
      quantities in which they appear in the order details. Return the product code and the
      total number of times it was ordered.
     */
    @GetMapping("high_demand_products")
    public Response AllHighDemandProducts(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                          @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                          @RequestParam(value = "value", defaultValue = "10", required = false) Integer value) {
        return productService.findAllHighDemandProducts(value,CommonUtils.getPageableNew(page,size));
    }

    /*
       8.	Products that are usually ordered in bulk: whenever one of these products is ordered, it
        is ordered in a quantity that on average is equal to or greater than 8. For each such
        product, return product code and price.
        */
    @GetMapping("bulk_products")
    public Response AllBulkProducts(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                    @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                    @RequestParam(value = "quantity", defaultValue = "8", required = false) Integer quantity) {
        return productService.findAllBulkProducts(quantity,CommonUtils.getPageableNew(page,size));
    }

    /*
       9.Total number of orders placed in 2016 by customers of each country. If all customers
           from a specific country did not place any orders in 2016, the country will not appear in
           the output.
           */
    @GetMapping("number_of_products_in_year")
    public Response allNumberOfProductsInYear(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                              @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                              @RequestParam(value = "year", defaultValue = "2016", required = false) Integer year) {
        return orderService.findAllNumberOfProductsInYear(year,CommonUtils.getPageableNew(page,size));
    }

    /*
      10.	For each order without invoice, list its ID, the date it was placed and the total price of the
        products in its detail, taking into account the quantity of each ordered product and its unit
        price. Orders without detail must not be included in the answers.
          */
    @GetMapping("orders_without_invoices")
    public Response allOrdersWithoutInvoices(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                             @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        return orderService.findAllOrdersWithoutInvoices(CommonUtils.getPageableNew(page,size));
    }

}
