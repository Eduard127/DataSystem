package ventanas;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import clases.Conexion;

public class InformacionCliente extends JFrame implements ActionListener{

	String user;
	int ID_cliente_update;
	public static int ID_equipo;
	
	private JLabel label_Wallpaper, label1, lb_nombre, lb_email, lb_telefono,lb_direccion,lb_footer, lb_ultimamodificacion;
	private JTextField txt_nombre, txt_email, txt_telefono, txt_direccion, txt_ultimamodificacion;
	private JButton btn1,btn2,btn3;
	private JScrollPane scrollpane;
	private JTable tabla;
	
	DefaultTableModel model = new DefaultTableModel();
		
	
	public InformacionCliente() {
		
		
		user = Login.user;
		ID_cliente_update = GestionarCliente.ID_cliente_update;
		
		setLayout(null);
		setBounds(0,0,555,400);
		//setSize(450,600);
		setResizable(false);
		setTitle("informacion del cliente / sesion de " + user);
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
		
		label1 = new JLabel("Informacion del Cliente");
		label1.setBounds(0,5,150,40);
		label_Wallpaper.add(label1);
		
		lb_nombre = new JLabel("Nombre");
		lb_nombre.setBounds(10,60,150,20);
		label_Wallpaper.add(lb_nombre);
		
		lb_email = new JLabel("Email");
		lb_email.setBounds(10,110,150,20);
		label_Wallpaper.add(lb_email);
		
		lb_telefono = new JLabel("Telefono");
		lb_telefono.setBounds(10,160,150,20);
		label_Wallpaper.add(lb_telefono);
		
		lb_direccion = new JLabel("Direccion");
		lb_direccion.setBounds(10,210,150,20);
		label_Wallpaper.add(lb_direccion);
		
		lb_ultimamodificacion = new JLabel("Ultima Modificacion");
		lb_ultimamodificacion.setBounds(10,260,150,20);
		label_Wallpaper.add(lb_ultimamodificacion);
		
		
		
		txt_nombre = new JTextField();
		txt_nombre.setBounds(10,80,150,20);
		label_Wallpaper.add(txt_nombre);
		
		txt_email = new JTextField();
		txt_email.setBounds(10,130,150,20);
		label_Wallpaper.add(txt_email);
		
		txt_telefono = new JTextField();
		txt_telefono.setBounds(10,180,150,20);
		label_Wallpaper.add(txt_telefono);
		
		txt_direccion = new JTextField();
		txt_direccion.setBounds(10,230,150,20);
		label_Wallpaper.add(txt_direccion);
		
		txt_ultimamodificacion = new JTextField();
		txt_ultimamodificacion.setBounds(10,280,150,20);
		label_Wallpaper.add(txt_ultimamodificacion);
		
		
		
		btn1 = new JButton("Registrar equipo");
		btn1.setBounds(200,250,200,30);
		btn1.addActionListener(this);
		label_Wallpaper.add(btn1);
				
		btn2 = new JButton("Actualizar cliente");
		btn2.setBounds(200,300,200,30);
		btn2.addActionListener(this);
		label_Wallpaper.add(btn2);
		
		btn3 = new JButton();
		btn3.setBounds(440,240,100,100);
		btn3.addActionListener(this);
		label_Wallpaper.add(btn3);
		
		ImageIcon btn3_imagen = new ImageIcon("src/images/impresora.png");
		Icon btn3_icon = new ImageIcon(btn3_imagen.getImage().getScaledInstance(btn3.getWidth()/2, 
				btn3.getHeight()/2, Image.SCALE_DEFAULT));
		btn3.setIcon(btn3_icon);
		this.repaint();
		
		
		scrollpane = new JScrollPane();
		scrollpane.setBounds(200,80,340,150);
		label_Wallpaper.add(scrollpane);
		
		tabla = new JTable(model);
		tabla.setBounds(0,100,400,130);
		
			model.addColumn("Id equipo");
			model.addColumn("Tipo de equipo");
			model.addColumn("Marca");
			model.addColumn("Estatus");
			
		scrollpane.setViewportView(tabla);
		
		lb_footer = new JLabel("La Geekipedia de ernesto");
		lb_footer.setBounds(220,370,200,40);
		label_Wallpaper.add(lb_footer);
		
		try {
			
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select * from clientes where id_cliente = '" + ID_cliente_update + "'");
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				
						
				txt_nombre.setText(rs.getString("nombre_cliente"));
				txt_email.setText(rs.getString("email_cliente"));
				txt_telefono.setText(rs.getString("tel_cliente"));
				txt_direccion.setText(rs.getString("dir_cliente"));
				txt_ultimamodificacion.setText(rs.getString("ultima_modificacion"));
									
			}
			cn.close();
			
		}catch(SQLException e) {
			System.err.println("Error en pedir la informcion dl cliente " + e);
		}
		
		try {
			
			Connection cn1 = Conexion.conectar();
			PreparedStatement pst1 = cn1.prepareStatement(
					"select id_equipo, tipo_equipo, marca, status from equipos where id_cliente = '" + ID_cliente_update + "'");//en la base de datos se escribio sin e
			
			ResultSet rs1 = pst1.executeQuery();
			
			while(rs1.next()) {
				Object[] fila = new Object[4];
				
				for(int i = 0; i < 4; i++) {
					fila[i] = rs1.getObject(i + 1);
			}
			
			model.addRow(fila);
			
		}
		cn1.close();
			
			
		}catch(Exception e){
			System.err.println("Error llenando la tabla " + e);
		}
		
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int fila_point = tabla.rowAtPoint(e.getPoint()); 
				int column_point = 0;
				
				if(fila_point > -1) {
					ID_equipo = (int)model.getValueAt(fila_point, column_point);
					JOptionPane.showMessageDialog(null, "el id del equipo es " + ID_equipo);
					new InformacionEquipo().setVisible(true);
					
				}
			}
		});
	}
	


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == btn1) {
			
			new RegistrarEquipo().setVisible(true);
		}
		if(arg0.getSource() == btn2) {
			
			int validacion = 0;
			String nombre,email,telefono,direccion,ultimamod;
			
			nombre = txt_nombre.getText().trim();
			email = txt_email.getText().trim();
			telefono = txt_telefono.getText().trim();
			direccion = txt_direccion.getText().trim();
			ultimamod = txt_ultimamodificacion.getText().trim();
			
			if(nombre.equals("")) {
				txt_nombre.setBackground(Color.RED);
				validacion++;
			}
			if(email.equals("")) {
				txt_email.setBackground(Color.RED);
				validacion++;
			}
			if(telefono.equals("")) {
				txt_telefono.setBackground(Color.RED);
				validacion++;
			}
			if(direccion.equals("")) {
				txt_direccion.setBackground(Color.RED);
				validacion++;
			}
			if(ultimamod.equals("")) {
				txt_ultimamodificacion.setBackground(Color.RED);
				txt_ultimamodificacion.setText(user);
				validacion++;
			}
			
			if(validacion == 0) {
				

				try {
				
				Connection cn1 = Conexion.conectar();
				PreparedStatement pst1 = cn1.prepareStatement(""
						+ "update clientes set nombre_cliente=?, email_cliente=?, tel_cliente=?, dir_cliente=?, ultima_modificacion=? where id_cliente = '" + ID_cliente_update + "'");

				pst1.setString(1, nombre);
				pst1.setString(2, email);
				pst1.setString(3, telefono);
				pst1.setString(4, direccion);
				pst1.setString(5, user);
				
				pst1.executeUpdate();
				cn1.close();
				
				Limpiar();
				
				txt_nombre.setBackground(Color.GREEN);
				txt_email.setBackground(Color.GREEN);
				txt_telefono.setBackground(Color.GREEN);
				txt_direccion.setBackground(Color.GREEN);		
				
				JOptionPane.showMessageDialog(null, "Actualizacion correcta");
				this.dispose();
				
			}catch(SQLException e) {
				System.err.println("error en upgradear la base de datos clientes " + e);
		  }
				
		}else {
			JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
		}
			
			
		}
		if(arg0.getSource() == btn3) {  // Boton 3 de imprimir en PDF
			
			Document documento = new Document();  // se crea el objeto Document de tipo Document
			
			try {
				
				String ruta = System.getProperty("user.home");
				PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/" + txt_nombre.getText().trim() + ".pdf"));// estamos generando la ruta dnde queremos guardar el archivo y posteriormente le damos un nombre
				
				com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/images/BannerPDF.jpg"); // se crea un objeto de tipo Image pero de otra libreria diferente a la de awt.image, por eso toca importarla directamente desde aqui y se le da el nombre header
				header.scaleToFit(650, 1000);
				header.setAlignment(Chunk.ALIGN_CENTER);
				
				Paragraph parrafo = new Paragraph();  //Se crea un objeto de tipo paragraph
				parrafo.setAlignment(Paragraph.ALIGN_CENTER);
				parrafo.add("informacion del cliente \n \n");
				parrafo.setFont(FontFactory.getFont("Tahoma",14,Font.BOLD,BaseColor.DARK_GRAY));
				
				documento.open();             // se abre el documento y se agrgan los objetos anteriormente creados, el heador y el parrafo
				documento.add(header);
				documento.add(parrafo);
				
				PdfPTable tablaCliente = new PdfPTable(5); //se crea la tabla clientes
				
				tablaCliente.addCell("ID");
				tablaCliente.addCell("Nombre");
				tablaCliente.addCell("Email");
				tablaCliente.addCell("Telefono");
				tablaCliente.addCell("Direccion");
				
				try {
					
					Connection cn = Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement("select * from clientes where id_cliente = '" + ID_cliente_update + "'");
					
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()) {
						do {
							
							tablaCliente.addCell(rs.getString(1));
							tablaCliente.addCell(rs.getString(2));
							tablaCliente.addCell(rs.getString(3));
							tablaCliente.addCell(rs.getString(4));
							tablaCliente.addCell(rs.getString(5));
							
						}while(rs.next());
						
						documento.add(tablaCliente);
					}
					
					Paragraph parrafo2 = new Paragraph();
					parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
					parrafo2.add("\n \n  Equipos Registrados. \n \n");
					parrafo2.setFont(FontFactory.getFont("Tahoma",14,Font.BOLD,BaseColor.DARK_GRAY));
					
					documento.add(parrafo2);
					
					PdfPTable tablaEquipos = new PdfPTable(4);
					
					tablaEquipos.addCell("Id Equipo");
					tablaEquipos.addCell("Tipo");
					tablaEquipos.addCell("Marca");
					tablaEquipos.addCell("Estatus");
					
					try {
						
						Connection cn2 = Conexion.conectar();
						PreparedStatement pst2 = cn2.prepareStatement("select id_equipo, tipo_equipo, marca, status from equipos where id_cliente = '" + ID_cliente_update + "'");
						
						ResultSet rs2 = pst2.executeQuery();
						
						if(rs2.next()) {
							do {
								
								tablaEquipos.addCell(rs2.getString(1));
								tablaEquipos.addCell(rs2.getString(2));
								tablaEquipos.addCell(rs2.getString(3));
								tablaEquipos.addCell(rs2.getString(4));
								
							}while(rs2.next());
							
							documento.add(tablaEquipos);
						}
						
					}catch(SQLException e) {
						System.err.print("Error al cargar equipos " + e);
					}
					
				}catch(SQLException e) {
					
					System.err.print("Error al obtener los datos del cliente e la tabla " + e);
				}
				
				documento.close();
				JOptionPane.showMessageDialog(null, "reporte Creado correctamente");
				
			}catch(DocumentException | IOException e) {
				System.err.print("error en pdf o ruta de imagen " + e);
				JOptionPane.showMessageDialog(null, "Error al generar Pdf, Contacte al administrador");
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
