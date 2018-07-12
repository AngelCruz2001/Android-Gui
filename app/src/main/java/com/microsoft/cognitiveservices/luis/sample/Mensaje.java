package com.microsoft.cognitiveservices.luis.sample;


public class Mensaje {
    private String Nombre,Mensaje;

    public Mensaje() {

    }

    public Mensaje(String nombre, String mensaje) {
        Nombre = nombre;
        Mensaje = mensaje;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }
}
