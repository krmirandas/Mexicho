package com.tuukul.proyecto.web;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.tuukul.modelo.Usuario;
import com.tuukul.modelo.UtilidadUsuario;
import java.util.Random;

@ManagedBean
@RequestScoped
public class RegisterController {
    private Random rn = new Random();
    private Usuario user = new Usuario();
    private UtilidadUsuario u = new UtilidadUsuario();

    public RegisterController() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
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
    
    public String verificaDatos() throws Exception{
        Usuario us;
        String resultado = "";
        try{
            us = u.verificaDatos(user);
            if(us != null){
                FacesContext context = FacesContext.getCurrentInstance();
                context.getCurrentInstance().getExternalContext()
                        .getSessionMap().put("usuario", us);
                context.getExternalContext().getSessionMap()
                        .put("rol", us.getRol());
                System.out.println(us.getRol());
                if(us.getRol().equals("Administrador")){
                    resultado = "PerfilAdmin?faces-redirect=true";
                }else if(us.getRol().equals("Comentarista")){
                    resultado = "PerfilComent?faces-redirect=true";
                }else if(us.getRol().equals("Informador")){
                    resultado = "PerfilInfor?faces-redirect=true";
                }
            }else{
                resultado = "error";
            }
        }
        catch(Exception e){
            throw e;
        }
        return resultado;
    }

    public boolean verificaSesion(){
        boolean estado;
        
        if(FacesContext.getCurrentInstance().getExternalContext()
            .getSessionMap().get("usuario") == null){
            estado = false;
        }else{
            estado = true;
        }
      return estado;
    }
    
    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().
                invalidateSession();
        return "iniciarSesion?faces-redirect=true";
    }

}
