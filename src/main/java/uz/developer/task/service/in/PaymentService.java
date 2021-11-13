package uz.developer.task.service.in;

import uz.developer.task.models.custom.Response;
import uz.developer.task.models.dto.PaymentDto;

public interface PaymentService {
    Response paymentMake(PaymentDto paymentDto);

    Response getDetailsByPaymentId(Long paymentId);
}
