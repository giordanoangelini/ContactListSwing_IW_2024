import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
	
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() { 
    	
    	Properties properties = new Properties();
    	
    	try {    		
    		FileInputStream input = new FileInputStream("credenziali_database.properties");
            properties.load(input);
            dbUrl = properties.getProperty("db.url");
            dbUsername = properties.getProperty("db.username");
            dbPassword = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	
        try {      
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            System.err.println("Errore durante la connessione al database: " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public Connection getConnection() {
    	return connection;
    }

    
}
