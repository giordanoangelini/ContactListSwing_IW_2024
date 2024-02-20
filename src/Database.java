import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class Database {
	
	public static ArrayList<Persona> fetchPersoneFromDB() {
	    	
    	ArrayList<Persona> rubrica = new ArrayList<>();
    	
    	Connection conn = DatabaseConnection.getInstance().getConnection();
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Persona");
	        while (resultSet.next()) {
	        	Integer id = resultSet.getInt("id");
	        	String nome = resultSet.getString("nome");
	            String cognome = resultSet.getString("cognome");
	            String indirizzo = resultSet.getString("indirizzo");
	            String telefono = resultSet.getString("telefono");
	            Integer eta = resultSet.getInt("eta");
	            if (resultSet.wasNull()) eta = (Integer) null;
	
	            rubrica.add(new Persona(id, nome, cognome, indirizzo, telefono, eta));
	   
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rubrica;
    }
	    
	public static void addPersonaDB(String nome, String cognome, String indirizzo, String telefono, Integer eta) {
		
		Connection conn = DatabaseConnection.getInstance().getConnection();
		
		String query = "INSERT INTO Persona (nome, cognome, indirizzo, telefono, eta) VALUES (?, ?, ?, ?, ?)";
		try {
	        PreparedStatement preparedStatement = conn.prepareStatement(query);
	        if (nome == null) preparedStatement.setNull(1, Types.VARCHAR); else preparedStatement.setString(1, nome);
	        if (cognome == null) preparedStatement.setNull(2, Types.VARCHAR); else preparedStatement.setString(2, cognome);
	        if (indirizzo == null) preparedStatement.setNull(3, Types.VARCHAR); else preparedStatement.setString(3, indirizzo);
	        if (telefono == null) preparedStatement.setNull(4, Types.VARCHAR); else preparedStatement.setString(4, telefono);
	        if (eta == null) preparedStatement.setNull(5, Types.INTEGER); else preparedStatement.setInt(5, eta);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	public static void editPersonaDB(int id, String nome, String cognome, String indirizzo, String telefono, Integer eta) {
			
		Connection conn = DatabaseConnection.getInstance().getConnection();
		
		String query = "UPDATE Persona SET nome = ?, cognome = ?, indirizzo = ?, telefono = ?, eta = ? WHERE id = ?";
		try {
	        PreparedStatement preparedStatement = conn.prepareStatement(query);
	        if (nome == null) preparedStatement.setNull(1, Types.VARCHAR); else preparedStatement.setString(1, nome);
	        if (cognome == null) preparedStatement.setNull(2, Types.VARCHAR); else preparedStatement.setString(2, cognome);
	        if (indirizzo == null) preparedStatement.setNull(3, Types.VARCHAR); else preparedStatement.setString(3, indirizzo);
	        if (telefono == null) preparedStatement.setNull(4, Types.VARCHAR); else preparedStatement.setString(4, telefono);
	        if (eta == null) preparedStatement.setNull(5, Types.INTEGER); else preparedStatement.setInt(5, eta);	       
	        preparedStatement.setInt(6, id);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
		
	public static void deletePersonaDB(Integer id) {
	    	
		Connection conn = DatabaseConnection.getInstance().getConnection();
			
		String query = "DELETE FROM Persona WHERE id = ?";
		try {
	        PreparedStatement preparedStatement = conn.prepareStatement(query);
	        preparedStatement.setInt(1, id);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
