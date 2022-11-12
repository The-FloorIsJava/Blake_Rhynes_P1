package RequestModel;

public class EmployeeReimbursementRequest {

    String name;
    double amount;
    String approvalStatus;
    public EmployeeReimbursementRequest(String name, double amount, String approvalStatus){
this.name = name;
this.amount = amount;
this.approvalStatus = approvalStatus;
    }
}
