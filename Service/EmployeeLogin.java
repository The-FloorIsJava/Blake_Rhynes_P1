package Service;

import EmployeeDao.EmployeeDao;

import java.util.List;

public class EmployeeLogin {

    private Model.EmployeeLogin employeeSession = null;

    private final EmployeeDao employeeDao;

    public EmployeeLogin(EmployeeDao employeeDao){
    this.employeeDao = employeeDao;
    }

    public Model.EmployeeLogin addEmployee(Model.EmployeeLogin employeeLogin){
        return employeeDao.create(employeeLogin);
    }

    public Model.EmployeeLogin getEmployee(String username){
        return null;
    }

    public void removeEmployee (String username){

    }

    public void login(String employee_role,String username, String password){
        employeeSession = employeeDao.loginCheck(employee_role,username, password);
    }

    public List<Model.EmployeeLogin> getAllEmployees(){
        return null;
    }

    public void logout(){
employeeSession = null;
    }

    public Model.EmployeeLogin getEmployeeSession(){
        return employeeSession;
    }
}
