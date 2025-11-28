package Classes;
import Enum.Role;
public class Employee extends Person {
    private Role role;
    private String workEmail;

    public Employee(String name, String id, Role role, String workEmail) {
        super(name, id);
        this.role = role;
        this.workEmail = workEmail;
    }

  

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

}
