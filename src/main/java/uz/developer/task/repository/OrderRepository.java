package uz.developer.task.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.developer.task.models.entity.Order;
import uz.developer.task.models.projection.DetailsByOrderIdProjection;
import uz.developer.task.models.projection.forTask.NumberOfProductsInYearProjection;
import uz.developer.task.models.projection.forTask.OrdersWithoutDetailsProjection;
import uz.developer.task.models.projection.forTask.OrdersWithoutInvoiceProjection;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /*
    3.Orders that do not have a detail and were placed before 6 September 2016. Return all
    attributes.
    */
    @Query(value = "select o.id as orderId, o.date as orderDate,c.name as customerName, c.address as address,\n" +
            "       c.phone as phone ,c2.name as country\n" +
            "from orders o\n" +
            " left join detail d on o.id = d.ord_id\n" +
            "join customer c on c.id = o.cust_id\n" +
            "join country c2 on c2.id = c.country_id\n" +
            "where d.ord_id is Null and  o.date<=:date", nativeQuery = true)
    Page<OrdersWithoutDetailsProjection> findAllOrdersWithoutDetails(@Param("date") Date date, Pageable pageable);


    /*
   9.	Total number of orders placed in 2016 by customers of each country. If all customers
    from a specific country did not place any orders in 2016, the country will not appear in
    the output.
   */
    @Query(value = "select c2.name as country, count(c.id)  as countOrder from orders o " +
            "    join customer c on c.id = o.cust_id " +
            "join country c2 on c2.id = c.country_id where  extract(year from o.date)=:year " +
            "group by c2.id", nativeQuery = true)
    Page<NumberOfProductsInYearProjection> findAllNumberOfProductsInYear(@Param("year") Integer year, Pageable pageable);

    /*
      10.	For each order without invoice, list its ID, the date it was placed and the total price of the
    products in its detail, taking into account the quantity of each ordered product and its unit
    price. Orders without detail must not be included in the answers.
  */
    @Query(value = "select order_details.order_id as orderId, order_date as orderDate, sum(price) as allPrice from orders " +
            " left join order_details on order_details.order_id = orders.id  " +
            " left join products on order_details.product_id = products.id  " +
            " left join invoice on orders.id = invoice.order_id " +
            " group by invoice.id, order_details.order_id, order_date having invoice.id is null", nativeQuery = true)
    Page<OrdersWithoutInvoiceProjection> findAllOrdersWithoutInvoices(Pageable pageable);


    @Query(value = "select p.name as name, d.quantity as quantity,o.id as orderId,o.date as orderdate  from orders o\n" +
            "    join detail d on o.id = d.ord_id\n" +
            " join product p on p.id = d.pr_id where o.id=:orderId", nativeQuery = true)
    DetailsByOrderIdProjection getDetailsByOrderId(@Param("orderId") Long orderId);


}
