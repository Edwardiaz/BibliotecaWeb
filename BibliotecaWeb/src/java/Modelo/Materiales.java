package Modelo;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Jorge Díaz
 */
public class Materiales {
    
    private String id;
    private String titulo;
    private String numeroDePaginas;
    private String isbn;
    private String periodicidad;
    private String fechaPublicacion;
    private String duracion;
    private String numeroDeCanciones;
    private Integer unidadesDisponibles;
    private String artista;
    private String autor;
    private String director;
    private String ubicacion;
    private String nombre_autor_CV;
    private String editorial;
    private String genero;
    private String tipoMaterial;
    private String Estado;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoMaterial")
    private List<Prestamos> prestamosList;

    public Materiales() {
    }

    public Materiales(String id) {
        this.id = id;
    }

    public Materiales(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }
    
    public Materiales(String id, String titulo, String numeroDePaginas, String isbn, String periodicidad, String fechaPublicacion, String duracion, String numeroDeCanciones, Integer unidadesDisponibles, String codigoArtista, String codigoAutor, String codigoDirector, String ubicacion, String nombre_autor_CV, String codigoEditorial, String codigoGenero, String codigoTipoMaterial, String codigoEstado) {
        this.id = id;
        this.titulo = titulo;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.periodicidad = periodicidad;
        this.fechaPublicacion = fechaPublicacion;
        this.duracion = duracion;
        this.numeroDeCanciones = numeroDeCanciones;
        this.unidadesDisponibles = unidadesDisponibles;
        this.artista = codigoArtista;
        this.autor = codigoAutor;
        this.director = codigoDirector;
        this.ubicacion = ubicacion;
        this.nombre_autor_CV = nombre_autor_CV;
        this.editorial = codigoEditorial;
        this.genero = codigoGenero;
        this.tipoMaterial = codigoTipoMaterial;
        this.Estado = codigoEstado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(String numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getNumeroDeCanciones() {
        return numeroDeCanciones;
    }

    public void setNumeroDeCanciones(String numeroDeCanciones) {
        this.numeroDeCanciones = numeroDeCanciones;
    }

    public Integer getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(Integer unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombre_autor_CV() {
        return nombre_autor_CV;
    }

    public void setNombre_autor_CV(String nombre_autor_CV) {
        this.nombre_autor_CV = nombre_autor_CV;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }
    
        public String getEstado() {
        return Estado;
    }
    
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public List<Prestamos> getPrestamosList() {
        return prestamosList;
    }

    public void setPrestamosList(List<Prestamos> prestamosList) {
        this.prestamosList = prestamosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materiales)) {
            return false;
        }
        Materiales other = (Materiales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Materiales[ id=" + id + " ]";
    }
    
}
