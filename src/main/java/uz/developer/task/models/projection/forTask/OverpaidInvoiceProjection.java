package uz.developer.task.models.projection.forTask;

public interface OverpaidInvoiceProjection {
    Long getInvoiceId();

    Double getAmount();

    Double getSurplusOfMoney();
}
