import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        ArrayList<Customer> customers = loadCustomers();
        ArrayList<Employee> employees = loadEmployees();
        ArrayList<Order> orders = loadOrders();

        // Display first registered employee if exists
        if(!employees.isEmpty()) {
            System.out.println("=== First Registered Employee ===");
            employees.get(0).displayEmployee();
            System.out.println("-------------------------------");
        }

        ArrayList<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem(1, "Burger", 5.99));
        menu.add(new MenuItem(2, "Pizza", 8.99));
        menu.add(new MenuItem(3, "Pasta", 7.99));
        menu.add(new MenuItem(4, "Coke", 1.99));

        int option;
        do {
            System.out.println("\n=== Restaurant Management System ===");
            System.out.println("| ========================|");
            System.out.println("| 1. Register Customer    |");
            System.out.println("| ------------------------|");
            System.out.println("| 2. Place Order          |");
            System.out.println("| ========================|");
            System.out.println("| 3. View All Customers   |");
            System.out.println("| ------------------------|");
            System.out.println("| 4. Update Customer      |");
            System.out.println("| ------------------------|");
            System.out.println("| 5. Delete Customer      |");
            System.out.println("| ------------------------|");
            System.out.println("| 6. Search Customer      |");
            System.out.println("| ========================|");
            System.out.println("| 7. Update Order         |");
            System.out.println("| ------------------------|");
            System.out.println("| 8. Delete Order         |");
            System.out.println("| ------------------------|");
            System.out.println("| 9. Search Order         |");
            System.out.println("| ========================|");
            System.out.println("| 10. Update Employee     |");
            System.out.println("| ------------------------|");
            System.out.println("| 11. Delete Employee     |");
            System.out.println("| ------------------------|");
            System.out.println("| 12. Search Employee     |");
            System.out.println("| ------------------------|");
            System.out.println("| 13. Register Employee   |");
            System.out.println("| ------------------------|");
            System.out.println("| 14. View All Employees  |");
            System.out.println("| ========================|");
            System.out.println("| 0. Exit                 |");
            System.out.println("| ========================|");
            
            System.out.println("| ========================|");
            System.out.println("| Select an option:       |");
            System.out.println("| ========================|");

            option = sc.nextInt();
            sc.nextLine();

            switch(option) {
                case 1:
                    registerCustomer(sc, customers);
                    saveCustomers(customers);
                    break;
                case 2:
                    placeOrder(sc, customers, orders, menu);
                    saveOrders(orders);
                    break;
                case 3:
                    if(customers.isEmpty()) System.out.println("No registered customers yet.");
                    else for(Customer c : customers) {
                        c.displayCustomer();
                        System.out.println("---------------------");
                    }
                    break;
                case 4:
                    updateCustomer(sc, customers);
                    saveCustomers(customers);
                    break;
                case 5:
                    deleteCustomer(sc, customers);
                    saveCustomers(customers);
                    break;
                case 6:
                    searchCustomer(sc, customers);
                    break;
                case 7:
                    updateOrder(sc, orders, customers, menu);
                    saveOrders(orders);
                    break;
                case 8:
                    deleteOrder(sc, orders);
                    saveOrders(orders);
                    break;
                case 9:
                    searchOrder(sc, orders, customers);
                    break;
                case 10:
                    updateEmployee(sc, employees);
                    saveEmployees(employees);
                    break;
                case 11:
                    deleteEmployee(sc, employees);
                    saveEmployees(employees);
                    break;
                case 12:
                    searchEmployee(sc, employees);
                    break;
                case 13:
                    registerEmployee(sc, employees);
                    saveEmployees(employees);
                    break;
                case 14:
                    if(employees.isEmpty()) System.out.println("No registered employees yet.");
                    else for(Employee e : employees) {
                        e.displayEmployee();
                        System.out.println("---------------------");
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        } while(option != 0);
    }

    // ====== Customer Methods ======
    private static void registerCustomer(Scanner sc, ArrayList<Customer> customers) {
        System.out.println("=== Register Customer ===");
        int custId;
        while(true) {
            System.out.print("Enter unique Customer ID: ");
            custId = sc.nextInt(); sc.nextLine();
            boolean exists = false;
            for(Customer c : customers) if(c.getId() == custId) exists = true;
            if(exists) System.out.println("ID already exists! Try again.");
            else break;
        }
        System.out.print("Name: "); String custName = sc.nextLine();
        System.out.print("Phone: "); String custPhone = sc.nextLine();
        System.out.print("Email: "); String custEmail = sc.nextLine();
        System.out.print("Address: "); String custAddress = sc.nextLine();

        Customer customer = new Customer(custId, custName, custPhone, custEmail, custAddress);
        customers.add(customer);
        System.out.println("Customer registered successfully!");
        customer.displayCustomer();
    }

    private static void updateCustomer(Scanner sc, ArrayList<Customer> customers) {
        System.out.print("Enter Customer ID to update: ");
        int id = sc.nextInt(); sc.nextLine();
        Customer cust = null;
        for(Customer c : customers) if(c.getId() == id) cust = c;
        if(cust == null) {
            System.out.println("Customer not found!");
            return;
        }
        System.out.print("New Name (" + cust.getName() + "): "); String name = sc.nextLine();
        System.out.print("New Phone (" + cust.getPhone() + "): "); String phone = sc.nextLine();
        System.out.print("New Email (" + cust.getEmail() + "): "); String email = sc.nextLine();
        System.out.print("New Address (" + cust.getAddress() + "): "); String address = sc.nextLine();

        if(!name.isEmpty()) cust.setName(name);
        if(!phone.isEmpty()) cust.setPhone(phone);
        if(!email.isEmpty()) cust.setEmail(email);
        if(!address.isEmpty()) cust.setAddress(address);

        System.out.println("Customer updated successfully!");
    }

    private static void deleteCustomer(Scanner sc, ArrayList<Customer> customers) {
        System.out.print("Enter Customer ID to delete: ");
        int id = sc.nextInt(); sc.nextLine();
        Customer cust = null;
        for(Customer c : customers) if(c.getId() == id) cust = c;
        if(cust == null) {
            System.out.println("Customer not found!");
            return;
        }
        customers.remove(cust);
        System.out.println("Customer deleted successfully!");
    }

    private static void searchCustomer(Scanner sc, ArrayList<Customer> customers) {
        System.out.print("Enter Customer ID to search: ");
        int id = sc.nextInt(); sc.nextLine();
        Customer cust = null;
        for(Customer c : customers) if(c.getId() == id) cust = c;
        if(cust == null) System.out.println("Customer not found!");
        else cust.displayCustomer();
    }

    private static Customer findCustomerById(ArrayList<Customer> customers, int id) {
        for(Customer c : customers) if(c.getId() == id) return c;
        return null;
    }

    // ====== Employee Methods ======
    private static void registerEmployee(Scanner sc, ArrayList<Employee> employees) {
        System.out.println("=== Register Employee ===");
        int empId;
        while(true) {
            System.out.print("Enter unique Employee ID: ");
            empId = sc.nextInt(); sc.nextLine();
            boolean exists = false;
            for(Employee e : employees) if(e.getId() == empId) exists = true;
            if(exists) System.out.println("ID already exists! Try again.");
            else break;
        }
        System.out.print("Name: "); String empName = sc.nextLine();
        System.out.print("Phone: "); String empPhone = sc.nextLine();
        System.out.print("Email: "); String empEmail = sc.nextLine();
        System.out.print("Position/Job: "); String empPosition = sc.nextLine();

        Employee employee = new Employee(empId, empName, empPhone, empEmail, empPosition);
        employees.add(employee);
        System.out.println("Employee registered successfully!");
        employee.displayEmployee();
    }

    private static void updateEmployee(Scanner sc, ArrayList<Employee> employees) {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt(); sc.nextLine();
        Employee emp = null;
        for(Employee e : employees) if(e.getId() == id) emp = e;
        if(emp == null) {
            System.out.println("Employee not found!");
            return;
        }
        System.out.print("New Name (" + emp.getName() + "): "); String name = sc.nextLine();
        System.out.print("New Phone (" + emp.getPhone() + "): "); String phone = sc.nextLine();
        System.out.print("New Email (" + emp.getEmail() + "): "); String email = sc.nextLine();
        System.out.print("New Position (" + emp.getPosition() + "): "); String position = sc.nextLine();

        if(!name.isEmpty()) emp.setName(name);
        if(!phone.isEmpty()) emp.setPhone(phone);
        if(!email.isEmpty()) emp.setEmail(email);
        if(!position.isEmpty()) emp.setPosition(position);

        System.out.println("Employee updated successfully!");
    }

    private static void deleteEmployee(Scanner sc, ArrayList<Employee> employees) {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt(); sc.nextLine();
        Employee emp = null;
        for(Employee e : employees) if(e.getId() == id) emp = e;
        if(emp == null) {
            System.out.println("Employee not found!");
            return;
        }
        employees.remove(emp);
        System.out.println("Employee deleted successfully!");
    }

    private static void searchEmployee(Scanner sc, ArrayList<Employee> employees) {
        System.out.print("Enter Employee ID to search: ");
        int id = sc.nextInt(); sc.nextLine();
        Employee emp = null;
        for(Employee e : employees) if(e.getId() == id) emp = e;
        if(emp == null) System.out.println("Employee not found!");
        else emp.displayEmployee();
    }

    // ====== Order Methods ======
    private static void placeOrder(Scanner sc, ArrayList<Customer> customers, ArrayList<Order> orders, ArrayList<MenuItem> menu) {
        System.out.print("Enter Customer ID for order: ");
        int custId = sc.nextInt(); sc.nextLine();
        Customer cust = findCustomerById(customers, custId);
        if(cust == null) {
            System.out.println("Customer not found! Register first.");
            return;
        }

        int orderId;
        while(true) {
            System.out.print("Enter unique Order ID: ");
            orderId = sc.nextInt(); sc.nextLine();
            boolean exists = false;
            for(Order o : orders) if(o.getId() == orderId) exists = true;
            if(exists) System.out.println("Order ID already exists! Try again.");
            else break;
        }

        Order order = new Order(orderId, custId);
        int choice;
        do {
            System.out.println("\nMenu:");
            for(MenuItem item : menu) item.display();
            System.out.print("Enter item number to order (0 to finish): ");
            choice = sc.nextInt();
            if(choice > 0 && choice <= menu.size()) {
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                order.addItem(menu.get(choice - 1), qty);
                System.out.println("Added " + menu.get(choice - 1).getName() + " x" + qty);
            } else if(choice != 0) {
                System.out.println("Invalid choice!");
            }
        } while(choice != 0);

        orders.add(order);
        System.out.println("\nOrder Summary:");
        cust.displayCustomer();
        order.displayOrder();
    }

    private static void updateOrder(Scanner sc, ArrayList<Order> orders, ArrayList<Customer> customers, ArrayList<MenuItem> menu) {
        System.out.print("Enter Order ID to update: ");
        int orderId = sc.nextInt(); sc.nextLine();
        Order order = null;
        for(Order o : orders) if(o.getId() == orderId) order = o;
        if(order == null) {
            System.out.println("Order not found!");
            return;
        }

        Customer cust = findCustomerById(customers, order.getCustomerId());
        System.out.println("Updating order for Customer: " + (cust != null ? cust.getName() : "Unknown"));

        order.getItems().clear();

        int choice;
        do {
            System.out.println("\nMenu:");
            for(MenuItem item : menu) item.display();
            System.out.print("Enter item number to add (0 to finish): ");
            choice = sc.nextInt();
            if(choice > 0 && choice <= menu.size()) {
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                order.addItem(menu.get(choice - 1), qty);
            } else if(choice != 0) {
                System.out.println("Invalid choice!");
            }
        } while(choice != 0);

        System.out.println("Order updated successfully!");
    }

    private static void deleteOrder(Scanner sc, ArrayList<Order> orders) {
        System.out.print("Enter Order ID to delete: ");
        int orderId = sc.nextInt(); sc.nextLine();
        Order order = null;
        for(Order o : orders) if(o.getId() == orderId) order = o;
        if(order == null) {
            System.out.println("Order not found!");
            return;
        }
        orders.remove(order);
        System.out.println("Order deleted successfully!");
    }

    private static void searchOrder(Scanner sc, ArrayList<Order> orders, ArrayList<Customer> customers) {
        System.out.print("Enter Order ID to search: ");
        int orderId = sc.nextInt(); sc.nextLine();
        Order order = null;
        for(Order o : orders) if(o.getId() == orderId) order = o;
        if(order == null) {
            System.out.println("Order not found!");
            return;
        }
        Customer cust = findCustomerById(customers, order.getCustomerId());
        if(cust != null) cust.displayCustomer();
        order.displayOrder();
    }

    // ====== File Handling ======
    private static void saveCustomers(ArrayList<Customer> customers) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("customers.dat"))) {
            out.writeObject(customers);
        } catch(IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    private static ArrayList<Customer> loadCustomers() {
        File f = new File("customers.dat");
        if(!f.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            return (ArrayList<Customer>) in.readObject();
        } catch(Exception e) {
            System.out.println("Error loading customers: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void saveEmployees(ArrayList<Employee> employees) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employees.dat"))) {
            out.writeObject(employees);
        } catch(IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }

    private static ArrayList<Employee> loadEmployees() {
        File f = new File("employees.dat");
        if(!f.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            return (ArrayList<Employee>) in.readObject();
        } catch(Exception e) {
            System.out.println("Error loading employees: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void saveOrders(ArrayList<Order> orders) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("orders.dat"))) {
            out.writeObject(orders);
        } catch(IOException e) {
            System.out.println("Error saving orders: " + e.getMessage());
        }
    }

    private static ArrayList<Order> loadOrders() {
        File f = new File("orders.dat");
        if(!f.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            return (ArrayList<Order>) in.readObject();
        } catch(Exception e) {
            System.out.println("Error loading orders: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}