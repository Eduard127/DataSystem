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
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

import clases.Conexion;

public class RegistrarCliente extends JFrame implements ActionListener{

	String user;
	
	private JLabel label_titulo, label_Wallpaper, label_nombre, label_email, label_telefono, label_direccion, label6;
	private JTextField txt_nombre, txt_email, txt_telefono, txt_direccion;
	private JButton btn1;
	
	
	public RegistrarCliente() {
		
		user = Login.user;
		
		setLayout(null);
		setBounds(0,0,530,350);
		//setSize(450,600);
		setResizable(false);
		setTitle("Administrador - sesion de ");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//permite que al cerrar el programa no se siga ejecutando en segundo plano
		setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
	
		Border raised_border = new SoftBevelBorder(SoftBevelBorder.RAISED);
		
		label_Wallpaper = new JLabel("");
		label_Wallpaper.setBounds(0,0,530,350);
		add(label_Wallpaper);
		
		ImageIcon WallPaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
		Icon icono = new ImageIcon(WallPaper.getImage().getScaledInstance(label_Wallpaper.getWidth(),
				label_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		label_Wallpaper.setIcon(icono);
		this.repaint();
		
		label_titulo = new JLabel("Registro de Clientes");
		label_titulo.setBounds(210,10,100,20);
		label_Wallpaper.add(label_titulo);
		
		label_nombre = new JLabel("Nombre");
		label_nombre.setBounds(10,50,100,20);
		label_Wallpaper.add(label_nombre);
		
		label_email = new JLabel("Email");
		label_email.setBounds(10,110,100,20);
		label_Wallpaper.add(label_email);
		
		label_telefono = new JLabel("Telefono");
		label_telefono.setBounds(10,170,100,20);
		label_Wallpaper.add(label_telefono);
		
		label_direccion = new JLabel("Direccion");
		label_direccion.setBounds(10,230,100,20);
		label_Wallpaper.add(label_direccion);
		
		txt_nombre = new JTextField();
		txt_nombre.setBounds(10,75,200,20);
		label_Wallpaper.add(txt_nombre);
		
		txt_email = new JTextField();
		txt_email.setBounds(10,135,200,20);
		label_Wallpaper.add(txt_email);
		
		txt_telefono = new JTextField();
		txt_telefono.setBounds(10,195,200,20);
		label_Wallpaper.add(txt_telefono);
		
		txt_direccion = new JTextField();
		txt_direccion.setBounds(10,255,200,20);
		label_Wallpaper.add(txt_direccion);
		
		btn1 = new JButton();
		btn1.setBounds(310,120,100,100);
		btn1.addActionListener(this);
		label_Wallpaper.add(btn1);
		
		ImageIcon btnimagen = new ImageIcon("src/images/add.png");
		Icon btn1icono = new ImageIcon(btnimagen.getImage().getScaledInstance(btn1.getWidth()/2,
				btn1.getHeight()/2, Image.SCALE_DEFAULT));
		btn1.setIcon(btn1icono);
		

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == btn1) {
			
			String nombre, email, telefono, direccion;
			int validacion = 0;
			
			nombre = txt_nombre.getText().trim();
			email = txt_email.getText().trim();
			telefono = txt_telefono.getText().trim();
			direccion = txt_direccion.getText().trim();
			
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
			if(direccion.equals("")) {
				txt_direccion.setBackground(new Color(200,0,0));
				validacion++;
			}
			if(validacion == 0) {
				
				
				try {
					Connection cn = Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement("insert into clientes values(?,?,?,?,?,?)");
					
					pst.setInt(1, 0);// el id
					
					pst.setString(2, nombre);
					pst.setString(3, email);
					pst.setString(4, telefono);
					pst.setString(5, direccion);
					pst.setString(6, user);
					
					pst.executeUpdate();
					cn.close();
					
					Limpiar();
					
					txt_nombre.setBackground(new Color(0,200,0));
					txt_email.setBackground(new Color(0,200,0));
					txt_telefono.setBackground(new Color(0,200,0));
					txt_direccion.setBackground(new Color(0,200,0));
					
					JOptionPane.showMessageDialog(null, "Cliente Registrado ");
					this.dispose();
				
					
				}catch(SQLException e) {
					System.err.println(" Error al registrar cliente " + e);
					JOptionPane.showMessageDialog(null, "Error al registrar el cliente en la base de datos");
				}
				
				
			}else {
				JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
			}
		}
	}
	
	public void Limpiar() {
		
		txt_nombre.setText("");
		txt_email.setText("");
		txt_telefono.setText("");
		txt_direccion.setText("");
	}
}
