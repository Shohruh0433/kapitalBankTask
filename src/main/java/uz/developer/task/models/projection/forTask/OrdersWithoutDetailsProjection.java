package uz.developer.task.models.projection.forTask;

import java.util.Date;

public interface OrdersWithoutDetailsProjection {

    Long getId();

    Date getOrderDate();

    String getCustomerName();

    String getAddress();

    String getPhone();

    String getCountry();

}
