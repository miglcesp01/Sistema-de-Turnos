package com.company.Modelo;

public class Paciente extends Persona {
    private int edad;
    private char genero;
    private Sintoma sintoma;


    public Paciente(int edad,char genero,Sintoma sintoma){
        this.edad=edad;
        this.genero=genero;
        this.sintoma=sintoma;
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


}
