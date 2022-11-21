package Service;

import EmployeeDao.ReimbursementDao;
import Model.Reimbursement;

import java.util.List;

public class ReimbursementRequest {

    private final ReimbursementDao reimbursementDao;

    public ReimbursementRequest(ReimbursementDao reimbursementDao){
        this.reimbursementDao = reimbursementDao;
    }

List<Reimbursement> reimbursementRequests;

//public ReimbursementRequest(){
//    reimbursementRequests = new ArrayList<>();
//}

//public void addRequest(String employee, double amount, String approvalStatus, String type, String description , int id){
//    Reimbursement newReimbursement = new Reimbursement(employee, amount, approvalStatus, type, description, id);
//    reimbursementRequests.add(newReimbursement);
//}

    public void addRequest(Reimbursement reimbursement){
        reimbursementDao.create(reimbursement);
    }


public Reimbursement getReimbursement(int id){

    return reimbursementDao.findById(id);
}
public void managerApproval(int id) {
    for (int i = 0; i < reimbursementRequests.size(); i++) {
        Reimbursement r = reimbursementRequests.get(i);
        if (r.id == (id)) {
            r.setApprovalStatus("Approved");
        }
    }
}

    public void managerDenial(int id) {
        for (int i = 0; i < reimbursementRequests.size(); i++) {
            Reimbursement r = reimbursementRequests.get(i);
            if (r.id == (id)) {
                r.setApprovalStatus("Denied");
            }
        }
    }

public List<Reimbursement> getAllRequests(){

        return reimbursementDao.findAll();
//    return reimbursementRequests;
}

public List<Reimbursement> getPendingRequests(){
        return reimbursementDao.findAllPending();
}

}
