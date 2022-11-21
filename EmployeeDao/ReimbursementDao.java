package EmployeeDao;

import Model.Reimbursement;
import Util.ConnectionFactory;
import Util.Interface.Crudable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDao implements Crudable<Reimbursement> {


    @Override
    public Reimbursement create(Reimbursement newReimbursement) {

        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

            String sql = "insert into reimbursement_ticket(amount, description, employee) values (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, newReimbursement.getAmount());
            preparedStatement.setString(2, newReimbursement.getDescription());
            preparedStatement.setString(3, newReimbursement.getEmployee());

            int checkInsert = preparedStatement.executeUpdate();
            if (checkInsert == 0){
                throw  new RuntimeException("Reimbursement was not added");
            }
            return newReimbursement;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reimbursement> findAll() {

        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "select * from reimbursement_ticket";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Reimbursement reimbursement = new Reimbursement();

                reimbursement.setAmount(resultSet.getDouble("amount"));
                reimbursement.setDescription(resultSet.getString("description"));
                reimbursement.setApprovalStatus(resultSet.getString("status"));
                reimbursement.setEmployee(resultSet.getString("employee"));

                reimbursements.add(reimbursement);

            }
            return reimbursements;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Reimbursement findById(int id) {
        return null;
    }

    @Override
    public boolean update(Reimbursement updatedObject) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
