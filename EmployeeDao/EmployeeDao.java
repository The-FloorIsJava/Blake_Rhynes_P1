package EmployeeDao;

import Model.EmployeeLogin;
import Util.ConnectionFactory;
import Util.Interface.Crudable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//need to provide implementation
public class EmployeeDao implements Crudable<EmployeeLogin> {
    @Override
    public EmployeeLogin create(EmployeeLogin newEmployeeLogin) {

        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

            String sql = "insert into login_information(employee_role, username, password) values ('manager',?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1,newEmployeeLogin.getEmployee_role());
            preparedStatement.setString(1,newEmployeeLogin.getUsername());
            preparedStatement.setString(2,newEmployeeLogin.getPassword());

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0){
                throw new RuntimeException("Employee not found");
            }

            return newEmployeeLogin;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }


    }

    public EmployeeLogin createEmployee(EmployeeLogin newEmployeeLogin) {

        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

            String sql = "insert into login_information(username, password) values (?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,newEmployeeLogin.getUsername());
            preparedStatement.setString(2,newEmployeeLogin.getPassword());

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0){
                throw new RuntimeException("Employee not found");
            }

            return newEmployeeLogin;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }


    }


    @Override
    public List<EmployeeLogin> findAll() {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

            List<EmployeeLogin> employeeLogins = new ArrayList<>();

            String sql = "select * from login_information";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                EmployeeLogin employeeLogin = new EmployeeLogin();

                employeeLogin.setEmployee_role(resultSet.getString("employee_role"));
                employeeLogin.setUsername(resultSet.getString("username"));
                employeeLogin.setPassword(resultSet.getString("password"));

                employeeLogins.add(employeeLogin);

            }
            return employeeLogins;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public EmployeeLogin findById(int id) {
        return null;
    }

    @Override
    public boolean update(EmployeeLogin updatedEmployeeLogin) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
    

    public EmployeeLogin loginCheck(String employee_role, String username, String password){

        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

            String sql = "select * from login_information where employee_role  = ? and username = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,employee_role);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                throw new RuntimeException("Entered information for " + username + " was incorrect");
            }

            EmployeeLogin employeeLogin = new EmployeeLogin();

            employeeLogin.setUsername(resultSet.getString("username"));
            employeeLogin.setPassword(resultSet.getString("password"));
            employeeLogin.setEmployee_role(resultSet.getString("employee_role"));

            return employeeLogin;

        }catch (SQLException e){
            e.printStackTrace();
            return null;

        }
    }
}
