package uz.developer.task.models.projection.forTask;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface ExpiredInvoiceProjection {
    Date getOrderDate();

    String getCustomerName();

    String getCountry();

    String getAddress();

    String getPhoneNumber();

    String getProductName();

    String getCategory();

    String getDescription();

    @Value("#{target.photo != null ? (@environment.getProperty('base.url')+@environment.getProperty('get.photo.base.url')+ target.photo):null}")
    String getPhoto();

    Short getQuantity();

    Double getInvoiceAmount();
}
