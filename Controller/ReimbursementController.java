package Controller;

import EmployeeDao.ReimbursementDao;
import Model.Reimbursement;
import Service.ReimbursementRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class ReimbursementController {
    ReimbursementRequest reimbursementRequest;

    Javalin app;
    public ReimbursementController(Javalin app){
        reimbursementRequest = new ReimbursementRequest(new ReimbursementDao());
        this.app = app;
    }
    public void reimbursementEndpoint(Javalin app){

        app.get("hello", this::helloHandler);
        app.post("reimbursement",this::postReimbursementHandler);
        app.get("reimbursementRequests",this::getAllReimbursements);
        app.get("reimbursement/{id}",this::getSpecificReimbursement);
        app.post("idApproval",this::managerApproval);
        app.post("idDenial",this::managerDenial);

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
