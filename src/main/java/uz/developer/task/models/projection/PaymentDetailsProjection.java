package uz.developer.task.models.projection;

import java.sql.Timestamp;

public interface PaymentDetailsProjection {
    Timestamp getDate();
    Double getAmount();
    String getProductId();

}
