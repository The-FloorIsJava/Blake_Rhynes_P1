package Main;

import Controller.LoginController;
import Controller.ReimbursementController;
import DAO.EmployeeDao;
import Service.EmployeeLogin;
import io.javalin.Javalin;


public class ReimbursementRequestMain {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8080);


        EmployeeLogin elogin = new EmployeeLogin(new EmployeeDao());

        ReimbursementController reimbursementController = new ReimbursementController(app, elogin);
        reimbursementController.reimbursementEndpoint(app);

        new LoginController(app,elogin).employeeEndpoint();
        new ReimbursementController(app,elogin);

    }
}
