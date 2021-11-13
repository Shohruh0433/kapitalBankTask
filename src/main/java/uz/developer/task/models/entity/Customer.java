package uz.developer.task.models.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import uz.developer.task.models.custom.BaseEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer")
@Data
public class Customer extends BaseEntity {

    @Length(max = 14)
    private String name;

    @ManyToOne
    private Country country;

    private String address;

    @Length(max = 50)
    private String phone;

}
