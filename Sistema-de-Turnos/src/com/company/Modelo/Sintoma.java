package com.company.Modelo;

public class Sintoma {
    private String sintoma;
    private int prioridad;

    public Sintoma(String sintoma,int prioridad){
        this.sintoma=sintoma;
        this.prioridad=prioridad;
    }
    public String getSintoma(){
        return sintoma;
    }
    public int getPrioridad(){
        return prioridad;
    }
    public void setSintoma(String sintoma){
        this.sintoma=sintoma;
    }
    public void setPrioridad(int prioridad){
        this.prioridad=prioridad;
    }

}
