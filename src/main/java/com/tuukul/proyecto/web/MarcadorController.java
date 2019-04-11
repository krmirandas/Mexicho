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
import com.tuukul.modelo.UtilidadMarcador;
import com.tuukul.modelo.Marcador;
import java.util.List;
import javax.annotation.PostConstruct;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@RequestScoped


public class MarcadorController {
    private Marcador marcador = new Marcador();
    private UtilidadMarcador u = new UtilidadMarcador();
    private MapModel simpleModel;
    private List<Marcador> marcadores = null;

    
    public Marcador getMarcador() {
        return marcador;
    }

    public void setMarcador(Marcador marcador) {
        this.marcador = marcador;
    }

    public MarcadorController() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }

    public String addMarcador() {
        if (marcador == null) {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Debes asignar un titulo al marcador", ""));
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "El marcador se agregó con éxito", ""));
            u.save(marcador);
            marcador = null;
        }
        return null;
    }
    
    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
          
        //Shared coordinates
        marcadores = u.getMarcadores();
        for(Marcador dist : marcadores) {
            LatLng coord = new LatLng(dist.getLatitud(), dist.getLongitud());
            simpleModel.addOverlay(new Marker(coord, dist.getTitulo_marcador()));
        } 
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
}
