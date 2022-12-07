/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import Config.Conexion;
import Modelo.Director;
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
public class DirectorDAO{
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    int rows = 0;
    Director director = new Director();

    public List listar() {
        try{
            ArrayList<Director> list = new ArrayList<>();

            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, nombre_director FROM directores";
        
            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Director director = new Director();
                director.setId(rs.getInt("id"));
                director.setNombre_director(rs.getString("nombre_director"));
                list.add(director);
            }
            return list;
            
        } catch (SQLException ex){
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int agregar(Director director) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "INSERT INTO directores (nombre_director) VALUES(?)";
        
            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            ps.setString(1, director.getNombre_director());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }    
    
    public boolean directorExiste(String nombre) throws SQLException{
        try {
            int rows = 0;
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT * FROM directores WHERE nombre_director ='"+ nombre +"'";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
            return true;
            }
            
        } catch (SQLException ex){
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public Director ver(String id) {
        try{
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, nombre_director FROM directores WHERE id ='"+ id +"'";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            Director director = new Director();
            
            if(rs.next()) {
                director.setId(rs.getInt("id"));
                director.setNombre_director(rs.getString("nombre_director"));
            }
            
            return director;
            
        } catch (SQLException ex){
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int actualizar(Director director) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "UPDATE directores SET nombre_director = ? WHERE id ='" + director.id + "'";
        
            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            ps.setString(1, director.getNombre_director());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int eliminar(String id) {
        try {
            int rows = 0;
            //Codigo SQL para insertar borrar registro
            String sql = "DELETE FROM directores WHERE id ='"+ id +"'";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);

            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }    
}
