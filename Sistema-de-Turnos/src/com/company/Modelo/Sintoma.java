package com.company.Modelo;

import java.util.Objects;

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
        final Sintoma other = (Sintoma) obj;
        if (!Objects.equals(this.sintoma, other.sintoma)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return sintoma;
    }
}
