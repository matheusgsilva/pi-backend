package br.senac.backend.service;

import br.senac.backend.model.OrderItem;

public interface OrderItemService {
	
	OrderItem detail(String guid);

	void delete(Long id);

	OrderItem save(OrderItem orderItem);
}
