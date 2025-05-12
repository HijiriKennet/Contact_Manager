import java.sql.*;

public class Principal_SQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Class.forName(com.mysql.cj.jdbc.Driver);
		Class.forName(sun.jdbc.odbc.JdbcOdbcDriver);
		DriverManager.registerDriver(com.mysql.cj.jdbc.Driver);
		*/
		//System.setProperty("jdbc.drivers", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String urlDB = "jdbc:sqlserver://localhost:1433;databaseName=Contact_Manager;user=Kennet;password=Kennet2024!;integratedSecurity=true;trustServerCertificate=true;";

		 try {
			Connection con = DriverManager.getConnection(urlDB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 //Une variable du type ci-dessous permet de récupérer les résultats de requête
		 ResultSet result = null;
		 //try catch pour mettre les besoins de connection 
		 try ( Connection con2 = DriverManager.getConnection(urlDB)) {
		      // ...
			 //l'interface qui permet d'exploiter les requetes
			 Statement stmt = con2.createStatement();
			 
			 String req = "CREATE TABLE ";
			 
			 //Voici comment exécuter une requête
			 result = stmt.executeQuery(req);
			 
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
	
	}

}
