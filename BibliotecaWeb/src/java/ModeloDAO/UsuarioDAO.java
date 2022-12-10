/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import Config.Conexion;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eduardo Trujillo
 */
public class UsuarioDAO{
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    int rows = 0;
    Usuario usr = new Usuario();

    public List<Usuario> listar() throws SQLException{
        try{
        ArrayList<Usuario> list = new ArrayList<>();
        
        //Codigo SQL para insertar registro a tabla
        String sql = "SELECT usuarios.id AS idUser,nombre, apellido, nickname, email, pass, mora, date_format(fecha_nacimiento, \"%d/%m/%Y\") AS fecha_nacimiento, rol "
                + "FROM biblioteca.usuarios JOIN rol ON usuarios.codigo_rol = rol.id";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Usuario usr = new Usuario();
                usr.setId(rs.getInt("idUser"));
                usr.setNombre(rs.getString("nombre"));
                usr.setApellido(rs.getString("apellido"));
                usr.setNickname(rs.getString("nickname"));
                usr.setEmail(rs.getString("email"));
                usr.setRol(rs.getString("rol"));
                usr.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                usr.setMora(rs.getFloat("mora"));
                list.add(usr);
            }
            
            return list;
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    
    }

    public int agregar(Usuario usr) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "INSERT INTO `usuarios`(nombre, apellido, nickname, email, pass, mora, fecha_nacimiento, codigo_rol) VALUES(?,?,?,?,?,?,?,?)";
        
            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            ps.setString(1, usr.getNombre());
            ps.setString(2, usr.getApellido());
            ps.setString(3, usr.getNickname());
            ps.setString(4, usr.getEmail()); 
            ps.setString(5, usr.getPass());
            ps.setFloat(6, usr.getMora());
            ps.setString(7, usr.getFecha_nacimiento());
            ps.setString(8, usr.getRol());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public boolean usrExiste(String email) throws SQLException{
        try {
            int rows = 0;
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT * FROM usuarios WHERE email ='"+ email +"'";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
            return true;
            }
            
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }    

    public Usuario ver(String id) {
        try{
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT usuarios.id AS idUser,nombre, apellido, nickname, email, pass, mora, fecha_nacimiento, rol, rol.id AS rolId "
                + "FROM biblioteca.usuarios JOIN rol ON usuarios.codigo_rol = rol.id  WHERE usuarios.id ='"+ id +"'";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            Usuario usr = new Usuario();
            
            if(rs.next()) {
                usr.setId(rs.getInt("idUser"));
                usr.setNombre(rs.getString("nombre"));
                usr.setApellido(rs.getString("apellido"));
                usr.setNickname(rs.getString("nickname"));
                usr.setEmail(rs.getString("email"));
                usr.setRol(rs.getString("rol"));
                usr.setCodigo_rol(rs.getInt("rolId"));
                usr.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                usr.setPass(rs.getString("pass"));
                usr.setMora(rs.getFloat("mora"));
            }
            
            return usr;
            
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int actualizar(Usuario usr) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, nickname = ?, email = ?,pass = ?, mora = ?, fecha_nacimiento = ?,"
                    + " codigo_rol = ? WHERE usuarios.id ='"+ usr.id +"'";
        
            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            ps.setString(1, usr.getNombre());
            ps.setString(2, usr.getApellido());
            ps.setString(3, usr.getNickname());
            ps.setString(4, usr.getEmail()); 
            ps.setString(5, usr.getPass());
            ps.setFloat(6, usr.getMora());
            ps.setString(7, usr.getFecha_nacimiento());
            ps.setString(8, usr.getRol());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }    

    public int eliminar(String id) {
        try {
            int rows = 0;
            //Codigo SQL para insertar registro a tabla
            String sql = "DELETE FROM usuarios WHERE id ='"+ id +"'";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);

            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public Usuario login(String nickname, String pass) throws SQLException{
        try{
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT id, nombre, apellido, nickname, codigo_rol FROM usuarios WHERE nickname='"+ nickname +"' AND pass='"+ pass +"'";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            Usuario user = new Usuario();
            
            if(rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setNickname(rs.getString("nickname"));
                user.setCodigo_rol(rs.getInt("codigo_rol"));
            }
            
            return user;
            
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int actualizarPerfil(Usuario usr) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, nickname = ?, email = ?,pass = ?, fecha_nacimiento = ? WHERE usuarios.id ='"+ usr.id +"'";
        
            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            ps.setString(1, usr.getNombre());
            ps.setString(2, usr.getApellido());
            ps.setString(3, usr.getNickname());
            ps.setString(4, usr.getEmail()); 
            ps.setString(5, usr.getPass());
            ps.setString(6, usr.getFecha_nacimiento());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int pagarMora(String id) {
        try {
            int rows = 0;
            //Codigo SQL para insertar registro a tabla
            String sql = "UPDATE usuarios SET mora = 0 WHERE usuarios.id ='"+ id +"'";

            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);

            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int cambiarPass(Usuario usr) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "UPDATE usuarios SET pass = ? WHERE nickname = ? AND email = ? AND fecha_nacimiento = ?";
        
            if(con == null || con.isClosed()){
                con = Conexion.getConnection();
            }
            
            ps = con.prepareStatement(sql);
            
            ps.setString(1, usr.getPass());
            ps.setString(2, usr.getNickname());
            ps.setString(3, usr.getEmail()); 
            ps.setString(4, usr.getFecha_nacimiento());
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }    
}
