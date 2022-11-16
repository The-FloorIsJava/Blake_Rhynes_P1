package Controller;

import Model.Reimbursement;
import Service.ReimbursementRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class ReimbursementController {
    ReimbursementRequest reimbursementRequest;
    public ReimbursementController(){
        reimbursementRequest = new ReimbursementRequest();
    }
    public void reimbursementEndpoint(Javalin app){
//        Javalin app = Javalin.create().start(8080);

        app.get("hello", this::helloHandler);
        app.get("reimbursement",this::postReimbursementHandler);
        app.get("reimbursementRequests",this::getAllReimbursements);
        app.get("reimbursement/{id}",this::getSpecificReimbursement);
        app.get("idApproval",this::managerApproval);
        app.get("idDenial",this::managerDenial);

    }
    private void getSpecificReimbursement(Context context) {
        String employee = context.pathParam("employee");
        Reimbursement reimbursement = reimbursementRequest.getReimbursement(employee);
        context.json(reimbursement);
    }

    private void getAllReimbursements(Context context) {
        List<Reimbursement> allRequests = reimbursementRequest.getAllRequests();
//        similar as context.result, but the content type is json rather than text.
        context.json(allRequests);
    }

    private void postReimbursementHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Reimbursement reimbursement = mapper.readValue(context.body(), Reimbursement.class);
        reimbursementRequest.addRequest(reimbursement);
        context.json(reimbursement);
    }

    private void managerApproval(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Reimbursement reimbursement = mapper.readValue(context.body(), Reimbursement.class);
        reimbursementRequest.managerApproval(reimbursement.id);
        context.json(reimbursement);
    }
    private void managerDenial(Context context)throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Reimbursement reimbursement = mapper.readValue(context.body(), Reimbursement.class);
        reimbursementRequest.managerDenial(reimbursement.id);
        context.json(reimbursement);
    }

    public void helloHandler(Context ctx){
        ctx.result("hello from the menu");
    }


}
