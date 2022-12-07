/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import Config.Conexion;
import Modelo.Genero;
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
public class GeneroDAO{
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    int rows = 0;
    Genero genero = new Genero();

    public List listar() {
        try{
            ArrayList<Genero> list = new ArrayList<>();

            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, nombre_genero FROM generos";
        
            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Genero genero = new Genero();
                genero.setId(rs.getInt("id"));
                genero.setNombre_genero(rs.getString("nombre_genero"));
                list.add(genero);
            }
            return list;
            
        } catch (SQLException ex){
            Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int agregar(Genero genero) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "INSERT INTO generos (nombre_genero) VALUES(?)";
        
            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            ps.setString(1, genero.getNombre_genero());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }    
    
    public boolean generoExiste(String nombre) throws SQLException{
        try {
            int rows = 0;
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT * FROM generos WHERE nombre_genero ='"+ nombre +"'";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
            return true;
            }
            
        } catch (SQLException ex){
            Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public Genero ver(String id) {
        try{
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, nombre_genero FROM generos WHERE id ='"+ id +"'";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            Genero genero = new Genero();
            
            if(rs.next()) {
                genero.setId(rs.getInt("id"));
                genero.setNombre_genero(rs.getString("nombre_genero"));
            }
            
            return genero;
            
        } catch (SQLException ex){
            Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int actualizar(Genero genero) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "UPDATE generos SET nombre_genero = ? WHERE id ='" + genero.id + "'";
        
            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            ps.setString(1, genero.getNombre_genero());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int eliminar(String id) {
        try {
            int rows = 0;
            //Codigo SQL para insertar borrar registro
            String sql = "DELETE FROM generos WHERE id ='"+ id +"'";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);

            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } 
    }    
}
