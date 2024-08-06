package com.ideas2it.connectionmanager;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * <p>
 * This class make a session for Hibernate Connection
 * </p> 
 * @author Aravind
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
     * <p>
     * This method return the factory from the Configuration from Session Factory
     * </p>
     * @return Session Factory to the Dao 
     * 
     */
    public static SessionFactory getFactory() { 
       return factory;
    }  
}