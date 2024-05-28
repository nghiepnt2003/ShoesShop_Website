package Entity;

import DBUtil.DBUtil;
import jakarta.persistence.*;

@Entity
@Table(name="CartLine")
public class CartLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.EAGER)
    private Product product;
    private Long quantity;

    public CartLine() {
    }
    public CartLine(Product product, Long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Double getTotal() {
        return quantity * product.getProductCost();
    }



    public CartLine(Product product) {
        this.product = product;
        this.quantity = 1L;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }



}
