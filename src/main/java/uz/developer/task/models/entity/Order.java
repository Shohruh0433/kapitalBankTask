package uz.developer.task.models.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.developer.task.models.custom.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "orders")
@Data
public class Order extends BaseEntity {

    private Date date;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;


}
