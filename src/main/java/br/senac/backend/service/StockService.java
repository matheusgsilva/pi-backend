package br.senac.backend.service;

import java.util.List;

import br.senac.backend.model.Stock;

public interface StockService {
	
	List<Stock> listAll();

	Stock detailByProductGuid(String productGuid);
	
	Stock findByGuid(String guid);

	boolean isExists(String productGuid);
	
	boolean isExists(String productGuid, String guid);

	void delete(Long id);

	Stock save(Stock stock);
}
