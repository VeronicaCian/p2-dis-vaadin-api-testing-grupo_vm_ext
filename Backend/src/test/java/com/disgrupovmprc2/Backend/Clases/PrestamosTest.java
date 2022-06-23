package com.disgrupovmprc2.Backend.Clases;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrestamosTest {

    @Test
    public void getFecha_Inicio_Prestamo() {
        Prestamos prestamos = new Prestamos(
                2,
                1,
                3,
                "24/10/2020",
                "24/12/2020",
                "23/12/2020",
                "Entregado en perfecto estado"
        );
        assertFalse("ERROR, Fecha Inicio Prestamo vacio", prestamos.getFecha_Inicio_Prestamo().isEmpty());
    }

    @Test
    public void getFecha_Fin_Prestamo() {
        Prestamos prestamos = new Prestamos(
                2,
                1,
                3,
                "24/10/2020",
                "24/12/2020",
                "23/12/2020",
                "Entregado en perfecto estado"
        );
        assertFalse("ERROR, Fecha Fin Prestamo vacio", prestamos.getFecha_Fin_Prestamo().isEmpty());
    }

    @Test
    public void getFecha_Real_Dev() {
        Prestamos prestamos = new Prestamos(
                2,
                1,
                3,
                "24/10/2020",
                "24/12/2020",
                "23/12/2020",
                "Entregado en perfecto estado"
        );
        assertFalse("ERROR, Fecha Real de Devolucion está vacio", prestamos.getFecha_Real_Dev().isEmpty());
    }
}