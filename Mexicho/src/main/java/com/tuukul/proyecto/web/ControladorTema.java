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
import com.tuukul.modelo.ManejadorTema;
/**
 *
 * @author coronado
 */
@ManagedBean
@RequestScoped
public class ControladorTema {
    private Tema tema = new Tema();
    private ManejadorTema m_tema = new ManejadorTema();
    
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
    
    public String agregarTema(){
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "El tema se agregó con éxito", ""));
        tema.setId_tema(m_tema.generaId());
        tema.setId_usuario(1);
        m_tema.save(tema);
        tema = null;
        
        return null;
    }  
    
    public void eliminarTema(){
        m_tema.delete(tema);
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "El tema se eliminó con éxito", ""));

    }      
    

}
