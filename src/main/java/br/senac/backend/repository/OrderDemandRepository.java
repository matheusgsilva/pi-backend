package br.senac.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.senac.backend.model.OrderDemand;

@Repository
public interface OrderDemandRepository extends JpaRepository<OrderDemand, Long> {

    @Query("SELECT o FROM OrderDemand o WHERE o.guid = ?1")
    OrderDemand findByGuid(String guid);
    
    @Query("SELECT o FROM OrderDemand o")
    List<OrderDemand> getAllOrders();
}
