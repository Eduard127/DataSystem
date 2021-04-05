package ventanas;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import clases.Conexion;

public class Capturista extends JFrame implements ActionListener{

	String user, nombre_usuario;
	int sesion_usuario;
	
	
	private JLabel label_Wallpaper,label1,label2,label3,label4;
	private JButton btn1,btn2,btn3;
	
	
	public Capturista() {
		
		
		user = Login.user;	
		sesion_usuario = Administrador.sesion_de_usuario;
		
		setLayout(null);
		setBounds(0,0,550,300);
		//setSize(450,600);
		setResizable(false);
		setTitle("Capturista session de " + user);
		setLocationRelativeTo(null);
		
		setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
		
		if(sesion_usuario == 1) {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}else {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}
		
		label_Wallpaper = new JLabel();
		label_Wallpaper.setBounds(0,0,550,300);
		add(label_Wallpaper);
		
		ImageIcon WallPaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
		Icon icono = new ImageIcon(WallPaper.getImage().getScaledInstance(label_Wallpaper.getWidth(),
				label_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		label_Wallpaper.setIcon(icono);
		this.repaint();
		
		label1 = new JLabel("");
		label1.setBounds(10,10,200,30);
		label_Wallpaper.add(label1);
		
		label2 = new JLabel("Registrar Cliente");
		label2.setBounds(50,200,200,30);
		label_Wallpaper.add(label2);
		
		label3 = new JLabel("Gestionar Cliente");
		label3.setBounds(200,200,200,30);
		label_Wallpaper.add(label3);
		
		label4 = new JLabel("Imprimir reporte");
		label4.setBounds(390,200,200,30);
		label_Wallpaper.add(label4);
		
		
		btn1 = new JButton();
		btn1.setBounds(50,90,100,100);
		label_Wallpaper.add(btn1);
		btn1.addActionListener(this);
		
		ImageIcon btnimagen = new ImageIcon("src/images/add.png");
		Icon btn1icono = new ImageIcon(btnimagen.getImage().getScaledInstance(btn1.getWidth()/2,
				btn1.getHeight()/2, Image.SCALE_DEFAULT));
		btn1.setIcon(btn1icono);
		this.repaint();
		
		btn2 = new JButton();
		btn2.setBounds(220,90,100,100);
		label_Wallpaper.add(btn2);
		btn2.addActionListener(this);
		
		ImageIcon btn2imagen = new ImageIcon("src/images/informationuser.png");
		Icon btn2icono = new ImageIcon(btn2imagen.getImage().getScaledInstance(btn2.getWidth()/2,
				btn2.getHeight()/2, Image.SCALE_DEFAULT));
		btn2.setIcon(btn2icono);
		this.repaint();
		
		btn3 = new JButton();
		btn3.setBounds(390,90,100,100);
		label_Wallpaper.add(btn3);
		btn3.addActionListener(this);
		
		ImageIcon btn3imagen = new ImageIcon("src/images/impresora.png");
		Icon btn3icono = new ImageIcon(btn3imagen.getImage().getScaledInstance(btn3.getWidth()/2,
				btn3.getHeight()/2, Image.SCALE_DEFAULT));
		btn3.setIcon(btn3icono);
		this.repaint();
		
try {
			//codigo para entrar a la base de datos y averiguar el user para ponerlo en el label1
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select nombre_usuario from usuarios where username = '" + user + "'");
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				
				nombre_usuario = rs.getString("nombre_usuario");
				label1.setText("Bienvenido " + nombre_usuario);
				
			}
		}catch(SQLException e) {
			System.err.println(" error en la conexion desde interfaz administrador");
		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == btn1) {
			new RegistrarCliente().setVisible(true); 
		}
		else if(arg0.getSource() == btn2) {
			new GestionarCliente().setVisible(true);
		}
		else if(arg0.getSource() == btn3) {
			
		}
	}
	
}
