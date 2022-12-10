/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import Config.Conexion;
import Modelo.Prestamos;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Equipo-HP
 */
public class PrestamoDAO {

    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    int rows = 0;
    Prestamos pres = new Prestamos();

    public List<Prestamos> listar() throws SQLException {
        try {
            ArrayList<Prestamos> list = new ArrayList<>();

            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT prestamos.id, materiales.id AS Material, materiales.titulo AS CodM, usuarios.id AS Usuario, usuarios.nickname AS Nickname, prestamos.codigo_usuario AS cod, "
                    + "date_format(fecha_prestamo, \"%d/%m/%Y\") AS fecha_prestamo, date_format(fecha_devolucion, \"%d/%m/%Y\") AS fecha_devolucion "
                    + " FROM biblioteca.prestamos LEFT JOIN materiales ON materiales.id = prestamos.codigo_material LEFT JOIN usuarios ON usuarios.id = prestamos.codigo_usuario WHERE prestamos.Activo=1";

            if (con == null || con.isClosed()) {
                con = Conexion.getConnection();
            }

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Prestamos pres = new Prestamos();
                pres.setId(rs.getInt("id"));
                pres.setCodigoMaterial(rs.getString("Material"));
                pres.setMaterial(rs.getString("CodM"));
                pres.setCodigoUsuario(rs.getInt("Usuario"));
                pres.setUsuario(rs.getString("Nickname"));
                pres.setFechaPrestamo(rs.getString("fecha_prestamo"));
                pres.setFechaDevolucion(rs.getString("fecha_devolucion"));
                list.add(pres);

                System.out.println(list);
            }

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean existeMaterial(String material) throws SQLException {
        boolean res = false;

        String sql = "SELECT titulo FROM materiales WHERE id='" + material + "' LIMIT 1";

        if (con == null || con.isClosed()) {
            con = Conexion.getConnection();
        }

        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        if (rs.next()) {
            res = true;
        }
        return res;
    }

    public boolean existeUsuario(String usuario) throws SQLException {
        boolean res = false;

        String sql = "SELECT * FROM usuarios WHERE nickname='" + usuario + "' LIMIT 1";

        if (con == null || con.isClosed()) {
            con = Conexion.getConnection();
        }

        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        if (rs.next()) {
            res = true;
        }
        return res;
    }

    public boolean verificarMora(int nickname) throws SQLException {

        try {
            int rows = 0;

            String sql = "SELECT mora FROM usuarios WHERE id='" + nickname + "' LIMIT 1";

            if (con == null || con.isClosed()) {
                con = Conexion.getConnection();
            }

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                float m = rs.getFloat("mora");
                if (m > 0) {
                    return true;
                }
            }

        } catch (Exception e) {
            Logger.getLogger(PrestamoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;

    }

    public boolean verificarPrestamo(int nickname, String material) throws SQLException {

        try {
            int rows = 0;
            String sql = "SELECT prestamos.id, prestamos.codigo_material AS Material, usuarios.nickname AS Usuario, prestamos.fecha_prestamo, prestamos.fecha_devolucion "
                    + "FROM prestamos LEFT JOIN materiales ON materiales.id = prestamos.codigo_material LEFT JOIN usuarios ON usuarios.id = prestamos.codigo_usuario "
                    + "WHERE prestamos.codigo_material='" + material + "' AND prestamos.codigo_usuario='" + nickname + "'";

            if (con == null || con.isClosed()) {
                con = Conexion.getConnection();
            }

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            Logger.getLogger(PrestamoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;

    }

    public boolean materialDisponible(String material) throws SQLException {
        boolean res = false;

        String sql = "SELECT unidades_disponibles FROM materiales WHERE id = '" + material + "' LIMIT 1";

        if (con == null || con.isClosed()) {
            con = Conexion.getConnection();
        }

        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        if (rs.next()) {
            int u = Integer.parseInt(rs.getString("unidades_disponibles"));
            if (u >= 1) {
                res = true;
            }
        }
        return res;
    }

    public int agregarPrestamo(int nickname, String material) throws SQLException {
        try {
            rows = 0;

            java.util.Date hoy = new java.util.Date();
            java.util.Date ahora = establecerFecha(hoy, 0);

            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            String prestamo_hoy = formato.format(ahora);
            java.sql.Date conversion = null;

            try {
                java.util.Date fecha = formato.parse(prestamo_hoy);
                conversion = new java.sql.Date(fecha.getTime());
            } catch (Exception e) {

            }

            java.util.Date actual = new java.util.Date();

            java.util.Date regresar = fechaDevolucion(actual, 5);
            SimpleDateFormat formato_dev = new SimpleDateFormat("dd-MM-yyyy");
            String prestamo_dev = formato_dev.format(regresar);
            java.sql.Date devolver = null;

            try {
                java.util.Date fecha = formato.parse(prestamo_dev);
                conversion = new java.sql.Date(fecha.getTime());
            } catch (Exception e) {

            }

            //Codigo SQL para insertar registro a tabla
            //INSERT INTO `Prestamos`(codigo_material, codigo_usuario, fecha_prestamo, fecha_devolucion, Activo) VALUES('LIB00001', 3, CURRENT_DATE, DATE_ADD(CURRENT_DATE, INTERVAL 5 DAY), 1);
            String sql = "INSERT INTO `Prestamos`(codigo_material, codigo_usuario, fecha_prestamo, fecha_devolucion, Activo) VALUES(?,?,?,?,1)";

            if (con == null || con.isClosed()) {
                con = Conexion.getConnection();
            }

            ps = con.prepareStatement(sql);

            ps.setString(1, material);
            ps.setInt(2, nickname);
            ps.setDate(3, (Date) ahora);
            ps.setDate(4, (Date) regresar);

            rows = ps.executeUpdate();
            return rows;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public static java.util.Date establecerFecha(java.util.Date ahora, int dias) {
        Calendar cale = new GregorianCalendar();
        cale.setTimeInMillis(ahora.getTime());
        cale.add(Calendar.DATE, dias);
        return new java.sql.Date(cale.getTimeInMillis());
    }

    public static java.util.Date fechaDevolucion(java.util.Date ahora, int dias) {
        Calendar cale = new GregorianCalendar();
        cale.setTimeInMillis(ahora.getTime());
        cale.add(Calendar.DATE, dias);
        return new java.sql.Date(cale.getTimeInMillis());
    }

    public int regresarMaterial(String material, int idUsuario) throws SQLException {
        int row = 0;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet re = null;

        String sql = "SELECT prestamos.id, materiales.id, prestamos.fecha_devolucion, DATEDIFF(now(),prestamos.fecha_devolucion) AS Atraso, "
                + "DATEDIFF(now(),prestamos.fecha_devolucion) * 0.5 AS Mora,Activo "
                + "FROM prestamos LEFT JOIN usuarios ON prestamos.codigo_usuario = usuarios.id "
                + "LEFT JOIN materiales ON materiales.id = prestamos.codigo_material"
                + " WHERE prestamos.codigo_usuario='" + idUsuario + "' AND Activo=1 AND prestamos.codigo_material ='" + material + "'";

        if (con == null || con.isClosed()) {
            con = Conexion.getConnection();
        }

        stmt = con.prepareStatement(sql);
        re = stmt.executeQuery();
        if (re.next()) {
            Float mora = re.getFloat("Mora");

            if (mora > 0) {
                stmt = con.prepareStatement("UPDATE usuarios SET mora='" + mora + "' WHERE usuarios.id='" + idUsuario + "'");
                stmt.executeUpdate();

                stmt2 = con.prepareStatement("UPDATE prestamos SET Activo=0 WHERE prestamos.codigo_usuario='" + idUsuario + "'");
                stmt2.executeUpdate();

            } else {
                stmt = con.prepareStatement("DELETE FROM prestamos WHERE codigo_material = '" + material + "' AND codigo_usuario = '" + idUsuario + "' LIMIT 1");
                stmt.executeUpdate();

                stmt2 = con.prepareStatement("UPDATE materiales SET unidades_disponibles = unidades_disponibles+1 WHERE id = '" + material + "';");
                stmt2.executeUpdate();

            }

        }
        return row;
    }

}
