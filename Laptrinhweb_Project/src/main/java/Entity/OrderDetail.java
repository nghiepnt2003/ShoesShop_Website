package Entity;

import jakarta.persistence.*;

@Entity
@Table(name="OrderDetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String brand;
    private String productImage;    // tạo data kiếm mấy url có đường dẫn ngắn ngắn, dài quá 255 ký tự là bỏ qua kia lỗi
    private String color;
    private int size;
    private Double productCost;
    private String description; // cái này cũng ngắn ngắn hoi, khỏi tiếng việt càng tốt
    private Long quantity;


    public OrderDetail() {
    }

    public OrderDetail(String productName, String brand, String productImage, String color, int size, Double productCost, String description, Long quantity) {
        this.productName = productName;
        this.brand = brand;
        this.productImage = productImage;
        this.color = color;
        this.size = size;
        this.productCost = productCost;
        this.description = description;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Double getProductCost() {
        return productCost;
    }

    public void setProductCost(Double productCost) {
        this.productCost = productCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
