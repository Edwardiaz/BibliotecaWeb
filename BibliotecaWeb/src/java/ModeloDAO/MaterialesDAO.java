package ModeloDAO;

import Config.Conexion;
import Modelo.Materiales;
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
 * @author Jorge Díaz
 */
public class MaterialesDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    int rows = 0;
    
    public List listar() {
        try{
            ArrayList<Materiales> list = new ArrayList<>();

            //Codigo SQL para insertar registro a tabla
            //String sql = "SELECT * FROM materiales JOIN  ORDER BY id";
            String sql = "SELECT "
                +"materiales.id, materiales.titulo,autores.nombre_autor AS Autor, "
                +"materiales.numero_de_paginas, editoriales.nombre_editorial AS "
                +"Editorial, materiales.isbn, materiales.periodicidad, "
                +"date_format(materiales.fecha_publicacion, '%d/%m/%Y\') "
                +"AS fecha, materiales.ubicacion, materiales.nombre_autor_cv, "
                +"artistas.nombre_artista AS Artista, materiales.duracion, "
                +"materiales.numero_de_canciones,materiales.unidades_disponibles, "
                +"directores.nombre_director as Director, materiales.duracion, "
                +"generos.nombre_genero AS Genero,tipo_material.tipo_material "
                +"AS Tipo from materiales LEFT JOIN directores on "
                +"directores.id = materiales.codigo_director LEFT JOIN generos "
                +"ON generos.id = materiales.codigo_genero LEFT JOIN "
                +"tipo_material ON tipo_material.id = "
                +"materiales.codigo_tipo_material LEFT JOIN "
                +"autores on autores.id = materiales.codigo_autor LEFT JOIN "
                +"artistas ON artistas.id = materiales.codigo_artista LEFT JOIN "
                +"editoriales ON editoriales.id = materiales.codigo_editorial ";
                //+ "WHERE materiales.titulo like '"+titulo+"%'";
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Materiales materiales = new Materiales();
                materiales.setId(rs.getString("id"));
                materiales.setTitulo(rs.getString("titulo"));
                materiales.setTipoMaterial(rs.getString("Tipo"));
                materiales.setAutor(rs.getString("Autor"));
                materiales.setNumeroDePaginas(rs.getString("numero_de_paginas"));
                materiales.setEditorial(rs.getString("Editorial"));
                materiales.setIsbn(rs.getString("isbn"));
                materiales.setPeriodicidad(rs.getString("periodicidad"));
                materiales.setFechaPublicacion(rs.getString("fecha"));
                materiales.setArtista(rs.getString("Artista"));
                materiales.setGenero(rs.getString("Genero"));
                materiales.setDuracion(rs.getString("duracion"));
                materiales.setNumeroDeCanciones(rs.getString("numero_de_canciones"));
                materiales.setDirector(rs.getString("Director"));
                materiales.setUbicacion(rs.getString("ubicacion"));
                materiales.setNombre_autor_CV(rs.getString("nombre_autor_cv"));
                materiales.setUnidadesDisponibles(rs.getInt("unidades_disponibles"));
                /*materiales.setRol(rs.getString("rol"));
                materiales.setNumero_prestamos(rs.getInt("numero_prestamos"));
                materiales.setDias_prestamo(rs.getInt("dias_prestamo"));*/
                list.add(materiales);
            }
            return list;
            
        } catch (SQLException ex){
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        } 
    }

    public int agregar(Materiales materiales) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = "INSERT INTO materiales" +
                    "(id, titulo, codigo_tipo_material, codigo_autor, "
                    + "numero_de_paginas, codigo_editorial, isbn, periodicidad,"
                    + "fecha_publicacion, codigo_artista, codigo_genero, "
                    + "duracion, numero_de_canciones, codigo_director, "
                    + "ubicacion, nombre_autor_CV, unidades_disponibles) " 
                    + "VALUES(?,?,4,?,?,?,?,?,?,?)";
        
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            
            /*ps.setString(1, rol.getRol());
            ps.setInt(2, rol.getNumero_prestamos());
            ps.setInt(3, rol.getDias_prestamo());*/
            
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException ex){
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        } 
    }
    
    /////////////////////////////////////////////
    public int insertarLibros(Materiales materiales, int codigo) 
            throws SQLException{        
        
        String sql = "INSERT INTO materiales (id,titulo,codigo_tipo_material,"
                + "codigo_autor,numero_de_paginas,codigo_editorial,isbn,"
                + "fecha_publicacion,unidades_disponibles, ubicacion) VALUES (?,?,4,?,?,?,?,?,?,?)";
        //PreparedStatement stmt = null;
        ps = conn.prepareStatement(sql);
        Connection conn = null;
        int rows = 0;
        
        //Llamar a los siguientes métodos, pasándole parámetros para obtener sus respectivos ID
        int autorID = consultarAutorPorNombre(materiales.getAutor());
        int editorialID = consultarEditorialPorNombre(materiales.getEditorial());

        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            int index = 1;
            if(codigo==1){
                ps.setString(index++, crearID("LIB"));
            } else {
                ps.setString(index++, crearID("OBR"));   
            }
            ps.setString(index++, materiales.getTitulo());
            ps.setInt(index++, autorID);
            ps.setString(index++, materiales.getNumeroDePaginas());
            ps.setInt(index++, editorialID);
            ps.setString(index++, materiales.getIsbn());
            ps.setString(index++, materiales.getFechaPublicacion());
            ps.setInt(index++, materiales.getUnidadesDisponibles());
            ps.setString(index++, materiales.getUbicacion());

            rows = ps.executeUpdate();
        }catch(SQLException e){
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int insertarCV(Materiales materiales){ 
        
        String sql = "INSERT INTO materiales (id,titulo,codigo_tipo_material,"
                + "numero_de_paginas, ubicacion, nombre_autor_CV,"
                + "fecha_publicacion,unidades_disponibles) VALUES (?,?,6,?,?,?,?,?)";
        Connection conn = null;
        int rows = 0;

        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            int index = 1;
            ps.setString(index++, crearID("CV"));
            ps.setString(index++, materiales.getTitulo());
            ps.setString(index++, materiales.getNumeroDePaginas());
            ps.setString(index++, materiales.getUbicacion());
            ps.setString(index++, materiales.getNombre_autor_CV());
            ps.setString(index++, materiales.getFechaPublicacion());
            ps.setInt(index++, materiales.getUnidadesDisponibles());
            rows = ps.executeUpdate();
        }catch(SQLException e){
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int insertarCD(Materiales materiales){
        String sql = "INSERT INTO materiales (id,titulo, fecha_publicacion, codigo_artista,codigo_tipo_material,codigo_genero,duracion,numero_de_canciones,unidades_disponibles, ubicacion)"
        + "VALUES (?,?,?,1,?,?,?,?,?,?)";
        //PreparedStatement ps = null;
        int rows = 0;
        int idArtista = consultarArtistaPorNombre(materiales.getArtista());
        int idGenero = consultarGeneroPorNombre(materiales.getGenero());

        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            int index = 1;
            ps.setString(index++, crearID("CDA"));
            ps.setString(index++, materiales.getTitulo());
            ps.setString(index++, materiales.getFechaPublicacion());
            ps.setInt(index++, idArtista);
            ps.setInt(index++, idGenero);
            ps.setString(index++, materiales.getDuracion());
            ps.setString(index++, materiales.getNumeroDeCanciones());
            ps.setInt(index++, materiales.getUnidadesDisponibles());
            ps.setString(index++, materiales.getUbicacion());

            rows = ps.executeUpdate();
        }catch(Exception e){
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int insertarDVD(Materiales materiales){
        String sql = "INSERT INTO materiales (id,titulo,codigo_director,codigo_tipo_material,codigo_genero,duracion, u_disponibles, ubicacion)"
                        + "VALUES (?,?,?,2,?,?, ?, ?)";
        int idGenero = consultarGeneroPorNombre(materiales.getGenero());
        int idDirector = consultarDirectorPorNombre(materiales.getDirector());
        //PreparedStatement ps = null;
        //ResultSet rs = null;
        int rows = 0;

        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            int index = 1;
            ps.setString(index++, crearID("DVD"));
            ps.setString(index++, materiales.getTitulo());
            ps.setInt(index++, idDirector);
            ps.setInt(index++, idGenero);
            ps.setString(index, materiales.getDuracion());
            ps.setString(index++, materiales.getFechaPublicacion());
            ps.setInt(index++, materiales.getUnidadesDisponibles());
            ps.setString(index++, materiales.getUbicacion());
            rows = ps.executeUpdate();
        }catch(SQLException e){
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int insertarRevista(Materiales materiales){
        String sql = "INSERT INTO materiales (id,titulo,codigo_editorial,codigo_tipo_material,periodicidad,fecha_publicacion,unidades_disponibles, ubicacion)"
                 + "VALUES (?,?,?,3,?,?,?,?)";
        int idEditorial = consultarEditorialPorNombre(materiales.getEditorial());
         int rows = 0;

         try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            int index = 1;
            ps.setString(index++, crearID("REV"));
            ps.setString(index++, materiales.getTitulo());
            ps.setInt(index++, idEditorial);
            ps.setString(index++, materiales.getPeriodicidad());
            ps.setString(index++, materiales.getFechaPublicacion());
            ps.setInt(index++, materiales.getUnidadesDisponibles());
            ps.setString(index++, materiales.getUbicacion());
            rows = ps.executeUpdate();
         }catch(SQLException e){
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
         } finally{
             Conexion.close(ps);
             Conexion.close(conn);
         } 
        return rows;
    }
    
    public String crearID(String tipoMaterial){
        //Estructura del ID: DVD00001
        String sql = "SELECT id FROM materiales";
        //PreparedStatement ps = null;
        int[] arrayID = new int[8];
        int i = 0;
        String id = "";
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            //vamos convirtiendo cada ID en int quitando las tres primera letras
            //y depositando el numero en un array
            while (rs.next()) {                
                String nombreAutor = rs.getString("id");                
                String tipoID = nombreAutor.length() < 2 ? nombreAutor : nombreAutor.substring(0, 3);
                if(tipoID.equalsIgnoreCase(tipoMaterial)){
                    int numberId = Integer.parseInt(nombreAutor.substring(3));
                    arrayID[i] = numberId;
                    i++;
                }
            }
            //voy identificando el numero mayor
            int numMayor = arrayID[0];
            for (int j = 0; j < arrayID.length; j++){
                if (arrayID[j] > numMayor) {
                    numMayor = arrayID[j];
                }
            }
            //vamos a ver cuantos digitos tiene el numero resultante
            int conteo = (int)Math.floor(Math.log10(numMayor) + 1);
                                    
            //agregamos los ceros según la cantidad de digitos del numero mayor
            int resultado = 0;
            if(conteo <= 1){
                resultado = numMayor+1;
                id = String.valueOf(tipoMaterial+"0000"+resultado);
            } else if(conteo == 2) {
                resultado = numMayor+1;
                id = String.valueOf(tipoMaterial+"000"+resultado);
            } else if(conteo == 3) {
                resultado = numMayor+1;
                id = String.valueOf(tipoMaterial+"00"+resultado);
            } else if(conteo == 4) {
                resultado = numMayor+1;
                id = String.valueOf(tipoMaterial+"0"+resultado);
            } else {
                resultado = numMayor+1;
                id = String.valueOf(tipoMaterial+resultado);
            }
        } catch (Exception e) {
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return id;
    }
    
    public int consultarAutorPorNombre(String autor){
        String sql = "SELECT id, nombre_autor FROM AUTORES WHERE nombre_autor = '"+autor+"';";
        //PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                String nombreAutor = rs.getString("nombre_autor");
                id = rs.getInt("id");
            }
            
        } catch (Exception e) {
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return id;
    }
    
    public int consultarEditorialPorNombre(String editorial){
        String sql = "SELECT id, nombre_editorial FROM EDITORIALES WHERE nombre_editorial = '"+editorial+"';";
        //PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                String nombreAutor = rs.getString("nombre_editorial");
                id = rs.getInt("id");
            }
            
        } catch (Exception e) {
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return id;
    }
    
    public int consultarArtistaPorNombre(String artista){
        String sql = "SELECT id, nombre_artista FROM ARTISTAS WHERE nombre_artista = '"+artista+"';";
        //PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                String nombreArtista = rs.getString("nombre_artista");
                id = rs.getInt("id");
            }
            
        } catch (Exception e) {
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return id;
    }
    
    public int consultarGeneroPorNombre(String genero){
        String sql = "SELECT id, nombre_genero FROM GENEROS WHERE nombre_genero = '"+genero+"';";
        //PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                String nombreGenero = rs.getString("nombre_genero");
                id = rs.getInt("id");
            }
            
        } catch (Exception e) {
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return id;
    }
    
    public int consultarDirectorPorNombre(String director){
        String sql = "SELECT id, nombre_director FROM DIRECTORES WHERE nombre_director = '"+director+"';";
        //PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                String nombreDirector = rs.getString("nombre_director");
                id = rs.getInt("id");
            }
            
        } catch (Exception e) {
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return id;
    }
    
    public int consultarTipoMaterialNombre(String tipo){
        String sql = "SELECT id, tipo_material FROM tipo_material WHERE tipo_material = '"+tipo+"';";
        //PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                String nombreDirector = rs.getString("tipo_material");
                id = rs.getInt("id");
            }
            
        } catch (Exception e) {
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return id;
    }
    /////////////////////////////////////////////
    
    public boolean materialExiste(String tituloMaterial) throws SQLException{
        try {
            int rows = 0;
            //Codigo SQL para insertar registro a tabla
            String sql = "SELECT * FROM materiales WHERE titulo = '"+ tituloMaterial +"'";

            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
            return true;
            }
            
        } catch (SQLException e){
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }
        
        return false;
    }
    
    public Materiales ver(String id) {
        try{
            String sql = "SELECT "
                +"materiales.id, materiales.titulo,autores.nombre_autor AS Autor, "
                +"materiales.numero_de_paginas, editoriales.nombre_editorial AS "
                +"Editorial, materiales.isbn, materiales.periodicidad, "
                +"date_format(materiales.fecha_publicacion, \"%d/%m/%Y\"), "
                +"AS fecha, materiales.ubicacion, materiales.nombre_autor_cv, "
                +"artistas.nombre_artista AS Artista, materiales.duracion, "
                +"materiales.numero_de_canciones,materiales.unidades_disponibles, "
                +"directores.nombre_director as Director, materiales.duracion, "
                +"generos.nombre_genero AS Genero,tipo_material.tipo_material "
                +"AS Tipo from materiales LEFT JOIN directores on "
                +"directores.id = materiales.codigo_director LEFT JOIN generos "
                +"ON generos.id = materiales.codigo_genero LEFT JOIN "
                +"tipo_material ON tipo_material.id = "
                +"materiales.codigo_tipo_material LEFT JOIN "
                +"autores on autores.id = materiales.codigo_autor LEFT JOIN "
                +"artistas ON artistas.id = materiales.codigo_artista LEFT JOIN "
                +"editoriales ON editoriales.id = materiales.codigo_editorial  "
                + "WHERE materiales.id ='"+ id +"'";

            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            Materiales materiales = new Materiales();
            
            if(rs.next()) {
               /* rol.setId(rs.getInt("id"));
                rol.setRol(rs.getString("rol"));
                rol.setNumero_prestamos(rs.getInt("numero_prestamos"));
                rol.setDias_prestamo(rs.getInt("dias_prestamo"));*/
               
                materiales.setId(rs.getString("id"));
                materiales.setTitulo(rs.getString("titulo"));
                materiales.setTipoMaterial(rs.getString("tipo_material"));
                materiales.setAutor(rs.getString("Autor"));
                materiales.setNumeroDePaginas(rs.getString("numero_de_paginas"));
                materiales.setEditorial(rs.getString("editoriales"));
                materiales.setIsbn(rs.getString("isbn"));
                materiales.setPeriodicidad(rs.getString("periodicidad"));
                materiales.setFechaPublicacion(rs.getString("fecha"));
                materiales.setArtista(rs.getString("artistas"));
                materiales.setGenero(rs.getString("generos"));
                materiales.setDuracion(rs.getString("duracion"));
                materiales.setNumeroDeCanciones(rs.getString("numero_de_canciones"));
                materiales.setDirector(rs.getString("directores"));
                materiales.setUbicacion(rs.getString("ubicacion"));
                materiales.setNombre_autor_CV(rs.getString("nombre_autor_cv"));
                materiales.setUnidadesDisponibles(rs.getInt("unidades_disponibles"));
            }
            
            return materiales;
            
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }
    }
    
    /**
     * 
     * Metodo para hacer una consulta sin tener que cargar los registros de 
     * materiales de una sola vez, sino al hacer una busqueda individual por
     * titulo
     * 
     **/
    public Materiales consulta(String titulo) {
        try{
            String sql = "SELECT "
                +"materiales.id, materiales.titulo,autores.nombre_autor AS Autor, "
                +"materiales.numero_de_paginas, editoriales.nombre_editorial AS "
                +"Editorial, materiales.isbn, materiales.periodicidad, "
                +"date_format(materiales.fecha_publicacion, \"%d/%m/%Y\"), "
                +"AS fecha, materiales.ubicacion, materiales.nombre_autor_cv, "
                +"artistas.nombre_artista AS Artista, materiales.duracion, "
                +"materiales.numero_de_canciones,materiales.unidades_disponibles, "
                +"directores.nombre_director as Director, materiales.duracion, "
                +"generos.nombre_genero AS Genero,tipo_material.tipo_material "
                +"AS Tipo from materiales LEFT JOIN directores on "
                +"directores.id = materiales.codigo_director LEFT JOIN generos "
                +"ON generos.id = materiales.codigo_genero LEFT JOIN "
                +"tipo_material ON tipo_material.id = "
                +"materiales.codigo_tipo_material LEFT JOIN "
                +"autores on autores.id = materiales.codigo_autor LEFT JOIN "
                +"artistas ON artistas.id = materiales.codigo_artista LEFT JOIN "
                +"editoriales ON editoriales.id = materiales.codigo_editorial  "
                + "WHERE materiales.titulo like '%"+ titulo +"%'";

            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            Materiales materiales = new Materiales();
            
            if(rs.next()) {
               /* rol.setId(rs.getInt("id"));
                rol.setRol(rs.getString("rol"));
                rol.setNumero_prestamos(rs.getInt("numero_prestamos"));
                rol.setDias_prestamo(rs.getInt("dias_prestamo"));*/
               
                materiales.setId(rs.getString("id"));
                materiales.setTitulo(rs.getString("titulo"));
                materiales.setTipoMaterial(rs.getString("tipo_material"));
                materiales.setAutor(rs.getString("Autor"));
                materiales.setNumeroDePaginas(rs.getString("numero_de_paginas"));
                materiales.setEditorial(rs.getString("editoriales"));
                materiales.setIsbn(rs.getString("isbn"));
                materiales.setPeriodicidad(rs.getString("periodicidad"));
                materiales.setFechaPublicacion(rs.getString("fecha"));
                materiales.setArtista(rs.getString("artistas"));
                materiales.setGenero(rs.getString("generos"));
                materiales.setDuracion(rs.getString("duracion"));
                materiales.setNumeroDeCanciones(rs.getString("numero_de_canciones"));
                materiales.setDirector(rs.getString("directores"));
                materiales.setUbicacion(rs.getString("ubicacion"));
                materiales.setNombre_autor_CV(rs.getString("nombre_autor_cv"));
                materiales.setUnidadesDisponibles(rs.getInt("unidades_disponibles"));
            }
            
            return materiales;
            
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }
    }
    
    /*****/

    public int actualizar(Materiales material) throws SQLException{
        try {
            rows = 0;
                //Codigo SQL para insertar registro a tabla
            String sql = //"UPDATE materiales SET rol = ?, numero_prestamos = ?, dias_prestamo = ? WHERE id ='" + materiales.getId() + "'";
                "UPDATE materiales SET titulo=?, codigo_tipo_material=?, "
                + "codigo_autor=?, numero_de_paginas=?, codigo_editorial=?, isbn=?, "
                + "periodicidad=?, fecha_publicacion=?, codigo_artista=?, "
                + "codigo_genero=?, duracion=?, numero_de_canciones=?, "
                + "codigo_director=?, ubicacion=?, nombre_autor_cv=?, "
                + "unidades_disponibles=? WHERE id ='" +material.getId()+ "'";
        
            int idGenero = consultarGeneroPorNombre(material.getGenero());
            int idDirector = consultarDirectorPorNombre(material.getDirector());
            int editorialID = consultarEditorialPorNombre(material.getEditorial());
            int tipoMaterialID = consultarTipoMaterialNombre(material.getTipoMaterial());
            int autorID = consultarAutorPorNombre(material.getAutor());
            int idArtista = consultarArtistaPorNombre(material.getArtista());
            
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            
            /*ps.setString(1, material.getTitulo());
            ps.setInt(2, );
            ps.setInt(3, rol.getDias_prestamo());*/
            
            ps.setString(1, material.getTitulo());
            ps.setInt(2, tipoMaterialID);
            ps.setInt(3, autorID);
            ps.setString(4, material.getNumeroDePaginas());
            ps.setInt(5, editorialID);
            ps.setString(6, material.getIsbn());
            ps.setString(7, material.getPeriodicidad());
            ps.setString(8, material.getFechaPublicacion());
            ps.setInt(9, idArtista);
            ps.setInt(10, idGenero);
            ps.setString(11, material.getDuracion());
            ps.setString(12, material.getNumeroDeCanciones());
            ps.setInt(13, idDirector);
            ps.setString(14, material.getUbicacion());
            ps.setString(15, material.getNombre_autor_CV());
            ps.setInt(16, material.getUnidadesDisponibles());
            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException e){
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        } 
    }
    
    public int eliminar(String id) {
        try {
            int rows = 0;
            //Codigo SQL para insertar borrar registro
            String sql = "DELETE FROM materiales WHERE id ='"+ id +"'";

            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);

            rows = ps.executeUpdate();        
            return rows;
            
        } catch (SQLException e){
            Logger.getLogger(MaterialesDAO.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        } 
    }
}
