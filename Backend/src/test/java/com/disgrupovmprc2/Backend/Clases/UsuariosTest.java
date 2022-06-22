package com.disgrupovmprc2.Backend.Clases;

import org.junit.Test;

import static org.junit.Assert.*;

public class UsuariosTest {

    @Test
    public void getEmail() {
        Usuarios usu = new Usuarios(
                5,
                "Pablo",
                "Publicidad",
                "Madrid",
                648039974,
                "pablonf@gmail.com");
        assertTrue(usu.getEmail().contains("@"));
    }

    @Test
    public void getTelefono() {
        Usuarios usu = new Usuarios(
                5,
                "Miguel",
                "Contabilidad",
                "Pozuelo",
                654783985,
                "miguelcont@gmail.com");
        assertTrue(usu.getTelefono()>99999999 && usu.getTelefono()<999999999);
    }

    @Test
    public void getUbicacion() {
        Usuarios usu = new Usuarios(
                5,
                "Miguel",
                "Contabilidad",
                "Pozuelo",
                654783985,
                "miguelcont@gmail.com");

        assertFalse("ERROR, Ubicación vacio", usu.getUbicacion().isEmpty());

    }

    @Test
    public void getDepartamento() {
        Usuarios usu = new Usuarios(
                5,
                "Manuel",
                "Contabilidad",
                "Pozuelo",
                654783985,
                "miguelcont@gmail.com");

        assertFalse("ERROR, Departamento vacio", usu.getDepartamento().isEmpty());
    }
    @Test
    public void getNombre() {
        Usuarios usu = new Usuarios(
                5,
                "Manuel",
                "Contabilidad",
                "Pozuelo",
                654783985,
                "miguelcont@gmail.com");

        assertFalse("ERROR, Nombre vacio", usu.getNombre().isEmpty());
    }

}