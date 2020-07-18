package com.company.Modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import recursos.archivos.LecturaArchivos;

public class Sistema {
    public static Sistema sistema=new Sistema();
    private List<Doctor> doctores;
    private PriorityQueue<Paciente> pacientes;
    private List<Puesto> puestos;
    private List<Sintoma> sintomas;
    private int turnos=6;

    private Sistema() {
        this.doctores = LecturaArchivos.leerArchivoDoctor();
        this.pacientes = new PriorityQueue<>((Paciente p1,Paciente p2)-> p2.getSintoma().getPrioridad()-p1.getSintoma().getPrioridad());
        pacientes.addAll(LecturaArchivos.leerArchivoPaciente());
        this.puestos = new LinkedList<>();
        this.sintomas=LecturaArchivos.leerArchivoSintomas();
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

    public List<Sintoma> getSintomas(){
        return sintomas;
    }
    
    public boolean agregarDoctor(Doctor doctor){
        if(doctores.contains(doctor) || doctor==null) return false;
        doctores.add(doctor);
        return true;
    }
    
    public boolean generarPuesto(){
        return false;
    }
    
    public void agregarPaciente(Paciente p){
        if(p==null) throw new NullPointerException("Ingreso un paciente vacio");
        pacientes.offer(p);
    }
    public boolean eliminarPuesto(Puesto p){
        if(p!=null || puestos.contains(p)) puestos.remove(p);
        return false;
    }

    public int generarTurno(){
        return turnos++;
    }
    
    public Doctor buscarDoctorDisponible(){
        for(Doctor d: doctores){
            if(d.getDisponibilidad()) return d;
        }return null;
    }
}
