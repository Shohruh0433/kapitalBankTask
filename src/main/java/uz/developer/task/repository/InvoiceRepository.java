package uz.developer.task.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.developer.task.models.entity.Invoice;
import uz.developer.task.models.projection.forTask.ExpiredInvoiceProjection;
import uz.developer.task.models.projection.forTask.OverpaidInvoiceProjection;
import uz.developer.task.models.projection.forTask.WrongDateInvoiceProjection;

import java.util.Date;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    /*
    1.	IInvoices issued after their due date. Return all attributes.*/
    @Query(value = "select o.date as orderDate, c.name as customerName, coun.name as country,c.address as address,c.phone as phoneNumber,\n" +
            "       p.name as productName, cat.name as category, p.description as description, p.photo as photo, d.quantity as quantity, i.amount as invoiceAmount\n" +
            "from invoice i " +
            "join orders o on o.id = i.ord_id\n" +
            "join customer c on c.id = o.cust_id\n" +
            "join category cat on cat.id = c.country_id\n" +
            "join detail d on o.id = d.ord_id\n" +
            "join country coun on coun.id = c.country_id\n" +
            "join product p on p.id = d.pr_id\n" +
            "where i.due<:nowDate", nativeQuery = true)
    Page<ExpiredInvoiceProjection> findAllExpiredInvoices(@Param("nowDate") Date nowDate, Pageable pageable);

    /*
    2.	Invoices that were issued before the date in which the order they refer to was placed.
    Return the ID of the invoice, the date it was issued, the ID of the order associated with it
    and the date the order was placed.
     */
    @Query(value = "select i.id as invoiceId,i.issued as issuedDate,o.id as orderId  from  invoice i " +
            "        join orders o on o.id = i.ord_id " +
            "            where o.date>i.issued", nativeQuery = true)
    Page<WrongDateInvoiceProjection> findAllWrongDateInvoices(Pageable pageable);

    /*6.	Invoices that have been overpaid. Observe that there may be more than one payment
    referring to the same invoice. Return the invoice number and the amount that should be
    reimbursed.
    */
    @Query(value = "select ii.id as invoiceId,ii.amount as amount, nwtb.allAmount-ii.amount as surplusOfMoney from invoice ii  " +
            "join (select inv_id, sum(p.amount) as allAmount from invoice i " +
            "        join payment p on i.id = p.inv_id    group by inv_id) nwtb on nwtb.inv_id=ii.id " +
            "where ii.amount<nwtb.allAmount ", nativeQuery = true)
    Page<OverpaidInvoiceProjection> findAllOverpaidInvoices(Pageable pageable);


}
