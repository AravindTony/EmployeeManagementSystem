package com.connectionmanager;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
* This class make a session for Hibernate Connection
*/
public class HibernateManager {
    private static SessionFactory factory = null; 

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) { 
            System.err.println("Failed to create sessionFactory object." + e);
            throw new ExceptionInInitializerError(e); 
        }
    }

    /**
    * This method return the factory from the Configuration from Session Factory
    */
    public static SessionFactory getFactory() { 
       return factory;
    }  
}