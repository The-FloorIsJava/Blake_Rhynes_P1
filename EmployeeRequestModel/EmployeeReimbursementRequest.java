package EmployeeRequestModel;

public class EmployeeReimbursementRequest {
    public String name;

    public double amount;

    public String approvalStatus;
    public EmployeeReimbursementRequest(String name, double amount, String approvalStatus ){

        this.name = name;
        this.amount = amount;
        this.approvalStatus = "pending";


    }
    public EmployeeReimbursementRequest(){

    }

    @Override
    public String toString() {
        return "EmployeeRequestModel.EmployeeReimbursementRequest{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", approvalStatus='" + approvalStatus + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
