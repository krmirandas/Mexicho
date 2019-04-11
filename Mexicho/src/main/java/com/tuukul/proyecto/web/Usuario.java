package com.tuukul.proyecto.web;
import javax.faces.context.FacesContext;
import com.tuukul.modelo.UtilidadUsuario;

public class Usuario {
    private int id_usuario;
    private String usuario;
    private String contraseña;
    private String confirmacionContraseña;
    private String correo;
    
    
    public int getId_usuario(){
        return id_usuario;
    }

    public void setId_usuario(int id_usuario){
        this.id_usuario = id_usuario;
    }

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
 
}
