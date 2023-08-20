package br.senac.backend.service;

import java.util.List;

import br.senac.backend.model.Stock;

public interface StockService {
	
	List<Stock> listAll();

	Stock detailByProductGuid(String productGuid);
	
	Stock findByGuid(String guid);

	boolean existsByProductGuid(String productGuid);

	void delete(Long id);

	Stock save(Stock stock);
}
