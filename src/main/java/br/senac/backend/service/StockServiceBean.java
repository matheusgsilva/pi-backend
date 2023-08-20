package br.senac.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.senac.backend.model.Stock;
import br.senac.backend.repository.StockRepository;
import java.util.List;

@Service
public class StockServiceBean implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> listAll() {
        return stockRepository.getAllStocks();
    }

    @Override
    public Stock findByGuid(String guid) {
        return stockRepository.findByGuid(guid);
    }
    
    @Override
    public Stock detailByProductGuid(String productGuid) {
        return stockRepository.findByProductGuid(productGuid);
    }

    @Override
    public boolean existsByProductGuid(String productGuid) {
        return stockRepository.existsByStockForProductGuid(productGuid);
    }

    @Override
    public void delete(Long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }
}