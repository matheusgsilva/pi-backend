package br.senac.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.senac.backend.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT oi FROM OrderItem oi WHERE oi.guid = ?1")
    OrderItem findByGuid(String guid);
    
    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.guid = ?1")
    List<OrderItem> findByOrderGuid(String guid);
}
