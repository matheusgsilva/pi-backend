package br.senac.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.senac.backend.model.OrderItem;
import br.senac.backend.repository.OrderItemRepository;

@Service
public class OrderItemServiceBean implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem detail(String guid) {
        return orderItemRepository.findByGuid(guid);
    }

    @Override
    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
    
    @Override
    public List<OrderItem> findByOrderGuid(String number) {
        return orderItemRepository.findByOrderGuid(number);
    }
}
