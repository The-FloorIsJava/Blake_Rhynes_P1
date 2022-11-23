package Service;

import DAO.EmployeeDao;
import Model.Employee;

import java.util.List;

public class EmployeeLogin {

    public Employee employeeSession = null;

    static String employee_role = null;

    private final EmployeeDao employeeDao;


    public EmployeeLogin(EmployeeDao employeeDao){
    this.employeeDao = employeeDao;
    }

    public Employee addEmployee(Employee employee){
        return employeeDao.create(employee);
    }
    public Employee addStandardEmployee(Employee employee){
        return employeeDao.createEmployee(employee);
    }


    public List<Employee> getAllEmployeess(){
        return employeeDao.findAll();
    }
    public Employee getEmployee(String username){
        return null;
    }

    public void removeEmployee (String username){

    }

    public void login(String employee_role, String username, String password){
        employeeSession = employeeDao.loginCheck(employee_role, username, password);
        EmployeeLogin.employee_role = employee_role;
    }

    public void logout(){
        employeeSession = null;
    }

    public Employee getEmployeeSession(){
        return employeeSession;
    }


}
