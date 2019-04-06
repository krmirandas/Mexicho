/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuukul.proyecto.web;

/**
 *
 * @author coronado
 */
public class Tema {
    private int id_tema;
    private String titulo_tema;
    private String descripcion_tema;
    private String color_tema;
    private int id_usuario;
    
    public int getId_tema() {
        return id_tema;
    }

    public void setId_tema(int id_tema) {
        this.id_tema = id_tema;
    }

    public String getTitulo_tema() {
        return titulo_tema;
    }

    public void setTitulo_tema(String titulo_tema) {
        this.titulo_tema = titulo_tema;
    }

    public String getDescripcion_tema() {
        return descripcion_tema;
    }

    public void setDescripcion_tema(String descripcion_tema) {
        this.descripcion_tema = descripcion_tema;
    }

    public String getColor_tema() {
        return color_tema;
    }

    public void setColor_tema(String color_tema) {
        this.color_tema = color_tema;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }


}
