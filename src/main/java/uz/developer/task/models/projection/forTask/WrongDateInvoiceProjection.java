package uz.developer.task.models.projection.forTask;

import javax.xml.crypto.Data;
import java.util.Date;

public interface WrongDateInvoiceProjection {

    Long getInvoiceId();

    Date getIssuedDate();

    Long getOrderId();

}
