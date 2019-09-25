package com.access.accesos;

public class Rol {

    private Integer idr;
    private String nombre;

    public Rol(){
    }

    public Rol(Integer idr, String nombre){
        this.idr = idr;
        this.nombre = nombre;
    }

    public Integer getIdr() {
        return idr;
    }

    public void setIdr(Integer idr) {
        this.idr = idr;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
