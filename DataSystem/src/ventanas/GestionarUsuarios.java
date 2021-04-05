package ventanas;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import clases.Conexion;

public class GestionarUsuarios extends JFrame {

	String user;
	public static String user_update = "";
	
	
	private JLabel label_Wallpaper, label1, label2;
	//private JTextArea txt_textarea;
	private JTable tabla;
	private JScrollPane js_scrollpane;
	DefaultTableModel model = new DefaultTableModel(); //para poder meter nombres a la tabla
	
	
	public GestionarUsuarios() {
		
		
		//setLayout(null);
		setBounds(0,0,600,500);
		//setSize(450,600);
		setResizable(false);
		setTitle("Gestionar Usuarios");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
		
		
		label_Wallpaper = new JLabel();
		label_Wallpaper.setBounds(0,0,600,500);
		add(label_Wallpaper);
		
		ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
		Icon wallpaper_icono = new ImageIcon(wallpaper.getImage().getScaledInstance(label_Wallpaper.getWidth(), 
				label_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		label_Wallpaper.setIcon(wallpaper_icono);
		this.repaint();
		
		label1 = new JLabel(" Gestionar Usuarios");
		label1.setBounds(220,10,200,60);
		label_Wallpaper.add(label1);
		
		label2 = new JLabel("La Geekipedia de ernesto");
		label2.setBounds(220,400,200,60);
		label_Wallpaper.add(label2);
		
		js_scrollpane = new JScrollPane();
		js_scrollpane.setBounds(0,100,600,300);
		label_Wallpaper.add(js_scrollpane);
		
		tabla = new JTable(model);
		tabla.setBounds(0,100,400,300);
		
			model.addColumn(" ");
			model.addColumn("Nombre");
			model.addColumn("Username");
			model.addColumn("permisos");
			model.addColumn("Estatus");
			
		js_scrollpane.setViewportView(tabla);// se mete la tabla en el JSCrollPane con ayuda del metodo setViewPortView
		
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select id_usuario, nombre_usuario, username, tipo_nivel, estatus from usuarios");
			
			ResultSet rs = pst.executeQuery();
				
			
			while(rs.next()) {
				
				Object[] fila = new Object[5];
				
				for(int i = 0; i < 5; i++) {
					fila[i] = rs.getObject(i + 1);
				}
				
				model.addRow(fila);
				
			}
			cn.close();
			
		}catch(SQLException e) {
			System.err.println("error al llenar la tabla " + e);
			JOptionPane.showMessageDialog(null, "Error al mostrar informacion, Contacte administrador");
		}
		
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int fila_point = tabla.rowAtPoint(e.getPoint());
				int column_point = 2;
				
				if(fila_point > -1) {
					user_update = (String)model.getValueAt(fila_point, column_point);
					InformacionUsuario informacion_usuario = new InformacionUsuario();
					informacion_usuario.setVisible(true);
				}
			}
		});
		
	}	
	
}
