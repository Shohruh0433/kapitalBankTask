package uz.developer.task.models.projection;

import javax.xml.crypto.Data;

public interface DetailsByOrderIdProjection {
    String getName();

    Integer getQuantity();

    Long getOrderId();

    Data getOrderDate();

}
