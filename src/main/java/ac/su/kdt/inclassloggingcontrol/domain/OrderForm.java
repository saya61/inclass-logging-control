package ac.su.kdt.inclassloggingcontrol.domain;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderForm {
    // id, productId, cartId, userId, quantity
    private Long id;
    private Long productId;
    private Long cartId;
    private Long userId;
    private Integer quantity;

    @Override
    public String toString() {
        return "OrderForm{" +
                "id=" + id +
                ", productId=" + productId +
                ", cartId=" + cartId +
                ", userId=" + userId +
                ", quantity=" + quantity +
                '}';
    }
}
