package Controller;

import Model.Employee;
import Service.EmployeeLogin;
import Util.DTO.LoginCreds;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class LoginController {
    EmployeeLogin eLogin;



    Javalin app;

    public LoginController(Javalin app, EmployeeLogin eLogin) {
        this.eLogin = eLogin;
        this.app = app;
    }

    public void employeeEndpoint(){
        app.post("employee",this::postAddEmployee);
        app.get("employee/{username}",this::getSpecificEmployeeHandler);
        app.get("allEmployees",this::getAllEmployees);
        app.post("login", this::loginHandler);
        app.delete("logout", this::logoutHandler);
        app.post("addStandardEmployee",this::postAddStandardEmployee);
    }
    private void getAllEmployees(Context context) {
        List<Employee> allEmployees = eLogin.getAllEmployeess();
        context.json(allEmployees);
    }

    private void postAddEmployee(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
         Employee newEmployee = mapper.readValue(context.body(), Employee.class);
        Employee addedEmployee = eLogin.addEmployee(newEmployee);
        context.json(addedEmployee);


    }
    private void postAddStandardEmployee(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Employee newEmployee = mapper.readValue(context.body(), Employee.class);
        Employee addedEmployee = eLogin.addStandardEmployee(newEmployee);
        context.json(addedEmployee);
    }

    private void getSpecificEmployeeHandler(Context context) {
        String name = context.pathParam("username");
        Employee employee = eLogin.getEmployee(name);
        context.json(employee);
    }

    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LoginCreds loginCreds = mapper.readValue(context.body(), LoginCreds.class);
        eLogin.login(loginCreds.getEmployee_role(), loginCreds.getUsername(), loginCreds.getPassword());
        context.json("Successfully logged in");
    }

    private void logoutHandler(Context context){
        String employeeUsername = eLogin.getEmployeeSession().getUsername();
        eLogin.logout();
        context.json(employeeUsername + " has logged out");
    }



}
