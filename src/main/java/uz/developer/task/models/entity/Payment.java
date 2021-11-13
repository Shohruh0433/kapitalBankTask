package uz.developer.task.models.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.developer.task.models.custom.BaseEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "payment")
@Data
public class Payment extends BaseEntity {

    private Timestamp time;

    @Column(precision = 8, scale = 2)
    private Double amount;

    @JoinColumn(name = "inv_id")
    @ManyToOne
    private Invoice invoice;
}
