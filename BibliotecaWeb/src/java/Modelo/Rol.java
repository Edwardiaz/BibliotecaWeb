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
public class Rol {

    //Atributos
    public int id;
    public String rol;
    public int numero_prestamos;
    public int dias_prestamo;

    public Rol() {
    }

    public Rol(String rol, int numero_prestamos, int dias_prestamo) {
        this.rol = rol;
        this.numero_prestamos = numero_prestamos;
        this.dias_prestamo = dias_prestamo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getNumero_prestamos() {
        return numero_prestamos;
    }

    public void setNumero_prestamos(int numero_prestamos) {
        this.numero_prestamos = numero_prestamos;
    }

    public int getDias_prestamo() {
        return dias_prestamo;
    }

    public void setDias_prestamo(int dias_prestamo) {
        this.dias_prestamo = dias_prestamo;
    }    
}
