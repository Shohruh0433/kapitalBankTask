package uz.developer.task.models.projection.forTask;

import javax.xml.crypto.Data;
import java.util.Date;

public interface OrdersWithoutInvoiceProjection {
    Long getOrderId();
    Date getOrderDate();
    Double getAllPrice();

}
