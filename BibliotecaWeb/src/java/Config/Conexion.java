/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author PC
 */
public class Conexion {
    private static Connection connection = null;
    static{
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/DataDB");
            connection = ds.getConnection();            
        } catch (NamingException e) {
            e.printStackTrace();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection(){
        return connection;
    }
}