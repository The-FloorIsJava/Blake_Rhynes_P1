package Controller;

import DAO.ReimbursementDao;
import Model.Reimbursement;
import Service.EmployeeLogin;
import Service.ReimbursementRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class ReimbursementController {
    ReimbursementRequest reimbursementRequest;
    EmployeeLogin eLogin;

    Javalin app;
    public ReimbursementController(Javalin app, EmployeeLogin eLogin){
        this.eLogin = eLogin;
        this.reimbursementRequest = new ReimbursementRequest(new ReimbursementDao());
        this.app = app;
    }
    public void reimbursementEndpoint(Javalin app){

//        app.get("hello", this::helloHandler);
        app.post("reimbursement",this::postReimbursementHandler);
        app.get("reimbursementRequests",this::getAllReimbursements);
        app.get("reimbursement/{id}",this::getSpecificReimbursement);
        app.post("idApproval/{id}",this::managerApproval);
        app.post("idDenial/{id}",this::managerDenial);
        app.get("getPending",this::getPendingRequests);

    }
    private void getSpecificReimbursement(Context context) {
        if (isManager()){
            String id = context.pathParam("id");
            Reimbursement reimbursement = reimbursementRequest.getReimbursement(Integer.parseInt(id));
            context.json(reimbursement);
        }
    }
    private void managerApproval(Context context){
        if (isManager()){
            String id = context.pathParam("id");
            reimbursementRequest.managerApproval(Integer.parseInt(id));
            Reimbursement reimbursement = reimbursementRequest.getReimbursement(Integer.parseInt(id));
            context.json(reimbursement);
        }
    }
    private void managerDenial(Context context) {
        if (isManager()){
            String id = context.pathParam("id");
            reimbursementRequest.managerDenial(Integer.parseInt(id));
            Reimbursement reimbursement = reimbursementRequest.getReimbursement(Integer.parseInt(id));
            context.json(reimbursement);
        }
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
        if (isManager()){
            List<Reimbursement> allRequests = reimbursementRequest.getAllRequests();
            context.json(allRequests);
        }
    }
    private void getPendingRequests(Context context) {
        if (isManager()){
            List<Reimbursement> allPendingRequests = reimbursementRequest.getPendingRequests();
            context.json(allPendingRequests);
        }
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

    public boolean isManager(){
        if (eLogin.getEmployeeSession().getEmployee_role().equals("manager")){
            return true;
        }else {
            return false;
        }

    }


}
