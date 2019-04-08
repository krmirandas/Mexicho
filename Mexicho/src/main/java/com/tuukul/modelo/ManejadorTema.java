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
     * Para evitar problemas con los índices, tomamos el máximo y le sumamos uno.
     * @return int 
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
            int max = 0;
            for(int i=0; i<obj.size(); i++){//iteramos sobre los id_tema
                int num = obj.get(i).getId_tema();
                if(num>max)//Si el num es mayor al max  actualizamos el max
                    max = num;
            }
            return max+1;//Este será el id del nuevo tema
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
    
    /**Método save
     * Guardamos el nuevo tema.
     * @param tema 
     */
    public void save(Tema tema) {
        try {
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            sessionObj.save(tema);//Guardamos el tema
            sessionObj.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Ocurrió un error.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }
    
    
    /**Método delete
     * Elimina un tema. (Se elimina con el título)
     * @param tema 
     */
    public void delete(Tema tema){
        
        try{
            String titulo= tema.getTitulo_tema();
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            String hql = "delete Tema t WHERE t.titulo_tema= :titulo";
            sessionObj.createQuery(hql).setString("titulo",titulo).executeUpdate();
            sessionObj.getTransaction().commit();
        }catch(Exception sqlException){
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Ocurrió un error.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }finally{
            if (sessionObj != null) {
                sessionObj.close();
            }            
        } 
    }
    
    /**Método buscar
     * Busca un tema por el título e imprime su información.
     * @param tema
     * @return 
     */
    public Tema buscar(Tema tema){
        
        try{
            String titulo= tema.getTitulo_tema();
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            String hql = " FROM Tema t WHERE t.titulo_tema= :titulo";
            Query query = sessionObj.createQuery(hql).setString("titulo",titulo);
            Tema t = (Tema)query.uniqueResult();
            sessionObj.getTransaction().commit();
            return t;
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
        return null;
    }
}