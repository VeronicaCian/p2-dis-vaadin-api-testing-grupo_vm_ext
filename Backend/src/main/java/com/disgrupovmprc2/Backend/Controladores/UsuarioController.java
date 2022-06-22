package com.disgrupovmprc2.Backend.Controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.disgrupovmprc2.Backend.Clases.Usuarios;
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

public class UsuarioController {

    // Clase Controlador para los Usuarios

    Utils utils = new Utils();
    public ArrayList<Usuarios> usuarios;

    //Controlador para mostrar los marcadores

    @GetMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Usuarios> cargarUsuarios(){

        usuarios = utils.lecturaJSONUsuarios();

        return usuarios;

    }
    
}
