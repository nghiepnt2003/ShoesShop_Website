package Entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
@Table(name="Product")
public class Product {
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
    @ManyToOne()
    private Category category;


    public Product() {
    }


    public Product(String productName, String brand, String productImage, String color, int size, Double productCost, String description, Category category) {
        this.productName = productName;
        this.brand = brand;
        this.productImage = productImage;
        this.color = color;
        this.size = size;
        this.productCost = productCost;
        this.description = description;
        this.category = category;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
