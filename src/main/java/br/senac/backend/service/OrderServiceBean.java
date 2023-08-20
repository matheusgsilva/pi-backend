package br.senac.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senac.backend.model.OrderDemand;
import br.senac.backend.repository.OrderDemandRepository;

import java.util.List;

@Service
public class OrderServiceBean implements OrderService {

    @Autowired
    private OrderDemandRepository orderRepository;

    @Override
    public List<OrderDemand> listAll() {
        return orderRepository.getAllOrders();
    }

    @Override
    public OrderDemand detail(String guid) {
        return orderRepository.findByGuid(guid);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDemand save(OrderDemand order) {
        return orderRepository.save(order);
    }
}