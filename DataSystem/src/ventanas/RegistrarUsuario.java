package ventanas;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import clases.Conexion;

public class RegistrarUsuario extends JFrame implements ActionListener{

	
	String user;  // recuperamos el nombre de usuario de quien a iniciado sesion
	
	private JLabel label_Wallpaper, label_titulo, label_nombre, label_email, label_telefono, label_username, label_password, label_permisos;
	private JTextField txt_nombre, txt_email, txt_telefono, txt_username;
	private JPasswordField txt_pass;
	private JButton btn_agregar;
	private JComboBox<String> cmb_niveles;
	
	
	
	public RegistrarUsuario() {
		
		user = Login.user; // recuperamos el nombre de usuario de quien a iniciado sesion
		
		setLayout(null);
		setBounds(0,0,600,400);
		//setSize(450,600);
		setResizable(false);
		setTitle("Registrar Usuarios");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
		
		label_Wallpaper = new JLabel("");
		label_Wallpaper.setBounds(0,0,650,430);
		add(label_Wallpaper);
		
		ImageIcon Wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
		Icon iconoWallpaper = new ImageIcon(Wallpaper.getImage().getScaledInstance(label_Wallpaper.getWidth(),
				label_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		label_Wallpaper.setIcon(iconoWallpaper);
		this.repaint();
		
		label_titulo = new JLabel("Registrar Usuario");
		label_titulo.setBounds(250,15,150,40);
		label_Wallpaper.add(label_titulo);
		
		label_nombre = new JLabel("Nombre");
		label_nombre.setBounds(10,80,100,40);
		label_Wallpaper.add(label_nombre);
		
		label_email = new JLabel("Email");
		label_email.setBounds(10,130,100,40);
		label_Wallpaper.add(label_email);
		
		label_telefono = new JLabel("Telefono");
		label_telefono.setBounds(10,180,100,40);
		label_Wallpaper.add(label_telefono);
		
		label_permisos = new JLabel("permisos");
		label_permisos.setBounds(10,240,100,40);
		label_Wallpaper.add(label_permisos);
		
		label_username = new JLabel("Username");
		label_username.setBounds(400,80,100,40);
		label_Wallpaper.add(label_username);
		
		label_password = new JLabel("Password");
		label_password.setBounds(400,130,100,40);
		label_Wallpaper.add(label_password);
		
		txt_nombre = new JTextField("");
		txt_nombre.setBounds(10,110,150,20);
		label_Wallpaper.add(txt_nombre);
		
		txt_email = new JTextField("");
		txt_email.setBounds(10,160,150,20);
		label_Wallpaper.add(txt_email);
		
		txt_telefono = new JTextField("");
		txt_telefono.setBounds(10,210,150,20);
		label_Wallpaper.add(txt_telefono);
		
		txt_username = new JTextField("");
		txt_username.setBounds(400,110,150,20);
		label_Wallpaper.add(txt_username);
		
		txt_pass = new JPasswordField("");
		txt_pass.setBounds(400,160,150,20);
		label_Wallpaper.add(txt_pass);
		
		btn_agregar = new JButton("");
		btn_agregar.setBounds(400,250,100,100);
		btn_agregar.addActionListener(this);
		label_Wallpaper.add(btn_agregar);
		
		ImageIcon agregar = new ImageIcon("src/images/addUser.png");
		Icon icono_agregar = new ImageIcon(agregar.getImage().getScaledInstance(btn_agregar.getWidth()/2, 
				btn_agregar.getHeight()/2, Image.SCALE_DEFAULT));
		btn_agregar.setIcon(icono_agregar);
		this.repaint();
		
		cmb_niveles = new JComboBox();
		cmb_niveles.setBounds(10, 280, 100, 20);
		label_Wallpaper.add(cmb_niveles);
		
		cmb_niveles.addItem("Administrador");
		cmb_niveles.addItem("Capturista");
		cmb_niveles.addItem("Tecnico");
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == btn_agregar) {
			
			int permisos_cmb, validacion = 0;
			String nombre, email, telefono, username, pass, permisos_string = "";
			// desde aqui inicia mi codigo
			
					
			username = txt_username.getText().trim();
			nombre = txt_nombre.getText().trim();
			email = txt_email.getText().trim();
			telefono = txt_telefono.getText().trim();
			pass = txt_pass.getText().trim();
			permisos_cmb = cmb_niveles.getSelectedIndex() + 1;
			
			if(username.equalsIgnoreCase("")) {
				txt_username.setBackground(new Color(200,0,0));
				validacion++;
			}
			if(nombre.equalsIgnoreCase("")) {
					txt_nombre.setBackground(new Color(200,0,0));
					validacion++;
			}
			if(email.equalsIgnoreCase("")) {
					txt_email.setBackground(new Color(200,0,0));
					validacion++;
			}
			if(telefono.equalsIgnoreCase("")) {
					txt_telefono.setBackground(new Color(200,0,0));
					validacion++;
			}
			if(pass.equalsIgnoreCase("")) {
				txt_pass.setBackground(new Color(200,0,0));
				validacion++;
			}	
			if(permisos_cmb == 1) {
				permisos_string = "Administrador";
			}
			else if(permisos_cmb == 2) {
				permisos_string = "Capturista";
			}
			else if(permisos_cmb == 3) {
				permisos_string = "Tecnico";
			}
			
			try {
				
				Connection cn = Conexion.conectar();
				PreparedStatement pst = cn.prepareStatement("select username from usuarios where username = '" + username + "'");
				
				ResultSet rs = pst.executeQuery(); 
				
				if(rs.next()) {
					txt_username.setBackground(new Color(200,0,0));
					JOptionPane.showMessageDialog(null, "Nombre de usuario no disponible");
					cn.close();
				}else {
					cn.close();
					
					if(validacion == 0) {
						try {
							Connection cn2 = Conexion.conectar();
							PreparedStatement pst2 = cn2.prepareStatement("insert into usuarios values (?,?,?,?,?,?,?,?,?)");
							
							pst2.setInt(1, 0);
							pst2.setString(2, nombre);
							pst2.setString(3, email);
							pst2.setString(4, telefono);
							pst2.setString(5, username);
							pst2.setString(6, pass);
							pst2.setString(7, permisos_string);
							pst2.setString(8, "Activo");
							pst2.setString(9, user);
							
							pst2.executeUpdate();
							cn2.close();
							
							Limpiar();
							
							txt_username.setBackground(new Color(0,200,0));
							txt_nombre.setBackground(new Color(0,200,0));
							txt_email.setBackground(new Color(0,200,0));
							txt_telefono.setBackground(new Color(0,200,0));
							txt_pass.setBackground(new Color(0,200,0));
							
							JOptionPane.showMessageDialog(null, "Registro exitoso");
							this.dispose();
							
							
						}catch(SQLException e) {
							System.err.println("Error al registrar usuario" + e);
							JOptionPane.showMessageDialog(null, "!! ERROR al registrar// contacte al administrador");
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
					}
				}
				
				
			}catch(SQLException i) {
				System.err.println("Error en validar el nombre de usuario + "  + i);
				JOptionPane.showMessageDialog(null, "!!! ERROR al comparar usuario// Contacte con el administrador");
			}
			
			//aqui termina mi cosigo
		}
	}


	
	public void Limpiar() {
		
		txt_username.setText("");
		txt_nombre.setText("");
		txt_email.setText("");
		txt_telefono.setText("");
		txt_pass.setText("");
		cmb_niveles.setSelectedIndex(0);
		
	}
}
