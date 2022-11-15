package Model;

public class Reimbursement {
    public String employee;

    private double amount;

    private String approvalStatus;

    private String type;

    public int id;


    private String description;
    public Reimbursement(String employee, double amount, String approvalStatus, String type, String description , int id){

        this.employee = employee;
        this.amount = amount;
        this.approvalStatus = "pending";
        this.id = id;


    }
    public Reimbursement(){

    }

    @Override
    public String toString() {
        return "EmployeeRequestModel.EmployeeReimbursementRequest{" +
                "employee='" + employee + '\'' +
                ", amount=" + amount +
                ", approvalStatus='" + approvalStatus + '\'' +
                '}';
    }

    public String getEmployee() {
        return employee;
    }
    public void setEmployee(String employee){
        this.employee = employee;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
