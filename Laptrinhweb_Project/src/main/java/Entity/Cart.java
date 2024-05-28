package Entity;

import DBUtil.DBUtil;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Siêng năng : Truy vấn 1  đối tượng là truy vấn quan hệ ví dụ như trên sẽ truy vấn cartline
    // Lan truyền
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    orphanRemoval = true: Nếu một "CartLine" không còn được tham chiếu từ "Cart", nó sẽ tự động bị xóa.
    private List<CartLine> cartLines;
    @OneToOne
    private Customer customer;

    public Cart() {
    }

    public Cart(Customer customer) {
        this.customer = customer;
        this.cartLines = new ArrayList<CartLine>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "accountID=" + id +
                ", CartLines=" + cartLines +
                '}';
    }


    public void removeCartLine(CartLine cartLine) {
        cartLines.removeIf(item -> item.getId().equals(cartLine.getId()));
    }

    public void removeCartLine(Long productID) {
        cartLines.removeIf(item -> item.getProduct().getId().equals(productID));
    }

    public int countCartLine() {
        return cartLines.size();
    }

    public void addCartLine(CartLine cartLine) {
        for (var item : cartLines) {
            if (item.getProduct().getId().equals(cartLine.getProduct().getId())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        cartLines.add(cartLine);
    }

    public void addQuantityProduct(Long productID) {
        for (var item : cartLines) {
            if (item.getProduct().getId().equals(productID)) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
    }

    public void minusQuantityProduct(Long productID) {
        for (var item : cartLines) {
            if (item.getProduct().getId().equals(productID)) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                }
                return;
            }
        }
    }
}
