package com.ideas2it.connectionManager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>
 * This class make a session for Hibernate Connection
 * </p> 
 * @author Aravind
 */
public class HibernateManager {
    private static final SessionFactory factory;
    private static final Logger logger = LogManager.getLogger();

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            logger.error("Failed to create sessionFactory object.{}", e);
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