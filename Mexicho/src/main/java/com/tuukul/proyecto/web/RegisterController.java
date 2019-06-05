package com.tuukul.proyecto.web;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.tuukul.modelo.User;
import com.tuukul.modelo.Utility;
import java.util.Random;

@ManagedBean
@RequestScoped
public class RegisterController {
    private Random rn = new Random();
    private User user = new User();
    private Utility u = new Utility();

    public RegisterController() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String addUser() {
        if (!user.getContrasena().equals(user.getConfirmaContrasena())) {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Fallo de registro: Las contraseñas deben coincidir", ""));
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Felicidades, el registro se ha realizado correctamente", ""));
	    user.setId_usuario(u.generaId());
	    user.setRol("Comentarista");
	    user.setUrl_imagen("Ninguna");
	    u.save(user);
            user = null;
        }
        return null;
    }

    public String addInformador(){           
        if (!correoValido(user.getCorreo())) {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Fallo de registro: Correo invalido", ""));
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Felicidades, el registro se ha realizado correctamente", ""));
            user.setContrasena(generaContrasenia());
	    user.setId_usuario(u.generaId());
	    user.setRol("Informador");
	    user.setUrl_imagen("Ninguna");
            u.save(user);
            user = null;
        }
        return null;	
    }
    public String generaContrasenia(){
	String[] total = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","1","2","3","4","5","6","7","8","9","0"};
	String contrasenia = new String();
	int n = 0;
	for (int i = 0; i<8; i++){	    
	    n=rn.nextInt(total.length);
            contrasenia += total[n];
        }
	return contrasenia;
    }

    public boolean correoValido(String correoE){
	return correoE.contains("@");
    }
    public void eliminarUsuario(){
        u.delete(user);
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "El usuario se eliminó con éxito", ""));

    }      

}
