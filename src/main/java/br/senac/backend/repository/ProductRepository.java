package br.senac.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.senac.backend.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.guid = ?1")
    Product findByGuid(String guid);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Product p WHERE p.code = ?1")
    boolean isExists(String code);
    
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Product p WHERE p.code = ?1 AND  p.guid <> ?2")
    boolean isExists(String code, String guid);
    
    @Query("SELECT p FROM Product p")
    List<Product> getAllProducts();
}
