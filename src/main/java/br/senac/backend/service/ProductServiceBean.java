package br.senac.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.senac.backend.model.Product;
import br.senac.backend.repository.ProductRepository;
import java.util.List;

@Service
public class ProductServiceBean implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listAll() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product detail(String guid) {
        return productRepository.findByGuid(guid);
    }

    @Override
    public boolean isExists(String code) {
        return productRepository.isExists(code);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}