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
public class Usuario {

    //Atributos
    public int id;
    public String nombre;
    public String apellido;
    public String nickname;
    public String email;
    public String pass;
    public Float mora;
    public String fecha_nacimiento;
    public int codigo_rol;
    public String rol;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String nickname, String email, String pass, Float mora, String fecha_nacimiento, int codigo_rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.email = email;
        this.pass = pass;
        this.mora = mora;
        this.fecha_nacimiento = fecha_nacimiento;
        this.codigo_rol = codigo_rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Float getMora() {
        return mora;
    }

    public void setMora(Float mora) {
        this.mora = mora;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getCodigo_rol() {
        return codigo_rol;
    }

    public void setCodigo_rol(int codigo_rol) {
        this.codigo_rol = codigo_rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }    
}
