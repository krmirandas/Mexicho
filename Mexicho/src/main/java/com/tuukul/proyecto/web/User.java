package com.tuukul.proyecto.web;
import javax.faces.context.FacesContext;

public class User {

    private String usuario;
    private String contraseña;
    private String confirmacionContraseña;
    private String correo;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getConfirmacionContraseña() {
        return confirmacionContraseña;
    }

    public void setConfirmacionContraseña(String confirmacionContraseña) {
        this.confirmacionContraseña = confirmacionContraseña;
    }
    
    public boolean verificaSesion(){
        boolean estado;
        
        if(FacesContext.getCurrentInstance().getExternalContext()
            .getSessionMap().get("User") == null){
            estado = false;
        }else{
            estado = true;
        }
      return estado;
    }
    
    public String cerrarSession(){
        FacesContext.getCurrentInstance().getExternalContext().
                invalidateSession();
        return "index?faces-redirect=true";
    }
}
