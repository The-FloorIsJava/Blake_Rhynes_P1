package Service;

import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {
    List<Employee> employeeReimbursements;
    public ReimbursementService(){
//        employeeReimbursements = new ArrayList<>();
    }

    public void addRequest(String name, double reqAmount, String approvalStatus){
Employee newRequest = new Employee(name, reqAmount, approvalStatus);
employeeReimbursements.add(newRequest);
    }

    public Employee getRequest(String name){
        for (int i = 0; i<employeeReimbursements.size();i++){
            if(employeeReimbursements.get(i).name.equals(name)){
                return employeeReimbursements.get(i);
            }
        }
        return null;
    }

    public void requestDecision(String name, double reqAmount, String approvalStatus){

    }
}
