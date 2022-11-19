package test;

import Util.ConnectionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryTestSuite {

    @Test
    public  void test_getConnection_returnValidConnection_givenProvidedCredentialsAreCorrect(){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            System.out.println(connection);
            Assertions.assertNotNull(connection);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
