package uz.developer.task.models.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PaymentDto {
    @NotNull
    private Long invoiceId;
    @NotNull
    private Double amount;
}
