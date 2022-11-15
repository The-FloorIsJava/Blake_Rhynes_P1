package Controller;

import Service.ReimbursementRequest;

public class reimbursementController {
    ReimbursementRequest reimbursementRequest;
    public reimbursementController(){
        reimbursementRequest = new ReimbursementRequest();
    }
//    public void startAPI(){
//        Javalin app = Javalin.create().start(8080);
//
//        app.get("hello", this::helloHandler);
//        app.get("reimbursement",this::postReimbursementHandler);
//        app.get("reimbursement",this::getAllReimbursements);
//        app.get("reimbursement/{id}",this::getSpecificReimbursement);
//    }


}
