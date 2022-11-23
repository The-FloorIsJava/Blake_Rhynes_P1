package Controller;

import EmployeeDao.EmployeeDao;
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

    public LoginController(Javalin app) {
        eLogin = new EmployeeLogin(new EmployeeDao());
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
        List<Model.EmployeeLogin> allEmployees = eLogin.getAllEmployeess();
//        similar as context.result, but the content type is json rather than text.
        context.json(allEmployees);
    }

    private void postAddEmployee(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
         Model.EmployeeLogin newEmployeeLogin = mapper.readValue(context.body(), Model.EmployeeLogin.class);
        Model.EmployeeLogin addedEmployee = eLogin.addEmployee(newEmployeeLogin);
        context.json(addedEmployee);


    }
    private void postAddStandardEmployee(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Model.EmployeeLogin newEmployeeLogin = mapper.readValue(context.body(), Model.EmployeeLogin.class);
        Model.EmployeeLogin addedEmployee = eLogin.addStandardEmployee(newEmployeeLogin);
        context.json(addedEmployee);
    }

    private void getSpecificEmployeeHandler(Context context) {
        String name = context.pathParam("username");
        Model.EmployeeLogin employee = eLogin.getEmployee(name);
        context.json(employee);
    }

    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LoginCreds loginCreds = mapper.readValue(context.body(), LoginCreds.class);
        eLogin.login(loginCreds.getEmployee_role(), loginCreds.getUsername(), loginCreds.getPassword());//loginCreds.getEmployee_role(),
        context.json("Successfully logged in");
    }

    private void logoutHandler(Context context){
        String employeeUsername = eLogin.getEmployeeSession().getUsername();
        eLogin.logout();
        context.json(employeeUsername + " has logged out");
    }



}
