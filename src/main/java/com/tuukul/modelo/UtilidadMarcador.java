/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuukul.modelo;

import static com.tuukul.modelo.UtilidadTema.sessionObj;
import static com.tuukul.modelo.UtilidadUsuario.sessionObj;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author coronado
 */
public class UtilidadMarcador {
    static Usuario userObj;
    static Session sessionObj;
    
    
    public void save(Marcador marcador) {
        try {
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            sessionObj.save(marcador);
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
    
    public List<Marcador> getMarcadores() {
        try {
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            String queryString = "FROM Marcador";
            org.hibernate.Query q = sessionObj.createQuery(queryString);
            List<Marcador> marc = q.list();
            sessionObj.getTransaction().commit();
            return marc;
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
        return new ArrayList<>();
    }
    
    public int generaId(){
        List<Marcador> obj = null;
        sessionObj = HibernateUtil.getSessionFactory().openSession();

        try{
            sessionObj.beginTransaction();
            String hql = "FROM Marcador";
            Query query = sessionObj.createQuery(hql);
            obj = (List<Marcador>)query.list();
            sessionObj.getTransaction().commit();
            int max = 0;
            for(int i=0; i<obj.size(); i++){
                int num = obj.get(i).getId_marcador();
                if(num>max)
                    max = num;
            }
            return max+1;
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
}
