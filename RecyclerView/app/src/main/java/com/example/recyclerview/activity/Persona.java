package com.example.recyclerview.activity;

import java.io.Serializable;

public class Persona implements Serializable {
    private String nombre;
    private String edad;
    private int foto;

    public Persona(String nombre, String edad, int foto) {
        this.nombre = nombre;
        this.edad = edad;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEdad() {
        return edad;
    }

    public int getFoto() {
        return foto;
    }
}
