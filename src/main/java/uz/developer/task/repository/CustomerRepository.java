package uz.developer.task.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.developer.task.models.entity.Customer;
import uz.developer.task.models.projection.forTask.CustomersLastOrdersProjection;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /*
    4.Customers who have not placed any orders in 2016. Return all attributes.
    * */
    @Query(value = "select * from customer cus where cus.id not in (select c.id " +
            "    from orders o join customer c on c.id = o.cust_id where extract(year from o.date)=:year)", nativeQuery = true)
    Page<Customer> findAllCustomersWithoutOrders(@Param("year") Integer year, Pageable pageable);

    /*
 5.	ID and name of customers and the date of their last order. For customers who did not
place any orders, no rows must be returned. For each customer who placed more than
one order on the date of their most recent order, only one row must be returned.
 * */
    @Query(value = "SELECT distinct c.id as customerId,c.name as name, " +
            "       (SELECT o.date FROM orders o WHERE " +
            "               o.cust_id=c.id " +
            "        ORDER BY o.date DESC limit 1) as lastOrderDate " +
            "FROM customer c join orders o2 on c.id = o2.cust_id", nativeQuery = true)
    Page<CustomersLastOrdersProjection> findCustomersLastOrders(Pageable pageable);




}
