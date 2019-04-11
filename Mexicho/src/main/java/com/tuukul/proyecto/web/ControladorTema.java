/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuukul.proyecto.web;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.tuukul.modelo.Tema;
import com.tuukul.modelo.UtilidadTema;
/**
 *
 * @author coronado
 */
@ManagedBean
@RequestScoped
public class ControladorTema {
    private Tema tema = new Tema();//objeto Tema
    private UtilidadTema m_tema = new UtilidadTema();//objeto ManejadorTema
    
    public ControladorTema(){
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }

    public Tema getTema(){
        return this.tema;
    }
    
    public void setTema(Tema tema){
        tema=this.tema;
    }
    
    /**Método agregarTema()
     * Agrega un nuevo tema haciendo uso del manejador de tema.
     * @return 
     */
    public String agregarTema(){
        tema.setId_tema(m_tema.generaId());
        tema.setId_usuario(1);
        m_tema.save(tema);
        tema = null;
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "El tema se agregó con éxito", ""));
        
        return "exito";
    }  
    
    /**Método eliminarTema()
     * Elimina el tema haciendo uso del manejador de tema.
     */
    public String eliminarTema(){
        m_tema.delete(tema);
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "El tema se eliminó con éxito", ""));
        return "exito";
    } 
    
    /**Método getBuscarTema()
     * 
     * @return 
     */
    public void buscarTema(){ 
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Buscando el tema...", ""));
        Tema t = m_tema.buscar(tema);
        
        System.out.println(t.getTitulo_tema()+"\n"+t.getDescripcion_tema()+"\n"+t.getColor_tema());
        
    }
    

}
