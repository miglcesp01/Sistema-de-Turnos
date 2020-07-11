package com.company.Modelo;

import java.util.List;
import java.util.PriorityQueue;

public class Sistema {
    private List<Doctor> doctores;
    private PriorityQueue<Paciente> pacientes;
    private List<Puesto> puestos;

    public Sistema(List<Doctor> doctores, PriorityQueue<Paciente> pacientes, List<Puesto> puestos) {
        this.doctores = doctores;
        this.pacientes = pacientes;
        this.puestos = puestos;
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
    public boolean eliminarPuesto(){
        return false;
    }
    public void cargarDatos(){

    }

}
