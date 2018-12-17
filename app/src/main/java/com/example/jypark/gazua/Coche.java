package com.example.jypark.gazua;

public class Coche {

    private int img;
    private String nombre;
    private String city;

    public Coche(int img, String nombre, String city) {
        this.img = img;
        this.nombre = nombre;
        this.city = city;
    }

    public int getImg() {
        return img;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCity() {
        return city;
    }
}
