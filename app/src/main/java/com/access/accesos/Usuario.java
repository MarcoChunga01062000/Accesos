package com.access.accesos;

public class Usuario {
    private Integer _id;
    private String nombres;
    private String user;
    private String clave;
    private Integer idrol;

    public Usuario() {
    }

    public Usuario(Integer _id, String nombres, String user, String clave, Integer idrol) {
        this._id = _id;
        this.nombres = nombres;
        this.user = user;
        this.clave = clave;
        this.idrol = idrol;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getIdrol() { return idrol; }

    public void setIdrol(Integer idrol) { this.idrol = idrol; }
}
