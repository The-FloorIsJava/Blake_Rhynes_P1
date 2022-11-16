package Main;

import Controller.ReimbursementController;
import io.javalin.Javalin;

public class ReimbursementRequestMain {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8080);

        ReimbursementController reimbursementController = new ReimbursementController();
        reimbursementController.reimbursementEndpoint(app);

        //need to add login feature

    }
}
