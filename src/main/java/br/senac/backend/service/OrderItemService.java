package br.senac.backend.service;

import java.util.List;

import br.senac.backend.model.OrderItem;

public interface OrderItemService {

	OrderItem detail(String guid);

	void delete(Long id);

	OrderItem save(OrderItem orderItem);

	List<OrderItem> findByOrderGuid(String number);
}
