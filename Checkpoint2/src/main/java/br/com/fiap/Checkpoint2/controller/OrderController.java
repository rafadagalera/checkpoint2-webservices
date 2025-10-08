package br.com.fiap.Checkpoint2.controller;

import br.com.fiap.Checkpoint2.model.OrderModel;
import br.com.fiap.Checkpoint2.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody @Valid OrderModel order) {
        try {
            OrderModel createdOrder = orderService.createOrder(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public List<OrderModel> readOrders() {
        return orderService.readAllOrders();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Object> getOrder(@PathVariable("code") Long id) {
        try {
            OrderModel listedOrder = orderService.readOrderById(id);
            return new ResponseEntity<>(listedOrder, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{code}")
    public ResponseEntity <Object> updateOrder(@PathVariable("code") Long id, @RequestBody @Valid OrderModel order) {
        try {
            OrderModel updatedOrder = orderService.updateOrder(id, order);
            return new ResponseEntity<>(updatedOrder,  HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Object> deleteOrder(@PathVariable("code") Long id) {
        try{
            orderService.deleteOrderById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
