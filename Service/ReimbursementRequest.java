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

    public void addRequest(Reimbursement reimbursement){
        reimbursementDao.create(reimbursement);
    }


    public Reimbursement getReimbursement(int id){

    return reimbursementDao.findById(id);
}

    public Reimbursement managerApproval(int id) {
            return reimbursementDao.approve(id);
    }

    public Reimbursement managerDenial(int id) {
            return reimbursementDao.deny(id);
    }

    public List<Reimbursement> getAllRequests(){

        return reimbursementDao.findAll();
}

    public List<Reimbursement> getPendingRequests(){
        return reimbursementDao.findAllPending();
}

    public List<Reimbursement> getPersonalRequests(String username){
        return reimbursementDao.findPersonalRequests(username);
    }


}
