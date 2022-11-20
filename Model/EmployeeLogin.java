package Model;

public class EmployeeLogin {
    private String username;
    private String employee_role;
    private String password;

public EmployeeLogin(){

}
    public EmployeeLogin(String username, String employee_role, String password) {
        this.username = username;
        this.employee_role = employee_role;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmployee_role() {
        return employee_role;
    }

    public void setEmployee_role(String employee_role) {
        this.employee_role = employee_role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
