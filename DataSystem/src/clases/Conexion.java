package clases;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conexion {

	//conexion local
	
	public static Connection conectar() {
		
		try {
			
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_ds","root","");
			return cn;
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "error al conectarse a la base de datos" + e);
		}
		return(null);
	}
	
}
