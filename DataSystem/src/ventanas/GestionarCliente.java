package ventanas;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

public class GestionarCliente extends JFrame {

	String user;
	public static int ID_cliente_update = 0; 
	
	private JLabel label_Wallpaper, label1,label2;
	private JScrollPane js_scrollpane;
	private JTable tabla;
	DefaultTableModel model = new DefaultTableModel();
	
	
	public GestionarCliente() {
		
		
		user = Login.user;
		
		setLayout(null);
		setBounds(0,0,600,400);
		//setSize(450,600);
		setResizable(false);
		setTitle("Gestionar Usuarios " + user);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
		
		label_Wallpaper = new JLabel();
		label_Wallpaper.setBounds(0,0,600,400);
		add(label_Wallpaper);
		
		ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
		Icon wallpaper_icono = new ImageIcon(wallpaper.getImage().getScaledInstance(label_Wallpaper.getWidth(), 
				label_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		label_Wallpaper.setIcon(wallpaper_icono);
		this.repaint();
		
		label1 = new JLabel(" Gestionar Clientes");
		label1.setBounds(220,10,200,60);
		label_Wallpaper.add(label1);
		
		label2 = new JLabel("La Geekipedia de ernesto");
		label2.setBounds(220,300,200,60);
		label_Wallpaper.add(label2);
		
		js_scrollpane = new JScrollPane();
		js_scrollpane.setBounds(0,100,600,130);
		label_Wallpaper.add(js_scrollpane);
		
		tabla = new JTable(model);
		tabla.setBounds(0,100,400,130);
		
			model.addColumn(" ");
			model.addColumn("Nombre");
			model.addColumn("Email");
			model.addColumn("Telefono");
			model.addColumn("Modificado por");
			
		js_scrollpane.setViewportView(tabla);
		
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select id_cliente, nombre_cliente, email_cliente, tel_cliente, ultima_modificacion from clientes");
			
			ResultSet rs = pst.executeQuery();
			
				while(rs.next()) {
				
					Object[] fila = new Object[5];
				
					for(int i = 0; i < 5; i++) {
						fila[i] = rs.getObject(i + 1);
				}
				
				model.addRow(fila);
				
			}
			cn.close();
			
		}catch(Exception e) {
			System.err.println("Error en la base de datos mirar usuarios " + e);
		}
		
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int fila_point = tabla.rowAtPoint(e.getPoint());
				int column_point = 0;
				
				if(fila_point > -1) {
					ID_cliente_update = (int)model.getValueAt(fila_point, column_point);
					JOptionPane.showMessageDialog(null, "el id del usuario es " + ID_cliente_update);
					new InformacionCliente().setVisible(true);
				}
			}
		});
					
	}
}
