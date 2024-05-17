package config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

@WebListener
public class AppIntializer implements ServletContextListener{


    private static final String PROPERTIES_FILE_PATH = "/resources/application.properties";
    private Properties keys = new Properties();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        InputStream fileInputStream = servletContext.getResourceAsStream("resources/application.properties");
        try {
			keys.load(fileInputStream);
			System.out.println("password: " + keys.getProperty("database.driver.password"));
			System.out.println("redis: " + keys.getProperty("redis.connection.url"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Config config = new Config();
        config.useSingleServer().setAddress(keys.getProperty("redis.connection.url"));
        RedissonClient redissonClient = Redisson.create(config);
		System.out.println("redis client initialized!! on server 1");

		Connection connection= null;
		try {
			  Class.forName(keys.getProperty("database.driver.name"));
			  connection = DriverManager.getConnection(keys.getProperty("database.connection.url"), keys.getProperty("database.driver.username"),  keys.getProperty("database.driver.password"));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       AppProperties dbProperties = new AppProperties(keys.getProperty("database.driver.username"), keys.getProperty("database.driver.password"),
    		   keys.getProperty("database.connection.url"), keys.getProperty("database.driver.name"),
    		   keys.getProperty("redis.connection.url"), redissonClient, connection);
       servletContext.setAttribute("dbProperties", dbProperties);


    }

}
