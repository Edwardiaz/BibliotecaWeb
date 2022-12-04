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
public class Director {

    //Atributos
    public int id;
    public String nombre_director;

    public Director() {
    }

    public Director(String nombre_director) {
        this.nombre_director = nombre_director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_director() {
        return nombre_director;
    }

    public void setNombre_director(String nombre_director) {
        this.nombre_director = nombre_director;
    }
    
    
}
