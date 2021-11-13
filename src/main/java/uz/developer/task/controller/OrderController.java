package uz.developer.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.developer.task.models.custom.Response;
import uz.developer.task.models.dto.PostOrderDto;
import uz.developer.task.service.in.OrderService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public Response add(@Valid @RequestBody PostOrderDto postOrderDto) {
        return orderService.addOrder(postOrderDto);
    }

    @GetMapping("/details")
    public Response byOrderId(@RequestParam(value = "orderId") Long orderId ){
        return orderService.getOrderById(orderId);
    }
}
