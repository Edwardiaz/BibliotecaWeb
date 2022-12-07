/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import Config.Conexion;
import Modelo.Artista;
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
public class ArtistaDAO{
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    int rows = 0;
    Artista artista = new Artista();

    public List listar() {
        try{
            ArrayList<Artista> list = new ArrayList<>();

            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, nombre_artista FROM artistas";
        
            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Artista artista = new Artista();
                artista.setId(rs.getInt("id"));
                artista.setNombre_artista(rs.getString("nombre_artista"));
                list.add(artista);
            }
            return list;
            
        } catch (SQLException ex){
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }

    public int agregar(Artista artista) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "INSERT INTO artistas (nombre_artista) VALUES(?)";
        
            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            ps.setString(1, artista.getNombre_artista());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }    
    
    public boolean artistaExiste(String nombre) throws SQLException{
        try {
            int rows = 0;
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT * FROM artistas WHERE nombre_artista ='"+ nombre +"'";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
            return true;
            }
            
        } catch (SQLException ex){
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public Artista ver(String id) {
        try{
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, nombre_artista FROM artistas WHERE id ='"+ id +"'";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            Artista artista = new Artista();
            
            if(rs.next()) {
                artista.setId(rs.getInt("id"));
                artista.setNombre_artista(rs.getString("nombre_artista"));
            }
            
            return artista;
            
        } catch (SQLException ex){
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int actualizar(Artista artista) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "UPDATE artistas SET nombre_artista = ? WHERE id ='" + artista.id + "'";
        
            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            ps.setString(1, artista.getNombre_artista());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int eliminar(String id) {
        try {
            int rows = 0;
            //Codigo SQL para insertar borrar registro
            String sql = "DELETE FROM artistas WHERE id ='"+ id +"'";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);

            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }    
}
