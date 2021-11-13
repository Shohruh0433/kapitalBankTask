package uz.developer.task.models.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.developer.task.models.custom.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "invoice")
@Data
public class Invoice extends BaseEntity {

    @JoinColumn(name = "ord_id")
    @ManyToOne
    private Order order;

    @Column(precision = 8, scale = 2)
    private Double amount;

    private Date issued;

    private Date due;
}
