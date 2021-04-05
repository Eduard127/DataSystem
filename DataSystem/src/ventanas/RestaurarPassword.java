package ventanas;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import clases.Conexion;

public class RestaurarPassword extends JFrame implements ActionListener{

	String user, user_update;
	
	private JLabel label_Wallpaper, label_titulo, label1, label2;
	private JPasswordField txt_pass1, txt_pass2;
	private JButton btn_restaurar;
	
	RestaurarPassword(){
		
		user = Login.user;
		user_update = GestionarUsuarios.user_update;
		
		setLayout(null);
		setBounds(0,0,360,260);
		//setSize(450,600);
		setResizable(false);
		setTitle("Restauracion de contrasenia para " + user_update);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//permite que al cerrar el programa no se siga ejecutando en segundo plano
		setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
		
		
		label_Wallpaper = new JLabel();
		label_Wallpaper.setBounds(0,0,360,260);
		add(label_Wallpaper);
		
		ImageIcon Wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
		Icon icono = new ImageIcon(Wallpaper.getImage().getScaledInstance(label_Wallpaper.getWidth(),
				label_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		label_Wallpaper.setIcon(icono);
		this.repaint();
		
		
		label_titulo = new JLabel("Cambio de Password");
		label_titulo.setBounds(130,10,200,40);
		label_Wallpaper.add(label_titulo);
		
		label1 = new JLabel("Nuevo Password");
		label1.setBounds(10,50,150,20);
		label_Wallpaper.add(label1);
		
		label2 = new JLabel("ingrese su contrasenia nuevamente");
		label2.setBounds(10,100,300,20);
		label_Wallpaper.add(label2);
		
		txt_pass1 = new JPasswordField();
		txt_pass1.setBounds(10,70,200,25);
		label_Wallpaper.add(txt_pass1);
		
		txt_pass2 = new JPasswordField();
		txt_pass2.setBounds(10,120,200,25);
		label_Wallpaper.add(txt_pass2);
		
		btn_restaurar = new JButton("Nuevo Password");
		btn_restaurar.setBounds(10,180,150,30);
		btn_restaurar.addActionListener(this);
		label_Wallpaper.add(btn_restaurar);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == btn_restaurar) {
			
			String pass1, pass2, password;
			
			
				pass1 = txt_pass1.getText().trim();
				pass2 = txt_pass2.getText().trim();
				
				if(!pass1.equals("") && !pass2.equals("")) {
					
					if(pass1.equals(pass2)) {						
					password = pass1;
			try {
				Connection cn = Conexion.conectar();
				PreparedStatement pst = cn.prepareStatement("update usuarios set password=? where username = '" + user_update + "'");
				
				pst.setString(1, password);
				
				pst.executeUpdate();
				cn.close();
				
				txt_pass1.setBackground(new Color(0,150,0));
				txt_pass2.setBackground(new Color(0,150,0));
				
				JOptionPane.showMessageDialog(null, "modificacion exitosa");
				this.dispose();
				
			}catch(SQLException e) {
				System.out.println("Error en restaurar password" + e);
			}
			}else {			
				txt_pass2.setBackground(new Color(200,0,0));
				JOptionPane.showMessageDialog(null, "Las contrase;as no coinciden");
			}
		  }else {
			  txt_pass1.setBackground(new Color(200,0,0));
			  txt_pass2.setBackground(new Color(200,0,0));
			  JOptionPane.showMessageDialog(null, "No se admiten contrase;as vacias");
		  }
       }
	
  }
}