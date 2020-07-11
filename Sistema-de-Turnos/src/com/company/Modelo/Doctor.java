package com.company.Modelo;

import java.util.Objects;

public class Doctor extends Persona {

    private String identificacion;
    private String especialidad;
    private boolean disponibilidad;
    //private Paciente paciente;

    public Doctor(String especialidad,String identificacion){
        this.especialidad=especialidad;
        this.disponibilidad=true;
        this.identificacion=identificacion;
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
    
    public String getIdentificacion(){
        return identificacion;
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
        final Doctor other = (Doctor) obj;
        if (!Objects.equals(this.identificacion, other.identificacion)) {
            return false;
        }
        return true;
    }
    
}
