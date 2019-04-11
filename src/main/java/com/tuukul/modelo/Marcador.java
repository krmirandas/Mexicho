/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuukul.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog = "mexicho", schema = "marcadores", name = "marcador")


public class Marcador implements Serializable {
    @Id
    @Column(name = "id_marcador")
    private int id_marcador;
     //Hay que cambiar a String aquí y en la base de datos
    @Column(name = "titulo_tema")
    private String titulo_tema;
    
    @Column(name = "titulo_marcador")
    private String titulo_marcador;
    
    @Column(name = "descripcion_marcador")
    private String descripcion_marcador;

    @Column(name = "latitud")
    private double latitud;

    @Column(name = "longitud")
    private double longitud;
    

    public int getId_marcador() {
        return id_marcador;
    }

    public void setId_marcador(int id_marcador) {
        this.id_marcador= id_marcador;
    }
    
    public String getTitulo_tema() {
        return titulo_tema;
    }

    public void setTitulo_tema(String titulo_tema) {
        this.titulo_tema = titulo_tema;
    }

    public String getTitulo_marcador() {
        return titulo_marcador;
    }
    

    public void setTitulo_marcador(String titulo_marcador) {
        this.titulo_marcador = titulo_marcador;
    }

    public String getDescripcion_marcador() {
        return descripcion_marcador;
    }

    public void setDescripcion_marcador(String descripcion_marcador) {
        this.descripcion_marcador = descripcion_marcador;
    }
    
    public double getLatitud(){
        return latitud;
    }
    
    public void setLatitud(double latitud){
        this.latitud=latitud;
    }
    
    public double getLongitud(){
        return longitud;
    }
    
    public void setLongitud(double longitud){
        this.longitud=longitud;
    }

    
}
