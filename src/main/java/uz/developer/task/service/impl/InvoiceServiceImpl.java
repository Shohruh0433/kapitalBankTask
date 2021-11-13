package uz.developer.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.task.models.custom.Response;
import uz.developer.task.models.custom.Status;
import uz.developer.task.models.projection.forTask.ExpiredInvoiceProjection;
import uz.developer.task.models.projection.forTask.OverpaidInvoiceProjection;
import uz.developer.task.models.projection.forTask.WrongDateInvoiceProjection;
import uz.developer.task.repository.InvoiceRepository;
import uz.developer.task.service.in.InvoiceService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    @Override
    public Response findAllExpiredInvoices(Date date, Pageable pageable) {
        Response response=new Response();
        Status status;
        Page<ExpiredInvoiceProjection> allExpiredInvoices = invoiceRepository.findAllExpiredInvoices(date,pageable);
        if (!allExpiredInvoices.isEmpty()){
           status=new Status(0,"natija");
           response.setStatus(status);
           response.setData(allExpiredInvoices);
        }else {
            status=new Status(-1,"hech narsa topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response findAllWrongDateInvoices(Pageable pageable) {
        Response response=new Response();
        Status status;
        Page<WrongDateInvoiceProjection> allWrongDateInvoices = invoiceRepository.findAllWrongDateInvoices(pageable);
        if (!allWrongDateInvoices.isEmpty()){
            status=new Status(0,"natija");
            response.setStatus(status);
            response.setData(allWrongDateInvoices);
        }else {
            status=new Status(-1,"hech narsa topilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response findAllOverpaidInvoices(Pageable pageable) {
        Response response=new Response();
        Status status;
        Page<OverpaidInvoiceProjection> allOverpaidInvoices = invoiceRepository.findAllOverpaidInvoices(pageable);
        if (!allOverpaidInvoices.isEmpty()){
            status=new Status(0,"natija");
            response.setStatus(status);
            response.setData(allOverpaidInvoices);
        }else {
            status=new Status(-1,"hech narsa toppilmadi");
            response.setStatus(status);
            response.setData(null);
        }
        return response;
    }
}
