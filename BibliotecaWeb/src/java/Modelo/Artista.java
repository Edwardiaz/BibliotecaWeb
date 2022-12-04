package Modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Eduardo Trujillo
 */
public class Artista {

    //Atributos
    public int id;
    public String nombre_artista;

    public Artista() {
    }

    public Artista(String nombre_artista) {
        this.nombre_artista = nombre_artista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_artista() {
        return nombre_artista;
    }

    public void setNombre_artista(String nombre_artista) {
        this.nombre_artista = nombre_artista;
    }
    
    
}
