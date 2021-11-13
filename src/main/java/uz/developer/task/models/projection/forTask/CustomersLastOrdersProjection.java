package uz.developer.task.models.projection.forTask;

import java.util.Date;

public interface CustomersLastOrdersProjection {
    Long getCustomerId();

    String getName();

    Date getLastOrderDate();

}
