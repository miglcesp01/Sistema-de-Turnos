package com.company.Modelo;

import java.util.Objects;

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
        disponibilidad = false;
    }
    
    public void setDisponibilidad(boolean dis){
        this.disponibilidad=dis;
    }
    
    public boolean getDisponibilida(){
        return disponibilidad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Puesto other = (Puesto) obj;
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        return true;
    }
    
    public boolean isDisponible(){
        return disponibilidad;
    }

    @Override
    public String toString() {
        if(paciente==null) return"Puesto{" + "paciente= Ninguno" + ", doctor=" + doctor + ", numero=" + numero + ", disponibilidad=" + disponibilidad + '}';
        return "Puesto{" + "paciente=" + paciente.getNombre() + ", doctor=" + doctor + ", numero=" + numero + ", disponibilidad=" + disponibilidad + '}';
    }

}
