package uz.developer.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.developer.task.models.custom.Response;
import uz.developer.task.models.dto.PaymentDto;
import uz.developer.task.service.in.PaymentService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping()
    public Response makePayment(@Valid @RequestBody PaymentDto paymentDto) {
        return paymentService.paymentMake(paymentDto);
    }

    @GetMapping("/details")
    public Response getDetailsById(@RequestParam("paymentId") Long paymentId) {
        return paymentService.getDetailsByPaymentId(paymentId);
    }

}
