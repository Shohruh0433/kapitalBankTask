package uz.developer.task.models.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostOrderDto {
    @NotNull
    private Long customerId;
    @NotNull
    private Long productId;
    @NotNull
    private Short quantity;
}
