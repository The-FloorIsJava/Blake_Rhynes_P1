package Main;

import Controller.LoginController;
import Controller.ReimbursementController;
import io.javalin.Javalin;

public class ReimbursementRequestMain {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8080);

        ReimbursementController reimbursementController = new ReimbursementController();
        reimbursementController.reimbursementEndpoint(app);

        new LoginController(app).employeeEndpoint();

        //need to add login feature

    }
}
