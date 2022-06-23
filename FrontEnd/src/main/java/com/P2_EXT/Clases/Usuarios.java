package com.P2_EXT.Clases;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Usuarios {


    //atributos
    @JsonProperty("id")
    private int id;

    @JsonProperty("Nombre")
    private String Nombre;

    @JsonProperty("Departamento")
    private String Departamento;

    @JsonProperty("Ubicacion")
    private String Ubicacion;

    @JsonProperty("Telefono de contacto")
    private int telefono;

    @JsonProperty("Correo electronico")
    private String email;

    private Usuarios(){

    }


    public Usuarios(int id,String Nombre, String Departamento, String Ubicacion, int telefono, String email) {
        this.id = id;
        this.email = email;
        this.Departamento = Departamento;
        this.Nombre = Nombre;
        this.telefono = telefono;
        this.Ubicacion = Ubicacion;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String departamento) {
        Departamento = departamento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
