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

        return employeeLogin;
    }

    public Model.EmployeeLogin getEmployee(String username){
        return null;
    }

    public void removeEmployee (String username){

    }

    public void login(String username, String password){
        employeeSession = employeeDao.loginCheck(username, password); //need to adjust dao
    }

    public List<Model.EmployeeLogin> getAllEmployees(){
        return null;
    }

    public void logout(){

    }
}
