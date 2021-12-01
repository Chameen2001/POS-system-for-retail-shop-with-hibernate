package entity;

public class Order {
    private String cusId;
    private String orderId;
    private String orderDate;
    private double cost;
    private String orderTime;

    public Order() {
    }

    public Order(double cost, String orderTime) {
        this.cost = cost;
        this.orderTime = orderTime;
    }

    public Order(String orderDate, double cost) {
        this.orderDate = orderDate;
        this.cost = cost;
    }

    public Order(String cusId, String orderId, String orderDate, double cost, String OrderTime) {
        this.setCusId(cusId);
        this.setOrderId(orderId);
        this.setOrderDate(orderDate);
        this.setCost(cost);
        this.setOrderTime(OrderTime);
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
