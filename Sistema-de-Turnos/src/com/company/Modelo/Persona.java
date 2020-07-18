/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Modelo;

/**
 *
 * @author Alexis
 */
public abstract class Persona {
    protected String nombre;
    
    public Persona(String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    
}
