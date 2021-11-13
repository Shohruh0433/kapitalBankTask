package uz.developer.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.developer.task.models.entity.Payment;
import uz.developer.task.models.projection.PaymentDetailsProjection;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query(value = "select d.pr_id as productId, p.amount as amount,p.time as date from payment p\n" +
            "join invoice i on i.id = p.inv_id\n" +
            "join orders o on o.id = i.ord_id\n" +
            "join detail d on o.id = d.ord_id where p.id=:paymentId",nativeQuery = true)
    PaymentDetailsProjection getByPaymentId(@Param(value = "paymentId") Long paymentId);
}
