package br.senac.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.senac.backend.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s WHERE s.guid = ?1")
    Stock findByGuid(String guid);
    
    @Query("SELECT s FROM Stock s WHERE s.product.guid = ?1")
    Stock findByProductGuid(String guid);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Stock s WHERE s.product.guid = ?1")
    boolean existsByStockForProductGuid(String productGuid);
    
    @Query("SELECT s FROM Stock s")
    List<Stock> getAllStocks();
}