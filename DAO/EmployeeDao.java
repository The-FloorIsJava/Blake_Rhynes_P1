package DAO;

import Model.Employee;
import Util.ConnectionFactory;
import Util.Interface.Crudable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//need to provide implementation
public class EmployeeDao implements Crudable<Employee> {
    @Override
    public Employee create(Employee newEmployee) {

        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

            String sql = "insert into login_information(employee_role, username, password) values ('manager',?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1,newEmployeeLogin.getEmployee_role());
            preparedStatement.setString(1, newEmployee.getUsername());
            preparedStatement.setString(2, newEmployee.getPassword());

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0){
                throw new RuntimeException("Employee not found");
            }

            return newEmployee;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }


    }

    public Employee createEmployee(Employee newEmployee) {

        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

            String sql = "insert into login_information(username, password) values (?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newEmployee.getUsername());
            preparedStatement.setString(2, newEmployee.getPassword());

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0){
                throw new RuntimeException("Employee not found");
            }

            return newEmployee;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }


    }


    @Override
    public List<Employee> findAll() {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

            List<Employee> employees = new ArrayList<>();

            String sql = "select * from login_information";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Employee employee = new Employee();

                employee.setEmployee_role(resultSet.getString("employee_role"));
                employee.setUsername(resultSet.getString("username"));
                employee.setPassword(resultSet.getString("password"));

                employees.add(employee);

            }
            return employees;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee findById(int id) {
        return null;
    }

    @Override
    public boolean update(Employee updatedEmployee) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
    

    public Employee loginCheck(String employee_role, String username, String password){

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

            Employee employee = new Employee();

            employee.setUsername(resultSet.getString("username"));
            employee.setPassword(resultSet.getString("password"));
            employee.setEmployee_role(resultSet.getString("employee_role"));

            return employee;

        }catch (SQLException e){
            e.printStackTrace();
            return null;

        }
    }
}
