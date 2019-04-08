/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuukul.modelo;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class Utility {

    static User userObj;
    static Session sessionObj;

    public int generaId(){
        List<User> obj = null;
        sessionObj = HibernateUtil.getSessionFactory().openSession();

        try{
            sessionObj.beginTransaction();
            String hql = "FROM usuarios.usuario";
            Query query = sessionObj.createQuery(hql);
            obj = (List<User>)query.list();
            sessionObj.getTransaction().commit();
            int max = 0;
            for(int i=0; i<obj.size(); i++){//iteramos sobre los id_usuario
                int num = obj.get(i).getId_usuario();
                if(num>max)//Si el num es mayor al max  actualizamos el max
                    max = num;
            }
	    return max+1;//Este será el id del nuevo usuario
        }catch(Exception sqlException){
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }finally{
            if (sessionObj != null) {
                sessionObj.close();
            }            
        } 
        return 0;
    }

    public void save(User usuario) {
        try {
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            sessionObj.save(usuario);
            sessionObj.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }
    public void delete(User usuario){
        try {
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            sessionObj.delete(usuario);
            sessionObj.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

}
