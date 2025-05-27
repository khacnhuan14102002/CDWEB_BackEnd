package web.backend.service;

import web.backend.DTO.CartItemDTO;
import web.backend.model.CartItem;

import java.util.List;

public interface  ICartService {
    CartItem addToCart(CartItem item);
    List<CartItemDTO> getCartItems(String userId);
    void removeItem(Long id);
    void updateQuantity(Long id, int newQuantity);
}
