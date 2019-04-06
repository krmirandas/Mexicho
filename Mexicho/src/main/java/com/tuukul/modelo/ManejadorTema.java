/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tuukul.modelo;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author coronado
 */

public class ManejadorTema {

    static Tema userObj; //Clase entidad que está modelando
    static Session sessionObj;
    
    
        /**Método generaId
     * Regresa un nuevo id para un nuevo tema agregado.
     * Id =  Número de elementos en la lista + 1.
     * @return int 
     * -> id para el nuevo tema registrado
     * -> 0 cuando hay un error
     */
    public int generaId(){
        List<Tema> obj = null;
        sessionObj = HibernateUtil.getSessionFactory().openSession();

        try{
            sessionObj.beginTransaction();
            String hql = "FROM Tema";
            Query query = sessionObj.createQuery(hql);
            obj = (List<Tema>)query.list();
            sessionObj.getTransaction().commit();
            return obj.size()+1;
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

    public void save(Tema tema) {
        try {
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            sessionObj.save(tema);
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
    
    

    public void delete(Tema tema){
        try {
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            sessionObj.delete(tema);
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