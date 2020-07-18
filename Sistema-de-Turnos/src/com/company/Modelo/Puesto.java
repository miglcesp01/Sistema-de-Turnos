package com.company.Modelo;

public class Puesto {
    private Paciente paciente;
    private Doctor doctor;
    private int numero;
    private boolean disponibilidad;

    public Puesto(Doctor doctor,int numero){
        this.doctor=doctor; 
        this.numero=numero;
        disponibilidad=true;
    }
    public Paciente getPaciente(){
        return paciente;
    }
    public Doctor getDoctor(){
        return doctor;
    }
    public int getNumero(){
        return numero;
    }
    
    public void setPaciente(Paciente paciente){
        this.paciente=paciente;
    }
    
    public void setDisponibilidad(boolean dis){
        this.disponibilidad=dis;
    }
    
    public boolean getDisponibilida(){
        return disponibilidad;
    }

}
