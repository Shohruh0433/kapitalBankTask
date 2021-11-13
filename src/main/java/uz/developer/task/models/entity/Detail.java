package uz.developer.task.models.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.developer.task.models.custom.BaseEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "detail")
@Data
public class Detail extends BaseEntity {

    @JoinColumn(name = "ord_id")
    @ManyToOne
    private Order order;

    @JoinColumn(name = "pr_id")
    @ManyToOne
    private Product product;

    private Short quantity;

}
