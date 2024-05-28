package Entity;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "\"Order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    private Customer customer;

    private Date dateOrder;
    private Double totalPrice;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;


    public Order() {
    }

    public String getDateOrder() {

        SimpleDateFormat desiredFormat = new SimpleDateFormat("EEEE, dd'/'MM'/'yyyy 'lúc' HH:mm:ss", new Locale("vi", "VN"));
        desiredFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        return desiredFormat.format(dateOrder);
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order(Customer customer, Double totalPrice, List<OrderDetail> orderDetails) {
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.orderDetails = new ArrayList<OrderDetail>();
        this.dateOrder = new Date();
        this.status = "Processing";
        setDetails(orderDetails);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private void setDetails(List<OrderDetail> orderDetails) {
        for (var item :orderDetails) {
            String productName = item.getProductName();
            String brand = item.getBrand();
            String productImage = item.getProductImage();
            String color = item.getColor();
            int size = item.getSize();
            Double productCost = item.getProductCost();
            String description = item.getDescription();
            Long quantity = item.getQuantity();
            this.orderDetails.add(new OrderDetail(productName, brand,productImage,color,size,productCost,description,quantity));
        }
    }

}
