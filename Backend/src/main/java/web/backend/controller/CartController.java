package web.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.backend.DTO.CartItemDTO;
import web.backend.model.CartItem;
import web.backend.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody CartItem item) {
        return cartService.addToCart(item);
    }

    @GetMapping("/{userId}")
    public List<CartItemDTO> getCart(@PathVariable String userId) {
        return cartService.getCartItems(userId);
    }

    @DeleteMapping("/remove/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.removeItem(id);
    }
    @PutMapping("/update/{id}")
    public void updateCartItemQuantity(@PathVariable Long id, @RequestBody CartItem updatedItem) {
        cartService.updateQuantity(id, updatedItem.getSoLuong());
    }
}