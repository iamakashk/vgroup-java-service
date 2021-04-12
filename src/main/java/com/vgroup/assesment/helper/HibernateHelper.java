package com.vgroup.assesment.helper;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.vgroup.assesment.ProjectDetails;
import com.vgroup.assesment.model.Combination;
import com.vgroup.assesment.model.UserInput;
import com.vgroup.assesment.service.TestServiceImpl;

public class HibernateHelper {
	private static Logger logger = LoggerFactory.getLogger(HibernateHelper.class);
	
	@Autowired
    public ProjectDetails bean;
		
	public static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory(String dbUrl) {
		logger.info(" DB URL : " + dbUrl);
		long startTime  = System.currentTimeMillis();
		if (sessionFactory == null) {
			try {
				

				Configuration configuration = new Configuration();
				Properties settings = new Properties();

				String driverName = "com.mysql.jdbc.Driver";
				//String dbUrl = dbUrl;
				String dbUserName = "root";
				String dbPassword = "root";
				//LOG.info(logID + ServiceMessageConstants.MYSQL_CONNECTION_TRY + dbUrl);

				
				settings.put(Environment.DRIVER, driverName);
				settings.put(Environment.URL, dbUrl + "?useSSL=false");
				settings.put(Environment.USER, dbUserName);
				settings.put(Environment.PASS, dbPassword);
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				settings.put(Environment.SHOW_SQL, true);
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				/* settings.put(Environment.HBM2DDL_AUTO, "create-drop"); */
				settings.put("hibernate.connection.characterEncoding", "utf8");
				settings.put(Environment.DEFAULT_BATCH_FETCH_SIZE, 20);
				
				// c3p0 configuration start
				/*
				 * The above statement is used to specify the C3P0 connection provider.
				 * Here we use C3P0ConnnectionProvider class
				 */
				settings.put(Environment.CONNECTION_PROVIDER, "org.hibernate.connection.C3P0ConnectionProvider");
				/*
				 * This one tells the connection pool that it can hold 10 connections at the minimum. 
				 * Generally this will vary from (3-20) based on the need.
				 */
	            settings.put(Environment.C3P0_MIN_SIZE, 10);         //Minimum size of pool
	            /*
				 * This one tells the connection pool that it can hold 200 connections at the maximum
				 */
	            settings.put(Environment.C3P0_MAX_SIZE, 20);        //Maximum size of pool
	            /*
	             *  This determines how many connections the connection pool can acquire when the pool is exhausted.
	             */
	            settings.put(Environment.C3P0_ACQUIRE_INCREMENT, 100);//Number of connections acquired at a time when pool is exhausted
	            /*
	             *  Its Default value is 0. Zero means, idle connections never expire. If the value given is greater than 0,
	             *  then for the specified seconds the Connection can remain idle in pool but unused before discarded.
	             */
	            settings.put(Environment.C3P0_TIMEOUT, 1800);       //Connection idle time
	            /*
	             *  Its Default value is 0. The value that you specify here is the size of global PreparedStatement cache.
	             *   For more info: http://www.mchange.com/projects/c3p0/#configuring_statement_pooling
	             */
	            settings.put(Environment.C3P0_MAX_STATEMENTS, 150); //PreparedStatement cache size
	            settings.put(Environment.C3P0_CONFIG_PREFIX+".initialPoolSize", 5);
	            settings.put(Environment.C3P0_IDLE_TEST_PERIOD, 1500);
	            /*
	             * Default value is false. If we specify true, before every transaction gonna happen, 
	             * it will validate the connection is valid or not. And we need to specify efficient preferredTestQuery.
	             */
	            settings.put(Environment.C3P0_CONFIG_PREFIX+".validate", true);
	            // c3p0 configuration end
				
	            configuration.setProperties(settings);

				/* <<ADD HIBERNATE ANNOTED CLASS>> */

	            configuration.addAnnotatedClass(UserInput.class);
	            configuration.addAnnotatedClass(Combination.class);
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				
				logger.info(" CONNECTION CREATED SUCCESSFULLY");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(" ERROR WHILE CREATING CONNECTION");
				sessionFactory = null;
			}
		}

		long endTime = System.currentTimeMillis();
		System.out.println("TOTAL EXECUTION TIME: "+ (endTime-startTime));
		return sessionFactory;
	}	
}

class PropsValue{
	

}
