package EmployeeDao;

import Model.EmployeeLogin;
import Util.Interface.Crudable;

import java.util.List;
//need to provide implementation
public class EmployeeDao implements Crudable<EmployeeLogin> {
    @Override
    public EmployeeLogin create(EmployeeLogin newObject) {
        return null;
    }

    @Override
    public List<EmployeeLogin> findAll() {
        return null;
    }

    @Override
    public EmployeeLogin findById(int id) {
        return null;
    }

    @Override
    public boolean update(EmployeeLogin updatedObject) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
    

    public EmployeeLogin loginCheck(String username, String password){
        return null;
    }
}
