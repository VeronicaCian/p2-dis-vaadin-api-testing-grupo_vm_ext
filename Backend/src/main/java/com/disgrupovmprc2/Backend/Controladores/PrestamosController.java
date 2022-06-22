package com.disgrupovmprc2.Backend.Controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.disgrupovmprc2.Backend.Clases.Prestamos;
import com.disgrupovmprc2.Backend.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api")

public class PrestamosController {

    // Clase Controlador para los Prestamos

    Utils utils = new Utils();
    public ArrayList<Prestamos> prestamos;

    //Controlador para mostrar los prestamos

    @GetMapping(value = "/prestamos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Prestamos> cargarPrestamos(){

        prestamos = utils.lecturaJSONPrestamos();

        return prestamos;
    }

}
