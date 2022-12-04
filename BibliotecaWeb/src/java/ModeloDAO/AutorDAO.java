/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import Config.Conexion;
import Modelo.Autor;
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
public class AutorDAO{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int rows = 0;
    Autor autor = new Autor();

    public List listar() {
        try{
            ArrayList<Autor> list = new ArrayList<>();

            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, nombre_autor FROM autores";
        
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Autor autor = new Autor();
                autor.setId(rs.getInt("id"));
                autor.setNombre_autor(rs.getString("nombre_autor"));
                list.add(autor);
            }
            return list;
            
        } catch (SQLException ex){
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        } 
    }

    public int agregar(Autor autor) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "INSERT INTO `autores`(nombre_autor) VALUES(?)";
        
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, autor.getNombre_autor());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        } 
    }    
    
    public boolean autorExiste(String nombre) throws SQLException{
        try {
            int rows = 0;
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT * FROM autores WHERE nombre_autor ='"+ nombre +"'";

            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
            return true;
            }
            
        } catch (SQLException ex){
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        }
        
        return false;
    }
    
    public Autor ver(String id) {
        try{
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, nombre_autor FROM autores WHERE id ='"+ id +"'";

            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            Autor autor = new Autor();
            
            if(rs.next()) {
                autor.setId(rs.getInt("id"));
                autor.setNombre_autor(rs.getString("nombre_autor"));
            }
            
            return autor;
            
        } catch (SQLException ex){
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        }
    }

    public int actualizar(Autor autor) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "UPDATE autores SET nombre_autor = ? WHERE id ='" + autor.id + "'";
        
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, autor.getNombre_autor());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            String sql = "DELETE FROM autores WHERE id ='"+ id +"'";

            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);

            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        } 
    }    
}
