package ventanas;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import clases.Conexion;




public class RegistrarEquipo extends JFrame implements ActionListener{

	String user;
	int ID_equipo, ID_cliente_update;
	
	private JLabel label_Wallpaper, label_titulo, label_nombrecliente, label_tipoequipo, label_marca, label_modelo, label_numserie, label_observaciones,
					label_footer;
	private JTextField txt_nombrecliente, txt_modelo, txt_numserie;
	private JButton btn1;
	private JTextPane txp_observaciones;
	private JComboBox cmb_tipo, cmb_marca;

	public RegistrarEquipo() {
		
		user = Login.user;
		ID_equipo = InformacionCliente.ID_equipo;
		ID_cliente_update = GestionarCliente.ID_cliente_update;
		
		setLayout(null);
		setBounds(0,0,600,400);
		//setSize(450,600);
		setResizable(false);
		setTitle("Registrar equipo / sesion de  " + user);
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
		
		label_titulo = new JLabel("Registrar Equipo");
		label_titulo.setBounds(200,5,200,40);
		label_Wallpaper.add(label_titulo);
		
		label_nombrecliente= new JLabel("Nombre Cliente");
		label_nombrecliente.setBounds(0,50,200,20);
		label_Wallpaper.add(label_nombrecliente);
		
		label_tipoequipo = new JLabel("Tipo Equipo");
		label_tipoequipo.setBounds(0,100,200,20);
		label_Wallpaper.add(label_tipoequipo);
		
		label_marca = new JLabel("Marca");
		label_marca.setBounds(0,160,200,20);
		label_Wallpaper.add(label_marca);
		
		label_modelo = new JLabel("Modelo");
		label_modelo.setBounds(0,210,200,20);
		label_Wallpaper.add(label_modelo);
		
		label_numserie = new JLabel("Num de Serie");
		label_numserie.setBounds(0,260,200,20);
		label_Wallpaper.add(label_numserie);
		
		label_observaciones = new JLabel("Observaciones");
		label_observaciones.setBounds(300,50,200,20);
		label_Wallpaper.add(label_observaciones);
		
		label_footer = new JLabel("La Geekipedia de ernesto");
		label_footer.setBounds(220,315,200,40);
		label_Wallpaper.add(label_footer);
		
		txt_nombrecliente = new JTextField();
		txt_nombrecliente.setBounds(0,70,200,20);
		txt_nombrecliente.enable(false);
		label_Wallpaper.add(txt_nombrecliente);
		
		txt_modelo = new JTextField();
		txt_modelo.setBounds(0,230,200,20);
		label_Wallpaper.add(txt_modelo);
		
		txt_numserie = new JTextField();
		txt_numserie.setBounds(0,280,200,20);
		label_Wallpaper.add(txt_numserie);
		
		cmb_tipo = new JComboBox();
		cmb_tipo.setBounds(5,130,100,20);
		label_Wallpaper.add(cmb_tipo);
		
		cmb_tipo.addItem("Desktop");
		cmb_tipo.addItem("Portatil");
		cmb_tipo.addItem("Impresora");
		cmb_tipo.addItem("Multifuncional");
		
		cmb_marca = new JComboBox();
		cmb_marca.setBounds(5,180,100,20);
		label_Wallpaper.add(cmb_marca);
		
		cmb_marca.addItem("Acer");
		cmb_marca.addItem("Hp");
		cmb_marca.addItem("apple");
		cmb_marca.addItem("Asus");
		cmb_marca.addItem("msi");
		cmb_marca.addItem("Dell");
		cmb_marca.addItem("Lenovo");
		
		txp_observaciones = new JTextPane();
		txp_observaciones.setBounds(300,70,250,150);
		label_Wallpaper.add(txp_observaciones);
		
		btn1 = new JButton("Agregar Equipo");
		btn1.setBounds(380,220,150,25);
		btn1.addActionListener(this);
		label_Wallpaper.add(btn1);
		
		
		try {
			
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select nombre_cliente from clientes where id_cliente = '" + ID_cliente_update + "'");
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
			txt_nombrecliente.setText(rs.getString("nombre_cliente"));
		}
			cn.close();
			
		}catch(SQLException e) {
			System.err.println("Error en la base de datos-pedir usuario " + e);
			JOptionPane.showMessageDialog(null, "error en la base de datos contacte el administrador");
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == btn1) {
			
			int marca_cmb,tipo_cmb,validacion= 0;
			String nombre,tipo_string = "",marca_string = "",modelo,numserie,dia_ingreso,mes_ingreso,anio_ingreso,estatus,observaciones;
			
			tipo_cmb = cmb_tipo.getSelectedIndex() + 1;
			marca_cmb = cmb_marca.getSelectedIndex() + 1;
			modelo = txt_modelo.getText().trim();
			numserie = txt_numserie.getText().trim();
			observaciones = txp_observaciones.getText();
			estatus= "Nuevo ingreso";
			
			Calendar calendar = Calendar.getInstance();
			
			dia_ingreso = Integer.toString(calendar.get(Calendar.DATE));
			mes_ingreso = Integer.toString(calendar.get(Calendar.MONTH));
			anio_ingreso = Integer.toString(calendar.get(Calendar.YEAR));
			
			
			if(modelo.equalsIgnoreCase("")) {
				txt_modelo.setBackground(Color.RED);
				validacion++;
			}
			if(numserie.equalsIgnoreCase("")) {
				txt_numserie.setBackground(Color.RED);
				validacion++;
			}
			if(modelo.equalsIgnoreCase("")) {
				txp_observaciones.setText("Sin Observaciones");
			}
			if(tipo_cmb == 1) {
				tipo_string = "Desktop";
			}
			else if(tipo_cmb == 2) {
				tipo_string = "Portatil";
			}
			else if(tipo_cmb == 3) {
				tipo_string = "Impresora";
			}
			else if(tipo_cmb == 4) {
				tipo_string = "Multifuncional";
			}
			if(marca_cmb == 1) {
				marca_string = "Acer";
			}
			else if(marca_cmb == 2) {
				marca_string = "Hp";
			}
			else if(marca_cmb == 3) {
				marca_string = "Apple";
			}
			else if(marca_cmb == 4) {
				marca_string = "Assus";
			}
			else if(marca_cmb == 5) {
				marca_string = "Msi";
			}
			else if(marca_cmb == 6) {
				marca_string = "Dell";
			}
			else if(marca_cmb == 7) {
				marca_string = "Lenovo";
			}
			if(validacion == 0) {
				
				try {
					
					Connection cn = Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement("insert into equipos values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					
					pst.setInt(1,0);
					pst.setInt(2, ID_cliente_update);
					
					pst.setString(3, tipo_string);
					pst.setString(4, marca_string);
					pst.setString(5, modelo);
					pst.setString(6, numserie);
					pst.setString(7, dia_ingreso);
					pst.setString(8, mes_ingreso);
					pst.setString(9, anio_ingreso);
					pst.setString(10, observaciones);
					pst.setString(11, estatus);
					pst.setString(12, user);
					pst.setString(13, "");
					pst.setString(14, "");
					
					pst.executeUpdate();
					cn.close();
					
					txt_nombrecliente.setBackground(Color.GREEN);
					txt_modelo.setBackground(Color.GREEN);
					txt_numserie.setBackground(Color.GREEN);
					
					JOptionPane.showMessageDialog(null, "Registro exitoso");
					this.dispose();
					
				}catch(Exception e) {
					System.err.println("Error en la validacion de campos" + e);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
			}
			
		}
	}
}
