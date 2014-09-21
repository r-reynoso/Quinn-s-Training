package org.training.service.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DatabaseUtil {
    
    private final static DatabaseUtil instance = new DatabaseUtil();
    public static DatabaseUtil getInstance() {  
        return instance;  
    } 
    
    private final SessionFactory sessionFactory;  
    private final ServiceRegistry serviceRegistry;
    
    private DatabaseUtil() {
    	
    	Configuration conf = new Configuration();  
    	conf.configure();  
    	
    	serviceRegistry = new StandardServiceRegistryBuilder()
    	.applySettings(conf.getProperties()).build();
    	
    	try {  
    		sessionFactory = conf.buildSessionFactory( serviceRegistry );  
    	} catch (Exception e) {  
    		System.err.println( "Initial SessionFactory creation failed." + e );  
    		throw new ExceptionInInitializerError( e );
    	}       
	}
    
    public SessionFactory getSessionFactory() {
    	return sessionFactory;
    }
    
    public ServiceRegistry getServiceRegistry() {
    	return serviceRegistry;
    }
}
                     