package Controller;

import EmployeeDao.ReimbursementDao;
import Model.Reimbursement;
import Service.EmployeeLogin;
import Service.ReimbursementRequest;
import Util.DTO.LoginCreds;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.postgresql.util.PSQLException;

import java.util.List;

public class ReimbursementController {
    ReimbursementRequest reimbursementRequest;

    EmployeeLogin eLogin;

    LoginCreds loginCreds;
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
        app.post("idApproval/{id}",this::managerApproval);
        app.post("idDenial/{id}",this::managerDenial);
        app.get("getPending",this::getPendingRequests);

    }
    private void getSpecificReimbursement(Context context) {
        String id = context.pathParam("id");
        Reimbursement reimbursement = reimbursementRequest.getReimbursement(Integer.parseInt(id));
        context.json(reimbursement);
    }
    private void managerApproval(Context context){
            String id = context.pathParam("id");
            reimbursementRequest.managerApproval(Integer.parseInt(id));
            Reimbursement reimbursement = reimbursementRequest.getReimbursement(Integer.parseInt(id));
            context.json(reimbursement);
    }
    private void managerDenial(Context context)throws PSQLException {
        String id = context.pathParam("id");
        reimbursementRequest.managerDenial(Integer.parseInt(id));
        Reimbursement reimbursement = reimbursementRequest.getReimbursement(Integer.parseInt(id));
        context.json(reimbursement);
    }
//    private void managerApproval(Context context) throws JsonProcessingException{
//        ObjectMapper mapper = new ObjectMapper();
//        Reimbursement reimbursement = mapper.readValue(context.body(), Reimbursement.class);
//        reimbursementRequest.managerApproval(reimbursement.getId());
//        context.json(reimbursement);
//    }
//    private void managerDenial(Context context)throws JsonProcessingException{
//        ObjectMapper mapper = new ObjectMapper();
//        Reimbursement reimbursement = mapper.readValue(context.body(), Reimbursement.class);
//        reimbursementRequest.managerDenial(reimbursement.getId());
//        context.json(reimbursement);
//    }

    private void getAllReimbursements(Context context) {
        List<Reimbursement> allRequests = reimbursementRequest.getAllRequests();
//        similar as context.result, but the content type is json rather than text.
        context.json(allRequests);
    }
    private void getPendingRequests(Context context) {
        List<Reimbursement> allPendingRequests = reimbursementRequest.getPendingRequests();
//        similar as context.result, but the content type is json rather than text.
        context.json(allPendingRequests);
    }

    private void postReimbursementHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Reimbursement reimbursement = mapper.readValue(context.body(), Reimbursement.class);
        reimbursementRequest.addRequest(reimbursement);
        context.json(reimbursement);
    }



    public void helloHandler(Context ctx){
        ctx.result("hello from the menu");
    }


}
