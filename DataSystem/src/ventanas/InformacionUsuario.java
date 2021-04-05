package ventanas;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import clases.Conexion;

public class InformacionUsuario extends JFrame implements ActionListener{

	String user, user_update;
	int ID;
	
	private JLabel label_Wallpaper, label_titulo, label_nombre, label_email, label_telefono, label_permisosde, label_username, label_estatus,label_registradopor;
	private JTextField txt_nombre, txt_email, txt_telefono, txt_username, txt_registradopor;
	private JButton btn_actualizarusuario, btn_restaurarpassword;
	private JComboBox cmb_permisos,cmb_estatus;
	
	public InformacionUsuario() {
		
		user = Login.user;
		user_update = GestionarUsuarios.user_update;
				
		setLayout(null);
		setBounds(0,0,630,450);
		//setSize(450,600);
		setResizable(false);
		setTitle("Informacion del Usuario " + user_update + " / secion de " + user);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
		
		
		label_Wallpaper = new JLabel();
		label_Wallpaper.setBounds(0,0,630,450);
		add(label_Wallpaper);
		
		ImageIcon Wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
		Icon icono = new ImageIcon(Wallpaper.getImage().getScaledInstance(label_Wallpaper.getWidth(),
				label_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		label_Wallpaper.setIcon(icono);
		this.repaint();
		
		
		label_titulo = new JLabel("Informacion Del Usuario " + user_update);
		label_titulo.setBounds(250,30,200,50);
		label_Wallpaper.add(label_titulo);
		
		label_nombre = new JLabel("Nombre");
		label_nombre.setBounds(10,90,100,50);
		label_Wallpaper.add(label_nombre);
		
		label_email = new JLabel("email");
		label_email.setBounds(10,140,200,50);
		label_Wallpaper.add(label_email);
		
		label_telefono = new JLabel("telefono");
		label_telefono.setBounds(10,190,200,50);
		label_Wallpaper.add(label_telefono);
		
		label_permisosde = new JLabel("permisos: ");
		label_permisosde.setBounds(10,260,200,50);
		label_Wallpaper.add(label_permisosde);
		
		label_username = new JLabel("Username");
		label_username.setBounds(350,90,200,50);
		label_Wallpaper.add(label_username);
		
		label_estatus = new JLabel("Estatus: ");
		label_estatus.setBounds(350,135,200,50);
		label_Wallpaper.add(label_estatus);
		
		label_registradopor = new JLabel("Registrado por");
		label_registradopor.setBounds(350,190,100,50);
		label_Wallpaper.add(label_registradopor);
		
		
	    txt_nombre = new JTextField();
	    txt_nombre.setBounds(10,125,200,20);
	    label_Wallpaper.add(txt_nombre);
	    
	    txt_email = new JTextField();
	    txt_email.setBounds(10,175,200,20);
	    label_Wallpaper.add(txt_email);
	    
	    txt_telefono = new JTextField();
	    txt_telefono.setBounds(10,225,200,20);
	    label_Wallpaper.add(txt_telefono);
	    
	    txt_username = new JTextField();
	    txt_username.setBounds(350,125,200,20);
	    label_Wallpaper.add(txt_username);
	    
	    txt_registradopor = new JTextField();
	    txt_registradopor.setBounds(350,225,200,20);
	    label_Wallpaper.add(txt_registradopor);
	    
	    
	    btn_actualizarusuario = new JButton("Actualizar Usuario");
	    btn_actualizarusuario.setBounds(350,270,200,30);
	    label_Wallpaper.add(btn_actualizarusuario);
	   btn_actualizarusuario.addActionListener(this);
	   
	    btn_restaurarpassword = new JButton("Restaurar Password");
	    btn_restaurarpassword.setBounds(350,300,200,30);
	    label_Wallpaper.add(btn_restaurarpassword);
	    btn_restaurarpassword.addActionListener(this);
	    
	    
	    cmb_permisos = new JComboBox();
		cmb_permisos.setBounds(10, 300, 100, 20);
		label_Wallpaper.add(cmb_permisos);
		
		cmb_permisos.addItem("Administrador");
		cmb_permisos.addItem("Capturista");
		cmb_permisos.addItem("Tecnico");
		
		cmb_estatus = new JComboBox();
		cmb_estatus.setBounds(350, 180, 100, 20);
		label_Wallpaper.add(cmb_estatus);
		
		cmb_estatus.addItem("Activo");
		cmb_estatus.addItem("Inactivo");
	
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select * from usuarios where username = '" + user_update + "'");
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				
		        ID = rs.getInt("id_usuario");
		        
				txt_nombre.setText(rs.getString("nombre_usuario"));
				txt_email.setText(rs.getString("email"));
				txt_telefono.setText(rs.getString("telefono"));
				txt_username.setText(rs.getString("username"));
				txt_registradopor.setText(rs.getString("registrado_por"));
				
				cmb_permisos.setSelectedItem(rs.getString("tipo_nivel"));
				cmb_estatus.setSelectedItem(rs.getString("estatus"));
				
			}
			cn.close();
		}catch(SQLException e) {
			System.err.println("Error en cargar usuario " + e);
			JOptionPane.showMessageDialog(null, "!!!  ERROR al cargar!!!!    contacte al administrador");
		}
	   
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getSource() == btn_actualizarusuario) {
			
			int permisos_cmb, estatus_cmb, validacion = 0;
		    String nombre, email, telefono, username, permisos_string = "", estatus_string = "";
		    
		    nombre = txt_nombre.getText().trim();
		    email = txt_email.getText().trim();
		    telefono = txt_telefono.getText().trim();
		    username = txt_username.getText().trim();
		    
		    permisos_cmb = cmb_permisos.getSelectedIndex() + 1;
		    estatus_cmb = cmb_estatus.getSelectedIndex() + 1;
		    
		    if(nombre.equals("")) {
		    	txt_nombre.setBackground(new Color(200,0,0));
		    	validacion++;
		    }
		    if(email.equals("")) {
		    	txt_email.setBackground(new Color(200,0,0));
		    	validacion++;
		    }
		    if(telefono.equals("")) {
		    	txt_telefono.setBackground(new Color(200,0,0));
		    	validacion++;
		    }
		    if(username.equals("")) {
		    	txt_username.setBackground(new Color(200,0,0));
		    	validacion++;
		    }else {
		    	JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
		    }
		    
		    if(validacion == 0) {
		    	if(permisos_cmb == 1) {
		    		permisos_string = "Administrador"; 
		    	}
		    	else if(permisos_cmb == 2) {
		    		permisos_string = "Capturista";
		    	}
		    	else if(permisos_cmb == 3) {
		    		permisos_string = "Tecnico";
		    	}
		    
		    	if(estatus_cmb == 1) {
		    		estatus_string = "Activo";
		    	}
		    	else if(estatus_cmb == 2) {
		    		estatus_string = "Inactivo";
		    	}		    
		    		  		  
			try {
				Connection cn = Conexion.conectar();
				PreparedStatement pst = cn.prepareStatement(""
						+ "select username from usuarios where username = '" + username + "' and not id_usuario = '" + ID + "'");
				
				ResultSet rs = pst.executeQuery();
				
				if(rs.next()) {
					txt_username.setBackground(new Color(200,0,0));
					JOptionPane.showMessageDialog(null, "Nombre de usuario no disponible");
					cn.close();
				}else {
					
					Connection cn1 = Conexion.conectar();
					PreparedStatement pst1 = cn1.prepareStatement(
							"update usuarios set nombre_usuario=?, email=?, telefono=?, username=?, tipo_nivel=?, estatus=? "
							+ "where id_usuario = '" + ID + "'");
					
					pst1.setString(1, nombre);
					pst1.setString(2, email);
					pst1.setString(3, telefono);
					pst1.setString(4, username);
					pst1.setString(5, permisos_string);
					pst1.setString(6, estatus_string);
					
					pst1.executeUpdate();
					cn1.close();
					JOptionPane.showMessageDialog(null, "Actualizacion correcta");
					
				}
				
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, "Error comparando la base de datos " + e);
			}
		  }
		}
		
		if(arg0.getSource() == btn_restaurarpassword) {
			
			new RestaurarPassword().setVisible(true);
		}
	}
}
