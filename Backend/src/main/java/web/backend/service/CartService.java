    package web.backend.service;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.*;
    import web.backend.DTO.CartItemDTO;
    import web.backend.model.CartItem;
    import web.backend.model.SanPham;
    import web.backend.repository.CartItemRepository;
    import web.backend.repository.SanPhamRepository;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class CartService  implements ICartService{
        @Autowired
        private CartItemRepository cartItemRepository;

        @Autowired
        private SanPhamRepository sanPhamRepository;

        @Override
        public CartItem addToCart(CartItem item) {
            return cartItemRepository.save(item);
        }

        @Override
        public List<CartItemDTO> getCartItems(String userId) {
            List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
            List<CartItemDTO> responseList = new ArrayList<>();

            for (CartItem item : cartItems) {
                CartItemDTO dto = new CartItemDTO();
                dto.setId(item.getId());
                dto.setMaSP(item.getMaSP());
                dto.setKichCo(item.getKichCo());
                dto.setSoLuong(item.getSoLuong());
                dto.setGia(item.getGia());
                dto.setAnh(item.getAnh());
                dto.setUserId(item.getUserId());

                Optional<SanPham> spOpt = sanPhamRepository.findById(item.getMaSP());
                if (spOpt.isPresent()) {
                    dto.setTenSP(spOpt.get().getTenSP());
                } else {
                    dto.setTenSP("Unknown");
                }

                responseList.add(dto);
            }
            return responseList;
        }
        @Override
        public void removeItem(Long id) {
            cartItemRepository.deleteById(id);
        }
        @Override
        public void updateQuantity(Long id, int newQuantity) {
            CartItem item = cartItemRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Cart item not found with ID: " + id));

            // Optional: Bạn có thể kiểm tra số lượng hợp lệ
            if (newQuantity <= 0) {
                // Nếu số lượng <= 0, bạn có thể xóa item hoặc ném lỗi
                cartItemRepository.deleteById(id);
            } else {
                item.setSoLuong(newQuantity);
                cartItemRepository.save(item);
            }
        }
    }
