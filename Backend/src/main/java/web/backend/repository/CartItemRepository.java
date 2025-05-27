package web.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import web.backend.model.CartItem;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(String userId);
}