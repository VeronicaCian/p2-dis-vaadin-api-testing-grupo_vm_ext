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

    @PutMapping(path = "/usuario",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity modifyUsuario (@RequestBody Usuarios usuariomodificado) throws IOException {


        ArrayList<Usuarios> listamodified = utils.lecturaJSONUsuarios();


        int id = usuariomodificado.getId();

        for (Usuarios user : listamodified) {
            if(user.getId()==id){
                user.setNombre(usuariomodificado.getNombre());
                user.setDepartamento(usuariomodificado.getDepartamento());
                user.setUbicacion(usuariomodificado.getUbicacion());
                user.setTelefono(usuariomodificado.getTelefono());
                user.setEmail(usuariomodificado.getEmail());
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create(); //hacemeos que aparezca bonito en vez de en una linea
        FileWriter writer = null;                                   //inicializamos el filewriter
        String archivo = "Usuarios.json";

        try{
            writer = new FileWriter(archivo);
        }catch (IOException e){
            e.printStackTrace();
        }

        gson.toJson(listamodified,writer);

        try {
            writer.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

}
