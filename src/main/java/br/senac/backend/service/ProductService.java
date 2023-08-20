package br.senac.backend.service;

import java.util.List;

import br.senac.backend.model.Product;

public interface ProductService {
	
	List<Product> listAll();

	Product detail(String guid);

	boolean isExists(String code);

	void delete(Long id);

	Product save(Product product);
}
