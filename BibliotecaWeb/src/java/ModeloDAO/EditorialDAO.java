/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import Config.Conexion;
import Modelo.Editorial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo Trujillo
 */
public class EditorialDAO{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int rows = 0;
    Editorial editorial = new Editorial();

    public List listar() {
        try{
            ArrayList<Editorial> list = new ArrayList<>();

            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, nombre_editorial FROM editoriales";
        
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Editorial editorial = new Editorial();
                editorial.setId(rs.getInt("id"));
                editorial.setNombre_editorial(rs.getString("nombre_editorial"));
                list.add(editorial);
            }
            return list;
            
        } catch (SQLException ex){
            Logger.getLogger(EditorialDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        } 
    }

    public int agregar(Editorial editorial) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "INSERT INTO editoriales (nombre_editorial) VALUES(?)";
        
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, editorial.getNombre_editorial());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(EditorialDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        } 
    }    
    
    public boolean editorialExiste(String nombre) throws SQLException{
        try {
            int rows = 0;
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT * FROM editoriales WHERE nombre_editorial ='"+ nombre +"'";

            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
            return true;
            }
            
        } catch (SQLException ex){
            Logger.getLogger(EditorialDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        }
        
        return false;
    }
    
    public Editorial ver(String id) {
        try{
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, nombre_editorial FROM editoriales WHERE id ='"+ id +"'";

            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            Editorial editorial = new Editorial();
            
            if(rs.next()) {
                editorial.setId(rs.getInt("id"));
                editorial.setNombre_editorial(rs.getString("nombre_editorial"));
            }
            
            return editorial;
            
        } catch (SQLException ex){
            Logger.getLogger(EditorialDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        }
    }

    public int actualizar(Editorial editorial) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "UPDATE editoriales SET nombre_editorial = ? WHERE id ='" + editorial.id + "'";
        
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, editorial.getNombre_editorial());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(EditorialDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        } 
    }
    
    public int eliminar(String id) {
        try {
            int rows = 0;
            //Codigo SQL para insertar borrar registro
            String sql = "DELETE FROM editoriales WHERE id ='"+ id +"'";

            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);

            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(EditorialDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        } 
    }    
}
