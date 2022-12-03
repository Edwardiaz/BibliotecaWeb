/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import Config.Conexion;
import Modelo.Rol;
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
public class RolDAO{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int rows = 0;
    Rol rol = new Rol();

    public List listar() {
        try{
            ArrayList<Rol> list = new ArrayList<>();

            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, rol, numero_prestamos, dias_prestamo FROM rol";
        
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId(rs.getInt("id"));
                rol.setRol(rs.getString("rol"));
                rol.setNumero_prestamos(rs.getInt("numero_prestamos"));
                rol.setDias_prestamo(rs.getInt("dias_prestamo"));
                list.add(rol);
            }
            return list;
            
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        } 
    }

    public int agregar(Rol rol) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "INSERT INTO `rol`(rol, numero_prestamos, dias_prestamo) VALUES(?,?,?)";
        
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, rol.getRol());
            ps.setInt(2, rol.getNumero_prestamos());
            ps.setInt(3, rol.getDias_prestamo());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        } 
    }    
    
    public boolean rolExiste(String rol) throws SQLException{
        try {
            int rows = 0;
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT * FROM rol WHERE rol ='"+ rol +"'";

            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
            return true;
            }
            
        } catch (SQLException ex){
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        }
        
        return false;
    }
    
    public Rol ver(String id) {
        try{
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, rol, numero_prestamos, dias_prestamo FROM rol WHERE id ='"+ id +"'";

            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            Rol rol = new Rol();
            
            if(rs.next()) {
                rol.setId(rs.getInt("id"));
                rol.setRol(rs.getString("rol"));
                rol.setNumero_prestamos(rs.getInt("numero_prestamos"));
                rol.setDias_prestamo(rs.getInt("dias_prestamo"));
            }
            
            return rol;
            
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        }
    }

    public int actualizar(Rol rol) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "UPDATE rol SET rol = ?, numero_prestamos = ?, dias_prestamo = ? WHERE id ='" + rol.id + "'";
        
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, rol.getRol());
            ps.setInt(2, rol.getNumero_prestamos());
            ps.setInt(3, rol.getDias_prestamo());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            String sql = "DELETE FROM rol WHERE id ='"+ id +"'";

            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);

            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        } 
    }    
}
