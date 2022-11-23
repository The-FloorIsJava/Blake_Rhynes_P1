    package Service;

    import DAO.ReimbursementDao;
    import Model.Employee;
    import Model.Reimbursement;

    import java.util.List;

    public class ReimbursementRequest extends Employee {



    private final ReimbursementDao reimbursementDao;

    public ReimbursementRequest(ReimbursementDao reimbursementDao){
        this.reimbursementDao = reimbursementDao;
    }
    Employee myEmployee;

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
//public void managerApproval(int id) {
//    for (int i = 0; i < reimbursementRequests.size(); i++) {
//        Reimbursement r = reimbursementRequests.get(i);
//        if (r.getId() == (id)) {
//            r.setApprovalStatus("Approved");
//        }
//    }
//}
    public Reimbursement managerApproval(int id) {
            return reimbursementDao.approve(id);
    }

//    public void managerDenial(int id) {
//        for (int i = 0; i < reimbursementRequests.size(); i++) {
//            Reimbursement r = reimbursementRequests.get(i);
//            if (r.getId() == (id)) {
//                r.setApprovalStatus("Denied");
//            }
//        }
//    }
    public Reimbursement managerDenial(int id) {
            return reimbursementDao.deny(id);
    }

    public List<Reimbursement> getAllRequests(){

        return reimbursementDao.findAll();
}

    public List<Reimbursement> getPendingRequests(){
        return reimbursementDao.findAllPending();
}

}
