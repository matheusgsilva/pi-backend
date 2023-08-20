package br.senac.backend.service;

import java.util.List;

import br.senac.backend.model.OrderDemand;

public interface OrderService {

	List<OrderDemand> listAll();

	OrderDemand detail(String guid);

	void delete(Long id);

	OrderDemand save(OrderDemand order);
}
