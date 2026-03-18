import java.io.Serializable;
import java.util.*;

public class Order implements Serializable {
    private int id;
    private int customerId;
    private ArrayList<OrderItem> items;

    public Order(int id, int customerId) {
        this.id = id;
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    // --- Getters ---
    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public ArrayList<OrderItem> getItems() { return items; }

    // --- Setters ---
    public void setId(int id) { this.id = id; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setItems(ArrayList<OrderItem> items) { this.items = items; }

    // Add item
    public void addItem(MenuItem menuItem, int quantity) {
        items.add(new OrderItem(menuItem, quantity));
    }

    // Display order
    public void displayOrder() {
        System.out.println("Order ID: " + id);
        for(OrderItem oi : items) {
            System.out.println("- " + oi.getMenuItem().getName() + " x" + oi.getQuantity() + " = $" + (oi.getQuantity() * oi.getMenuItem().getPrice()));
        }
    }
}

// OrderItem class
class OrderItem implements Serializable {
    private MenuItem menuItem;
    private int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    // Getters and Setters
    public MenuItem getMenuItem() { return menuItem; }
    public int getQuantity() { return quantity; }
    public void setMenuItem(MenuItem menuItem) { this.menuItem = menuItem; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}