package br.com.fiap.Checkpoint2.service;

import br.com.fiap.Checkpoint2.model.OrderModel;
import br.com.fiap.Checkpoint2.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderModel  createOrder(OrderModel order){
        return orderRepository.save(order);
    }

    public List<OrderModel> readAllOrders(){
        return orderRepository.findAll();
    }

    public OrderModel readOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado!"));
    }
    public OrderModel updateOrder(Long id, OrderModel order){
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setClientName(order.getClientName());
                    existingOrder.setOrderDate(order.getOrderDate());
                    return orderRepository.save(existingOrder);
                })
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado!"));
    }
    public void deleteOrderById(Long id){
        try {
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Pedido não encontrado");
        }
    }
}
