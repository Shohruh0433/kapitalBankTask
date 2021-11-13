package uz.developer.task.models.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.developer.task.models.custom.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "country")
public class Country extends BaseEntity {
    private String  name;
}
