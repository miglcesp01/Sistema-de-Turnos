package com.company.Modelo;

public class Doctor extends Persona {

    private String especialidad;
    private boolean disponibilidad;
    //private Paciente paciente;

    public Doctor(String especialidad,boolean disponibilidad){
        this.especialidad=especialidad;
        this.disponibilidad=disponibilidad;
    }

    public String getEspecialidad(){
        return  especialidad;
    }

    public void setEspecialidad(String especialidad){
        this.especialidad=especialidad;
    }

    public boolean getDisponibilidad(){
        return disponibilidad;
    }

    public void setDisponibilidad(boolean b){
        disponibilidad=b;
    }

    public boolean atenderPaciente(Paciente paciente){
        return false;
    }
}
