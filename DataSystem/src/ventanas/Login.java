package ventanas;

import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

import clases.Conexion;

public class Login extends JFrame implements ActionListener{

	public static String user =""; //para que se puedan comunicar entre ventanas
	String pass = "";
	
	private JLabel label_Wallpaper, label_logo, label_footer;
	private JTextField txt_nombre;
	private JPasswordField txt_password;
	private JButton btn_acceder;
	
	public Login() {
		
		setLayout(null);
		setBounds(0,0,400,600);
		//setSize(450,600);
		setResizable(false);
		setTitle("Acceso al Sistema");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
		
		Border raised_border = new SoftBevelBorder(SoftBevelBorder.RAISED); //se llama este objeto que es el que le da el suavisado a los botones
		
		label_Wallpaper = new JLabel("");
		label_Wallpaper.setBounds(0,0,450,600);
		add(label_Wallpaper);
		
		label_logo = new JLabel("");
		label_logo.setBounds(60,40,250,250);
		label_Wallpaper.add(label_logo);  //agregar el JLabel logo a Jlabel wallpaper para que quede encima del otro
		
		label_footer = new JLabel("Creado por jorge Silva");
		label_footer.setBounds(60,545,280,30);
		label_Wallpaper.add(label_footer);
		
		txt_nombre = new JTextField("");
		txt_nombre.setBounds(90,350,200,30);
		txt_nombre.setForeground(new Color(255,255,255)); //color de la fuente
		txt_nombre.setBorder(raised_border); //suavisa los bordes nosotro creamos el objeto
		txt_nombre.setBackground(new Color(153,153,255)); //le dal el color de fondo al label
		label_Wallpaper.add(txt_nombre);
		
		
		txt_password = new JPasswordField("");
		txt_password.setBounds(90,400,200,30);
		txt_password.setForeground(new Color(255,255,255));
		txt_password.setBorder(raised_border);
		txt_password.setBackground(new Color(153,153,255));
		label_Wallpaper.add(txt_password);
		
		btn_acceder = new JButton("Acceder");
		btn_acceder.setBounds(90,450,200,30);
		btn_acceder.setForeground(new Color(255,255,255));
		btn_acceder.setBorder(raised_border);
		btn_acceder.setBackground(new Color(153,153,255));
		label_Wallpaper.add(btn_acceder);
		btn_acceder.addActionListener(this);
		
		
		ImageIcon WallPaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
		Icon icono = new ImageIcon(WallPaper.getImage().getScaledInstance(label_Wallpaper.getWidth(),
				label_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		label_Wallpaper.setIcon(icono);
		this.repaint();
		
		ImageIcon WallPaper_logo = new ImageIcon("src/images/DS.png");
		Icon icono_logo = new ImageIcon(WallPaper_logo.getImage().getScaledInstance(label_logo.getWidth(),
				label_logo.getHeight(), Image.SCALE_DEFAULT));
		label_logo.setIcon(icono_logo);
		this.repaint();
		
	}
	
	public static void main(String args[]) {
	
		Login login = new Login();
		login.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btn_acceder) {
			
			user = txt_nombre.getText().trim();
			pass = txt_password.getText().trim();
			
			if(!user.equals("") || !pass.equals("")) {
				
				try {
					
					Connection cn = Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement(""
							+ " select tipo_nivel, estatus from usuarios where username = '" + user 
							+ "' and password = '" + pass + "'" );
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()) {
						
						String tipo_nivel = rs.getString("tipo_nivel");
						String estatus = rs.getString("estatus");
						
						if(tipo_nivel.equalsIgnoreCase("Administrador") && estatus.equalsIgnoreCase("Activo")) {
							dispose();
							new Administrador().setVisible(true);
							
						}else if(tipo_nivel.equalsIgnoreCase("Capturista") && estatus.equalsIgnoreCase("Activo")){
							dispose();
							new Capturista().setVisible(true);
							
						}else if(tipo_nivel.equalsIgnoreCase("Tecnico") && estatus.equalsIgnoreCase("Activo")) {
							dispose();
							new Tecnico().setVisible(true);
							
						}
						
						
					}else {
						JOptionPane.showMessageDialog(null, "Datos de acceso incorrectos");
						txt_nombre.setText("");
						txt_password.setText("");
					}
							
				}catch(SQLException i) {
					System.err.println("error en el boton acceder " + i);
					JOptionPane.showMessageDialog(null, "Error al iniciar secion, Contacte a el Administrador");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");		
				
			}
		}
	}
}
