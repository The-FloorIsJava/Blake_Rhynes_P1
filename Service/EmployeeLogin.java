package Service;

import EmployeeDao.EmployeeDao;

import java.util.List;

public class EmployeeLogin {

    public Model.EmployeeLogin employeeSession = null; //changed to public to access in ReimbursementRequest Service

    static String employee_role = null;

    private final EmployeeDao employeeDao;


    public EmployeeLogin(EmployeeDao employeeDao){
    this.employeeDao = employeeDao;
    }

    public Model.EmployeeLogin addEmployee(Model.EmployeeLogin employeeLogin){
        return employeeDao.create(employeeLogin);
    }
    public Model.EmployeeLogin addStandardEmployee(Model.EmployeeLogin employeeLogin){
        return employeeDao.createEmployee(employeeLogin);
    }


    public List<Model.EmployeeLogin> getAllEmployeess(){
        return employeeDao.findAll();
    }
    public Model.EmployeeLogin getEmployee(String username){
        return null;
    }

    public void removeEmployee (String username){

    }

    public void login(String employee_role, String username, String password){
        employeeSession = employeeDao.loginCheck(employee_role, username, password);//employee_role,
        EmployeeLogin.employee_role = employee_role;
    }

    public void logout(){
        employeeSession = null;
    }

    public Model.EmployeeLogin getEmployeeSession(){
        return employeeSession;
    }


}
