package uz.developer.task.service.in;

import org.springframework.data.domain.Pageable;
import uz.developer.task.models.custom.Response;

import java.util.Date;

public interface InvoiceService {

    Response findAllExpiredInvoices(Date nowDate, Pageable pageable);

    Response findAllWrongDateInvoices(Pageable pageable);

    Response findAllOverpaidInvoices(Pageable pageable);
}
