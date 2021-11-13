package uz.developer.task.models.projection;

import org.springframework.beans.factory.annotation.Value;

public interface ProductProjection {
    Long getId();

    String getCountry();

    String getProductName();

    String getCategory();

    String getDescription();

    Double getPrice();

    @Value("#{target.photo != null ? (@environment.getProperty('base.url')+@environment.getProperty('get.photo.base.url')+ target.photo):null}")
    String getPhoto();

}
