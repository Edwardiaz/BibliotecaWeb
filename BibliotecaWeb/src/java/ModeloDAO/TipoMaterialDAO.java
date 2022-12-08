/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import Config.Conexion;
import Modelo.TipoMaterial;
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
 * @author Jorge Diaz
 */
public class TipoMaterialDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int rows = 0;
    TipoMaterial tipoMaterial = new TipoMaterial();

    public List listar() {
        try{
            ArrayList<TipoMaterial> list = new ArrayList<>();

            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, tipo_material FROM tipo_material";
        
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                TipoMaterial tipo = new TipoMaterial();
                tipo.setId(rs.getInt("id"));
                tipo.setTipoMaterial(rs.getString("tipo_material"));
                list.add(tipo);
            }
            return list;
            
        } catch (SQLException ex){
            Logger.getLogger(TipoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        } 
    }

    public int agregar(TipoMaterial tipoMaterial) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "INSERT INTO tipo_material (tipo_material) VALUES(?)";
        
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, tipoMaterial.getTipoMaterial());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(TipoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        } 
    }    
    
    public boolean TipoMaterialExiste(String nombre) throws SQLException{
        try {
            int rows = 0;
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT * FROM tipo_material WHERE tipo_material ='"+ nombre +"'";

            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
            return true;
            }
            
        } catch (SQLException ex){
            Logger.getLogger(TipoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        }
        
        return false;
    }
    
    public TipoMaterial ver(String id) {
        try{
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, tipo_material FROM tipo_material WHERE id ='"+ id +"'";

            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            TipoMaterial tipo = new TipoMaterial();
            
            if(rs.next()) {
                tipo.setId(rs.getInt("id"));
                tipo.setTipoMaterial(rs.getString("tipo_material"));
            }
            
            return tipo;
            
        } catch (SQLException ex){
            Logger.getLogger(EditorialDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        }
    }

    public int actualizar(TipoMaterial tipo) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "UPDATE tipo_material SET tipo_material = ? WHERE id ='" + tipo.getId() + "'";
        
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, tipo.getTipoMaterial());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(TipoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            String sql = "DELETE FROM tipo_material WHERE id ='"+ id +"'";

            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);

            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(TipoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        } 
    }  
}
