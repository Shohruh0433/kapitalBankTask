package uz.developer.task.models.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import uz.developer.task.models.custom.BaseEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product")
@Data
public class Product extends BaseEntity {

    @Length(max = 10)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Length(max = 20)
    private String description;

    @Column(precision = 6, scale = 2)
    private Double price;

    @Length(max = 1024)
    private String photo;
}
