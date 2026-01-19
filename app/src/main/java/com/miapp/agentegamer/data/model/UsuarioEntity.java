package com.miapp.agentegamer.data.model;

public class UsuarioEntity {

    private String uid;
    private String email;
    private double presupuesto;

    // Constructor vacio necesario para Firebase
    public UsuarioEntity(){}

    public UsuarioEntity(String uid, String email, double presupuesto) {
        this.uid = uid;
        this.email = email;
        this.presupuesto = presupuesto;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }
}
