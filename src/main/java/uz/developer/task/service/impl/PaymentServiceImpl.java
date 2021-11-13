package uz.developer.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.developer.task.models.custom.Response;
import uz.developer.task.models.custom.Status;
import uz.developer.task.models.dto.PaymentDto;
import uz.developer.task.models.entity.Invoice;
import uz.developer.task.models.entity.Payment;
import uz.developer.task.models.projection.PaymentDetailsProjection;
import uz.developer.task.repository.InvoiceRepository;
import uz.developer.task.repository.PaymentRepository;
import uz.developer.task.service.in.PaymentService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public Response paymentMake(PaymentDto paymentDto) {
        Response response = new Response();
        Status status;
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(paymentDto.getInvoiceId());
        if (paymentDto.getAmount() <= 0) {
            status = new Status(-2, "to'lov qilmati xato kiritildi");
            response.setData(null);
            response.setStatus(status);
            return response;
        }
        if (!optionalInvoice.isPresent()) {
            status = new Status(-1, "faktura topilmadi");
            response.setData(null);
            response.setStatus(status);
        } else {
            Payment payment = new Payment();
            payment.setAmount(paymentDto.getAmount());
            payment.setInvoice(optionalInvoice.get());
            payment.setTime(new Timestamp(new Date().getTime()));
            Payment savedPayment = paymentRepository.save(payment);

            status = new Status(0, "muvaffaqiyatli to'landi");
            response.setStatus(status);
            response.setData(savedPayment.getId());
        }
        return response;
    }

    @Override
    public Response getDetailsByPaymentId(Long paymentId) {
        Response response = new Response();
        Status status;
        PaymentDetailsProjection paymentDetailsProjection = paymentRepository.getByPaymentId(paymentId);
        if (paymentDetailsProjection != null) {
            status = new Status(0, "natija");
            response.setStatus(status);
            response.setData(paymentDetailsProjection);
        } else {
            status = new Status(-1, "bunday to'lov topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }
}
