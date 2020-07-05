package com.company.Modelo;

public class Puesto {
    private Paciente paciente;
    private Doctor doctor;
    private String turno;
    private int numero;


    public Puesto(Paciente paciente,Doctor doctor,String turno,int numero){
        this.paciente=paciente;
        this.doctor=doctor;
        this.turno=turno;
        this.numero=numero;
    }
    public Paciente getPaciente(){
        return paciente;
    }
    public Doctor getDoctor(){
        return doctor;
    }
    public String getTurno(){
        return turno;
    }
    public int getNumero(){
        return numero;
    }

}
