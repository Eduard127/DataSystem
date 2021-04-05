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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import clases.Conexion;

public class InformacionEquipo extends JFrame implements ActionListener{

	int ID_cliente_update,ID_equipo;
	String user = "",nom_cliente = "";
	
	private JLabel label_Wallpaper,label_titulo,label_nombrecliente,label_tipoequipo,label_marca,label_modelo,label7,label8,label9,label10,label11,label12;
	private JTextField txt_nombrecliente,txt_modelo,txt_numserie,txt_ultimamod,txt_fechaingreso;
	private JComboBox cmb_tipo,cmb_marca,cmb_estatus;
	private JScrollPane scrollpane1,scrollpane2;
	private JTextPane panel1,panel2;
	private JButton btn1;
	
	public InformacionEquipo() {
		
		user= Login.user;
		ID_equipo = InformacionCliente.ID_equipo;
		ID_cliente_update = GestionarCliente.ID_cliente_update;
		
		setLayout(null);
		setBounds(0,0,670,530);
		//setSize(450,600);
		setResizable(false);
		setTitle("Equipo del cliente " + nom_cliente);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
		
		label_Wallpaper = new JLabel();
		label_Wallpaper.setBounds(0,0,670,530);
		add(label_Wallpaper);
		
		ImageIcon Wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
		Icon icono_Wallpaper = new ImageIcon(Wallpaper.getImage().getScaledInstance(label_Wallpaper.getWidth(),
				label_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		label_Wallpaper.setIcon(icono_Wallpaper);
		this.repaint();
		
		label_titulo = new JLabel("Informacion de Equipo");
		label_titulo.setBounds(265,5,200,20);
		label_titulo.setForeground(new Color(255,255,255));
		label_Wallpaper.add(label_titulo);
		
		label_nombrecliente = new JLabel("Nombre del Cliente");
		label_nombrecliente.setBounds(5,60,200,20);
		label_nombrecliente.setForeground(new Color(255,255,255));
		label_nombrecliente.setEnabled(false);
		label_Wallpaper.add(label_nombrecliente);
		
		label_tipoequipo = new JLabel("Tipo de Equipo");
		label_tipoequipo.setBounds(5,120,200,20);
		label_tipoequipo.setForeground(new Color(255,255,255));
		label_Wallpaper.add(label_tipoequipo);
		
		label_marca = new JLabel("Marca");
		label_marca.setBounds(5,180,200,20);
		label_marca.setForeground(new Color(255,255,255));
		label_Wallpaper.add(label_marca);
		
		label_modelo = new JLabel("Modelo");
		label_modelo.setBounds(5,240,200,20);
		label_modelo.setForeground(new Color(255,255,255));
		label_Wallpaper.add(label_modelo);
		
		label7 = new JLabel("Num serie");
		label7.setBounds(5,300,200,20);
		label7.setForeground(new Color(255,255,255));
		label_Wallpaper.add(label7);
		
		label8 = new JLabel("ultima Modificacion por:");
		label8.setBounds(5,360,200,20);
		label8.setForeground(new Color(255,255,255));
		label_Wallpaper.add(label8);
		
		label9 = new JLabel("Fecha de ingreso");
		label9.setBounds(380,60,200,20);
		label9.setForeground(new Color(255,255,255));
		label_Wallpaper.add(label9);
		
		label10 = new JLabel("Estatus");
		label10.setBounds(530,60,200,20);
		label10.setForeground(new Color(255,255,255));
		label_Wallpaper.add(label10);
		
		label11 = new JLabel("Dano reportado y observaciones");
		label11.setBounds(380,120,200,20);
		label11.setForeground(new Color(255,255,255));
		label_Wallpaper.add(label11);
		
		label12 = new JLabel("Comentarios y actualizacion del tecnico");
		label12.setBounds(380,250,200,20);
		label12.setForeground(new Color(255,255,255));
		label_Wallpaper.add(label12);
		
		txt_nombrecliente = new JTextField();
		txt_nombrecliente.setBounds(5,80,200,20);
		label_Wallpaper.add(txt_nombrecliente);
		
		txt_modelo = new JTextField();
		txt_modelo.setBounds(5,260,200,20);
		label_Wallpaper.add(txt_modelo);
		
		txt_numserie = new JTextField();
		txt_numserie.setBounds(5,320,200,20);
		label_Wallpaper.add(txt_numserie);
		
		txt_ultimamod = new JTextField();
		txt_ultimamod.setBounds(5,380,200,20);
		label_Wallpaper.add(txt_ultimamod);
		
		txt_fechaingreso = new JTextField();
		txt_fechaingreso.setBounds(380,80,100,20);
		label_Wallpaper.add(txt_fechaingreso);
		
		cmb_tipo = new JComboBox();
		cmb_tipo.setBounds(5,140,100,20);
		label_Wallpaper.add(cmb_tipo);
		
		cmb_tipo.addItem("Desktop");
		cmb_tipo.addItem("Portatil");
		cmb_tipo.addItem("Impresora");
		cmb_tipo.addItem("Multifuncional");
		
		cmb_marca = new JComboBox();
		cmb_marca.setBounds(5,200,100,20);
		label_Wallpaper.add(cmb_marca);
		
		cmb_marca.addItem("Acer");
		cmb_marca.addItem("Hp");
		cmb_marca.addItem("apple");
		cmb_marca.addItem("Asus");
		cmb_marca.addItem("msi");
		cmb_marca.addItem("Dell");
		cmb_marca.addItem("Lenovo");
		
		cmb_estatus = new JComboBox();
		cmb_estatus.setBounds(530, 80, 100, 20);
		label_Wallpaper.add(cmb_estatus);
		
		cmb_estatus.addItem("Activo");
		cmb_estatus.addItem("Inactivo");
		
		scrollpane1 = new JScrollPane();
		scrollpane1.setBounds(380,140,250,100);
		label_Wallpaper.add(scrollpane1);
		
		panel1 = new JTextPane();
		//panel1.setBounds(0,140,250,300);
		scrollpane1.setViewportView(panel1); //en vez de agregarlo con un .add, toca hacerlo con el metodo setviewportview
		
		scrollpane2 = new JScrollPane();
		scrollpane2.setBounds(380,270,250,100);
		
		label_Wallpaper.add(scrollpane2);
		
		panel2 = new JTextPane();
		panel2.setEnabled(false);
		//panel2.setBounds(0,270,250,300);
		scrollpane2.setViewportView(panel2);
		
		btn1 = new JButton("Actualizar cliente");
		btn1.setBounds(480,380,150,25);
		btn1.addActionListener(this);
		label_Wallpaper.add(btn1);
		
		
		try {
			
			String nombre;
			
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select nombre_cliente from clientes where id_cliente = '" + ID_cliente_update + "'");
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				
				nom_cliente = rs.getString("nombre_cliente");
			}
						
		}catch(SQLException e){
			System.err.print("error al consultar el nombre del cliente " + e);
		}

		
		try {
			
			String nombre;
			
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select * from equipos where id_equipo = '" + ID_equipo + "'");
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				
				cmb_tipo.setSelectedItem(rs.getString("tipo_equipo"));
				cmb_marca.setSelectedItem(rs.getString("marca"));
				cmb_estatus.setSelectedItem(rs.getString("status"));
				
				txt_nombrecliente.setText(nom_cliente);
				txt_modelo.setText(rs.getString("modelo"));
				txt_numserie.setText(rs.getString("num_serie"));
				txt_ultimamod.setText(rs.getString("ultima_modificacion"));
				
				String dia = "",mes = "",anio = "";
						
				dia = rs.getString("dia_ingreso");
				mes = rs.getString("mes_ingreso");
				anio = rs.getString("annio_ingreso");
				
				txt_fechaingreso.setText(dia + "del " + mes + "del " + anio);
				
				panel1.setText(rs.getString("observaciones"));
				panel2.setText(rs.getString("comentarios_tecnicos"));
				label12.setText("Comentarios y actualizacion del tecnico " + rs.getString("revision_tecnica_de"));
				
			}
			
			cn.close();
			
		}catch(SQLException e){
			System.err.print("error al consultar la infomacion del equipo " + e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == btn1) {
			
			int validacion = 0;
			String tipo,marca,status,modelo,numserie,dia_ingreso,mes_ingreso,anio_ingreso,observaciones;
			
			tipo = cmb_tipo.getSelectedItem().toString();
			marca = cmb_marca.getSelectedItem().toString();
			status = cmb_estatus.getSelectedItem().toString();
			
			modelo = txt_modelo.getText().trim();
			numserie = txt_numserie.getText().trim();
			observaciones = panel1.getText();
			
			
			/*Calendar calendar = Calendar.getInstance();
			
			dia_ingreso = Integer.toString(calendar.get(Calendar.DATE));
			mes_ingreso = Integer.toString(calendar.get(Calendar.MONTH));
			anio_ingreso = Integer.toString(calendar.get(Calendar.YEAR));
			*/
			
			
			if(modelo.equals("")) {
				txt_modelo.setBackground(new Color(0,200,0));
				validacion++;
			}
			if(numserie.equals("")) {
				txt_numserie.setBackground(new Color(0,200,0));
				validacion++;
			}
			
			if(observaciones.equals("")) {
				panel1.setText("Sin observaciones");				
			}
						
			
			
			else if(validacion == 0) {
				
				try {
					
					Connection cn = Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement(""
							+ "update equipos set tipo_equipo=?, marca=?, modelo=?, num_serie=?, observaciones=?, status=?, ultima_modificacion=? where id_equipo = '" + ID_equipo + "'");
					
					pst.setString(1, tipo);
					pst.setString(2, marca);
					pst.setString(3, modelo);
					pst.setString(4, numserie);
					pst.setString(5, observaciones);
					pst.setString(6, status);
					pst.setString(7, user);
					
					pst.executeUpdate();
					cn.close();
					
					limpiar();
					
					this.dispose();
					
					JOptionPane.showMessageDialog(null, "Registro Exitoso");
					
				}catch(SQLException e) {
					
					JOptionPane.showMessageDialog(null, "Error en la base de datos Actualizar Equipo " + e);
					System.err.print("Error en la base de datos actualizar Equipo " + e);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
			}
		}
		
	}
	
	public void limpiar() {
		
		txt_modelo.setText("");
		txt_numserie.setText("");
		
	}
	
}
