package com.disgrupovmprc2.Backend;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.disgrupovmprc2.Backend.Clases.Equipos;
import com.disgrupovmprc2.Backend.Clases.Prestamos;
import com.disgrupovmprc2.Backend.Clases.Usuarios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
public class Utils {

    //lectrua de json Usuarios
    public ArrayList<Usuarios> lecturaJSONUsuarios() {

        //nos creamos un arraylist para ir añadiendo cada uno de los elementos del json
        ArrayList<Usuarios> arrayusers;
        BufferedReader br = null;

        Gson gson = new Gson();

        try {
            br = new BufferedReader(new FileReader("Usuarios.json"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        arrayusers = gson.fromJson(br, new TypeToken<ArrayList<Usuarios>>() {
        }.getType());

        return arrayusers;
    }
    //lectrua de json Usuarios
    public ArrayList<Prestamos> lecturaJSONPrestamos() {

        //nos creamos un arraylist para ir añadiendo cada uno de los elementos del json
        ArrayList<Prestamos> arrayprestamos;
        BufferedReader br = null;

        Gson gson = new Gson();

        try {
            br = new BufferedReader(new FileReader("Prestamos.json"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        arrayprestamos = gson.fromJson(br, new TypeToken<ArrayList<Prestamos>>() {
        }.getType());

        return arrayprestamos;
    }
    //lectrua de json Usuarios
    public ArrayList<Equipos> lecturaJSONEquipos() {

        //nos creamos un arraylist para ir añadiendo cada uno de los elementos del json
        ArrayList<Equipos> arrayequipos;
        BufferedReader br = null;

        Gson gson = new Gson();

        try {
            br = new BufferedReader(new FileReader("Equipos.json"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        arrayequipos = gson.fromJson(br, new TypeToken<ArrayList<Equipos>>() {
        }.getType());

        return arrayequipos;
    }

    }
