package uz.developer.task.models.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import uz.developer.task.models.custom.BaseEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    @Length(max = 250)
    private String name;
}
