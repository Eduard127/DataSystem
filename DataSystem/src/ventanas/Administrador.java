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
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

import clases.Conexion;

public class Administrador extends JFrame implements ActionListener {

	private JButton btn_RegistrarUsuario, btn_GestionarUsuario, btn_Creatividad, btn_Capturista, btn_Tecnico, btn_Acercade;
	private JLabel label_Wallpaper, label_NombreUsuario, label_RegistrarUsuario, label_GestionarUsuario, label_Creatividad,
				   label_Capturista, label_Tecnico, label_Acercade;
	
	String user,nombre_usuario;
	public static int sesion_de_usuario;
	
	public Administrador() {
		
		user = Login.user; //para usar la misma variable de la ventana login
		sesion_de_usuario = 1;
		
		setLayout(null);
		setBounds(0,0,650,430);
		//setSize(450,600);
		setResizable(false);
		setTitle("Administrador - sesion de " + user);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//permite que al cerrar el programa no se siga ejecutando en segundo plano
		setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
	
		Border raised_border = new SoftBevelBorder(SoftBevelBorder.RAISED);
		
		label_Wallpaper = new JLabel("");
		label_Wallpaper.setBounds(0,0,650,430);
		add(label_Wallpaper);
		
		ImageIcon WallPaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
		Icon icono = new ImageIcon(WallPaper.getImage().getScaledInstance(label_Wallpaper.getWidth(),
				label_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		label_Wallpaper.setIcon(icono);
		this.repaint();
		
		label_NombreUsuario = new JLabel(""); // revisar, para que de el nombre de usuario
		label_NombreUsuario.setBounds(0,0,120,30);
		label_Wallpaper.add(label_NombreUsuario);
		
		label_RegistrarUsuario = new JLabel("Registrar Usuario");
		label_RegistrarUsuario.setBounds(60,155,120,30);
		label_Wallpaper.add(label_RegistrarUsuario);
		
		label_GestionarUsuario = new JLabel("Gestionar Usuario");
		label_GestionarUsuario.setBounds(260,155,120,30);
		label_Wallpaper.add(label_GestionarUsuario);
		
		label_Creatividad = new JLabel("Creatividad");
		label_Creatividad.setBounds(460,155,120,30);
		label_Wallpaper.add(label_Creatividad);
		
		label_Capturista = new JLabel("Capturista");
		label_Capturista.setBounds(60,345,120,30);
		label_Wallpaper.add(label_Capturista);
		
		label_Tecnico = new JLabel("Tecnico");
		label_Tecnico.setBounds(260,345,120,30);
		label_Wallpaper.add(label_Tecnico);
		
		label_Acercade = new JLabel("Acerca De..");
		label_Acercade.setBounds(460,345,120,30);
		label_Wallpaper.add(label_Acercade);
		
		btn_RegistrarUsuario = new JButton("");
		btn_RegistrarUsuario.setBounds(60,60,120,100);
		btn_RegistrarUsuario.setBorder(raised_border);
		btn_RegistrarUsuario.addActionListener(this);
		label_Wallpaper.add(btn_RegistrarUsuario);
		
		ImageIcon registrarUsuario = new ImageIcon("src/images/addUser.png");
		Icon iconoRegistrar = new ImageIcon(registrarUsuario.getImage().getScaledInstance(btn_RegistrarUsuario.getWidth()/2, 
				btn_RegistrarUsuario.getHeight()/2, Image.SCALE_DEFAULT));
		btn_RegistrarUsuario.setIcon(iconoRegistrar);		
		this.repaint();
		
		btn_GestionarUsuario = new JButton("");
		btn_GestionarUsuario.setBounds(260,60,120,100);
		btn_GestionarUsuario.setBorder(raised_border);
		btn_GestionarUsuario.addActionListener(this);
		label_Wallpaper.add(btn_GestionarUsuario);
		
		
		ImageIcon gestionarUsuario = new ImageIcon("src/images/informationuser.png");
		Icon iconoGestionar = new ImageIcon(gestionarUsuario.getImage().getScaledInstance(btn_GestionarUsuario.getWidth()/2, 
				btn_GestionarUsuario.getHeight()/2, Image.SCALE_DEFAULT));
		btn_GestionarUsuario.setIcon(iconoGestionar);		
		this.repaint();
		
		btn_Creatividad = new JButton("");
		btn_Creatividad.setBounds(460,60,120,100);
		btn_Creatividad.setBorder(raised_border);
		label_Wallpaper.add(btn_Creatividad);
		
		ImageIcon creatividad = new ImageIcon("src/images/creatividad.png");
		Icon iconoCreatividad = new ImageIcon(creatividad.getImage().getScaledInstance(btn_Creatividad.getWidth()/2, 
				btn_Creatividad.getHeight()/2, Image.SCALE_DEFAULT));
		btn_Creatividad.setIcon(iconoCreatividad);		
		this.repaint();
		
		btn_Capturista = new JButton("");
		btn_Capturista.setBounds(60,250,120,100);
		btn_Capturista.setBorder(raised_border);
		btn_Capturista.addActionListener(this);
		label_Wallpaper.add(btn_Capturista);
		
		ImageIcon capturista = new ImageIcon("src/images/capturista.png");
		Icon iconoCapturista = new ImageIcon(capturista.getImage().getScaledInstance(btn_Capturista.getWidth()/2, 
				btn_Capturista.getHeight()/2, Image.SCALE_DEFAULT));
		btn_Capturista.setIcon(iconoCapturista);		
		this.repaint();
		
		btn_Tecnico = new JButton("");
		btn_Tecnico.setBounds(260,250,120,100);
		btn_Tecnico.setBorder(raised_border);
		label_Wallpaper.add(btn_Tecnico);
		
		ImageIcon tecnico = new ImageIcon("src/images/tecnico.png");
		Icon iconoTecnico = new ImageIcon(tecnico.getImage().getScaledInstance(btn_Tecnico.getWidth()/2, 
				btn_Tecnico.getHeight()/2, Image.SCALE_DEFAULT));
		btn_Tecnico.setIcon(iconoTecnico);		
		this.repaint();
			
		btn_Acercade = new JButton("");
		btn_Acercade.setBounds(460,250,120,100);
		btn_Acercade.setBorder(raised_border);
		label_Wallpaper.add(btn_Acercade);
		
		ImageIcon acercade = new ImageIcon("src/images/geekipedia.png");
		Icon iconoAcercade = new ImageIcon(acercade.getImage().getScaledInstance(btn_Acercade.getWidth()/2,
				btn_Acercade.getHeight()/2, Image.SCALE_DEFAULT));
		btn_Acercade.setIcon(iconoAcercade);		
		this.repaint();
		
		
		try {
			
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select nombre_usuario from usuarios where username = '" + user + "'");
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				
				nombre_usuario = rs.getString("nombre_usuario");
				label_NombreUsuario.setText("Bienvenido " + nombre_usuario);
				
			}
		}catch(SQLException e) {
			System.err.println(" error en la conexion desde interfaz administrador");
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == btn_RegistrarUsuario) {
			
			new RegistrarUsuario().setVisible(true);
			
		}
		if(arg0.getSource() == btn_GestionarUsuario) {
			
			new GestionarUsuarios().setVisible(true);
        }
		if(arg0.getSource() == btn_Capturista) {
			
			new Capturista().setVisible(true);
		}
	}
	
}
