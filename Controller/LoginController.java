package Controller;

import EmployeeDao.EmployeeDao;
import Service.EmployeeLogin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class LoginController {
    EmployeeLogin employeeLogin;

    Javalin app;

    public LoginController(Javalin app) {
        employeeLogin = new EmployeeLogin(new EmployeeDao());
        this.app = app;
    }

    public void employeeEndpoint(){
        app.get("hello", this::helloHandler);
        app.post("employee",this::postAddEmployee);
        app.get("employee/{name}",this::getSpecificEmployeeHandler);
        app.post("login", this::loginHandler);
        app.delete("logout", this::logoutHandler);
    }

    private void postAddEmployee(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
         Model.EmployeeLogin newEmployeeLogin = mapper.readValue(context.body(), Model.EmployeeLogin.class);
        Model.EmployeeLogin addedEmployee = employeeLogin.addEmployee(newEmployeeLogin);
        context.json(addedEmployee);
    }
    private void getSpecificEmployeeHandler(Context context) {
        String name = context.pathParam("name");
        Model.EmployeeLogin employee = employeeLogin.getEmployee(name);
        context.json(employee);
    }

    private void loginHandler(Context context) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        LoginCreds loginCreds = mapper.readValue(context.body(), LoginCreds.class);
//        employeeLogin.login(loginCreds.getEmployeeLogin(), loginCreds.getPassword());
//        context.json("Successfully logged in");
    }

    private void logoutHandler(Context context){
//        String employeeUsername = employeeLogin.getEmployee(username);
//        employeeLogin.logout();
//        context.json(employeeUsername + " has logged out");
    }
    public void helloHandler(Context ctx){
        ctx.result("hello world");
    }


}
