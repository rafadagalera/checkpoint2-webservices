package br.com.fiap.Checkpoint2.repository;

import br.com.fiap.Checkpoint2.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    Long id(Long id);
    // OrderModel FindById(Long id);
}
