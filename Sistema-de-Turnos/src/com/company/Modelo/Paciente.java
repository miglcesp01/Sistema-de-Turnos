package com.company.Modelo;

public class Paciente extends Persona {
    private int edad;
    private char genero;
    private Sintoma sintoma;
    private int turno;
    private String receta;


    public Paciente(int edad,char genero,Sintoma sintoma){
        this.edad=edad;
        this.genero=genero;
        this.sintoma=sintoma;
        turno=0;
        receta=null;
    }

    public int getEdad(){
        return edad;
    }
    public char getGenero(){
        return genero;
    }
    public Sintoma getSintoma(){
        return sintoma;
    }
    public void setEdad(int edad){
        this.edad=edad;
    }
    public void setGenero(char genero){
        this.genero=genero;
    }
    public void setSintoma(Sintoma sintoma){
        this.sintoma=sintoma;
    }
    
    public void setTurno(int turno){
        this.turno=turno;
    }
    
    public int getTurno(){
        return this.turno;
    }
    
    public void setReceta(String receta){
        this.receta=receta;
    }
    
    public String getReceta(){
        return receta;
    }
}
