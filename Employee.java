import java.io.Serializable;

public class Employee implements Serializable {
    private int id;           // Unique employee ID
    private String name;
    private String phone;
    private String email;
    private String position;  // Job/role

    public Employee(int id, String name, String phone, String email, String position) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getPosition() { return position; }
    
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setPosition(String position) { this.position = position; }

    public void displayEmployee() {
           //if(employees.sEmpty){
           // System.out.println("There no registered employee"); 
        //}
        System.out.println("Employee ID : " + id);
        System.out.println("Name        : " + name);
        System.out.println("Phone       : " + phone);
        System.out.println("Email       : " + email);
        System.out.println("Position    : " + position);
    }
}
