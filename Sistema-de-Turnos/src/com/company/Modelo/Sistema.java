package com.company.Modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import recursos.archivos.LecturaArchivos;

public class Sistema {
    private List<Doctor> doctores;
    private PriorityQueue<Paciente> pacientes;
    private List<Puesto> puestos;
    private List<Sintoma> sintomas;

    public Sistema() {
        this.doctores = LecturaArchivos.leerArchivoDoctor();
        this.pacientes = new PriorityQueue<>();
        pacientes.addAll(LecturaArchivos.leerArchivoPaciente());
        this.puestos = new LinkedList<>();
    }

    public List<Doctor> getDoctores() {
        return doctores;
    }
    public void setDoctores(List<Doctor> doctores) {
        this.doctores = doctores;
    }
    public PriorityQueue<Paciente> getPacientes() {
        return pacientes;
    }
    public void setPacientes(PriorityQueue<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    public List<Puesto> getPuestos() {
        return puestos;
    }
    public void setPuestos(List<Puesto> puestos) {
        this.puestos = puestos;
    }

    public boolean agregarDoctor(Doctor doctor){
        if(doctores.contains(doctor) || doctor==null) return false;
        doctores.add(doctor);
        return true;
    }
    
    public boolean generarPuesto(){
        return false;
    }
    public boolean eliminarPuesto(Puesto p){
        if(p!=null || puestos.contains(p)) puestos.remove(p);
        return false;
    }

}
