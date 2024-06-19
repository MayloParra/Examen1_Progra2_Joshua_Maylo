package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AdminPanel extends JFrame {

    Color colorPanel = new Color(234, 235, 237);
    String[] nombresColumnas = {"Cedula", "nombre1", "Nombre2", "apellido1", "apellido2", "login", "contraseña"};
    DefaultTableModel modelo = new DefaultTableModel(nombresColumnas, 0);
    JTable tabla = new JTable(modelo);
    JButton Agregar = new JButton("Agregar");
    JButton Actualizar = new JButton("Actualizar");
    JButton Eliminar = new JButton("Eliminar");
    JButton Regresar = new JButton("Regresar");
    JLabel Login = new JLabel("Usuario: ");
    JLabel Contraseña = new JLabel("Contraseña: ");
    JLabel Nombre1 = new JLabel("Primer nombre: ");
    JLabel Nombre2 = new JLabel("Segundo nombre: ");
    JLabel Apellido1 = new JLabel("Primer apellido: ");
    JLabel Apellido2 = new JLabel("Segundo apellido: ");
    JLabel Cedula = new JLabel("Cédula: ");
    JTextField TUsuario = new JTextField();
    JTextField TContraseña = new JTextField();
    JTextField TNombre1 = new JTextField();
    JTextField TNombre2 = new JTextField();
    JTextField TApellido1 = new JTextField();
    JTextField TApellido2 = new JTextField();
    JTextField TCedula = new JTextField();
    JPanel Datos = new JPanel();
    JPanel Tabla = new JPanel();
    JPanel Principal = new PanelConFondo("/Imagenes/Fondo3.jpg"); // Mantener la imagen de fondo
    //Atributos de la clase
    public AdminPanel() {
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(Principal);
        Detalles();
        eventos();
        cargarDatosDesdeBaseDeDatos();
    } // Fin del constructor

    public void Detalles() {
        Datos.setLayout(null);
        Tabla.setLayout(null);
        Principal.setLayout(null);
        Principal.setBackground(Color.red);
        Principal.add(Datos);
        Principal.add(Tabla);

        Datos.setBounds(15, 0, 960, 330);
        Datos.setBackground(colorPanel);

        Datos.add(Cedula);
        Datos.add(TCedula);
        Datos.add(Nombre1);
        Datos.add(TNombre1);
        Datos.add(Nombre2);
        Datos.add(TNombre2);
        Datos.add(Apellido1);
        Datos.add(TApellido1);
        Datos.add(Apellido2);
        Datos.add(TApellido2);
        Datos.add(Login);
        Datos.add(TUsuario);
        Datos.add(Contraseña);
        Datos.add(TContraseña);

        Datos.add(Agregar);
        Datos.add(Actualizar);
        Datos.add(Eliminar);
        Datos.add(Regresar);

        JScrollPane scrollPane = new JScrollPane(tabla);
        Tabla.add(scrollPane);
        Tabla.setBounds(15, 360, 960, 300);
        Tabla.setBackground(colorPanel);
        scrollPane.setBounds(0, 0, 960, 250);

        Cedula.setBounds(10, 10, 100, 30);
        TCedula.setBounds(140, 10, 200, 30);
        TCedula.setBorder(new Borde(15));
        TCedula.setOpaque(false);
        Nombre1.setBounds(10, 50, 100, 30);
        TNombre1.setBounds(140, 50, 200, 30);
        TNombre1.setBorder(new Borde(15));
        TNombre1.setOpaque(false);
        Nombre2.setBounds(10, 90, 120, 30);
        TNombre2.setBounds(140, 90, 200, 30);
        TNombre2.setBorder(new Borde(15));
        TNombre2.setOpaque(false);
        Apellido1.setBounds(10, 130, 100, 30);
        TApellido1.setBounds(140, 130, 200, 30);
        TApellido1.setBorder(new Borde(15));
        TApellido1.setOpaque(false);
        Apellido2.setBounds(10, 170, 120, 30);
        TApellido2.setBounds(140, 170, 200, 30);
        TApellido2.setBorder(new Borde(15));
        TApellido2.setOpaque(false);
        Login.setBounds(10, 210, 100, 30);
        TUsuario.setBounds(140, 210, 200, 30);
        TUsuario.setBorder(new Borde(15));
        TUsuario.setOpaque(false);
        Contraseña.setBounds(10, 250, 100, 30);
        TContraseña.setBounds(140, 250, 200, 30);
        TContraseña.setBorder(new Borde(15));
        TContraseña.setOpaque(false);

        Agregar.setBounds(400, 290, 100, 30);
        Agregar.setContentAreaFilled(false);
        Agregar.setBackground(Color.white);
        Actualizar.setBounds(515, 290, 100, 30);
        Actualizar.setContentAreaFilled(false);
        Actualizar.setBackground(Color.white);
        Eliminar.setBounds(630, 290, 100, 30);
        Eliminar.setContentAreaFilled(false);
        Eliminar.setBackground(Color.white);
        Regresar.setBounds(745, 290, 100, 30); 
        Regresar.setContentAreaFilled(false);
        Regresar.setBackground(Color.white);
    } // fin del método Detalles

    public void cargarDatosDesdeBaseDeDatos() {
        try {
            //Establecer conexión
            POO.Conexion objetoconexion = new POO.Conexion();
            Connection con;
            con = (Connection) objetoconexion.EstablecerConexion();

            //Ejecutar consulta SQL
            String consulta = "SELECT Cedula, nombre1, nombre2, apellido1, apellido2, Login, clave FROM usuarios";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            //Llenar el DefaultTableModel
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // Limpiar el modelo para evitar duplicados

            while (rs.next()) {
                Object[] fila = new Object[7]; // Ajusta el tamaño del arreglo según el número de columnas
                fila[0] = rs.getString("Cedula");
                fila[1] = rs.getString("nombre1");
                fila[2] = rs.getString("nombre2");
                fila[3] = rs.getString("apellido1");
                fila[4] = rs.getString("apellido2");
                fila[5] = rs.getString("Login");
                fila[6] = rs.getString("clave");
                modelo.addRow(fila);
            }

            //Asignar el modelo a la JTable
            tabla.setModel(modelo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }// fin del método cargarDatosDesdeBaseDeDatos

    public void eventos() {
        Agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AgregarUsuario(TCedula, TNombre1, TNombre2, TApellido1, TApellido2, TUsuario, TContraseña);

                String[] datos = {
                    TCedula.getText(),
                    TNombre1.getText(),
                    TNombre2.getText(),
                    TApellido1.getText(),
                    TApellido2.getText(),
                    TUsuario.getText(),
                    TContraseña.getText()
                };
                modelo.addRow(datos);
                clearTextFields();
            }
        });

        Actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActualizarUsuario(TCedula, TNombre1, TNombre2, TApellido1, TApellido2, TUsuario, TContraseña);

                int selectedRow = tabla.getSelectedRow();

                ActualizarUsuario(TCedula, TNombre1, TNombre2, TApellido1, TApellido2, TUsuario, TContraseña);
                if (selectedRow >= 0) {
                    modelo.setValueAt(TCedula.getText(), selectedRow, 0);
                    modelo.setValueAt(TNombre1.getText(), selectedRow, 1);
                    modelo.setValueAt(TNombre2.getText(), selectedRow, 2);
                    modelo.setValueAt(TApellido1.getText(), selectedRow, 3);
                    modelo.setValueAt(TApellido2.getText(), selectedRow, 4);
                    modelo.setValueAt(TUsuario.getText(), selectedRow, 5);
                    modelo.setValueAt(TContraseña.getText(), selectedRow, 6);
                    clearTextFields();
                }
            }
        });

        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                

                EliminarUsuario(TCedula);


                int selectedRow = tabla.getSelectedRow();
                if (selectedRow >= 0) {
                    modelo.removeRow(selectedRow);
                    clearTextFields();
                }
            }
        });

        Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login ventana = new Login();
                ventana.setBounds(0, 0, 800, 600);
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
            }
        });

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tabla.getSelectedRow();
                if (selectedRow >= 0) {
                    TCedula.setText(modelo.getValueAt(selectedRow, 0).toString());
                    TNombre1.setText(modelo.getValueAt(selectedRow, 1).toString());
                    TNombre2.setText(modelo.getValueAt(selectedRow, 2).toString());
                    TApellido1.setText(modelo.getValueAt(selectedRow, 3).toString());
                    TApellido2.setText(modelo.getValueAt(selectedRow, 4).toString());
                    TUsuario.setText(modelo.getValueAt(selectedRow, 5).toString());
                    TContraseña.setText(modelo.getValueAt(selectedRow, 6).toString());
                }
            }
        });

    } // fin del método eventos

    private void clearTextFields() {
        TUsuario.setText("");
        TContraseña.setText("");
        TNombre1.setText("");
        TNombre2.setText("");
        TApellido1.setText("");
        TApellido2.setText("");
        TCedula.setText("");
    } // fin del método clearTextFields

    public void AgregarUsuario(JTextField TCedula, JTextField TNombre1, JTextField TNombre2, JTextField TApellido1, JTextField TApellido2, JTextField TUsuario, JTextField TContraseña) {
        
        POO.Conexion objetoconexion = new POO.Conexion();
        String consulta = "insert into usuarios (cedula, nombre1, nombre2, apellido1, apellido2, login, clave) values(?,?,?,?,?,?,?);";

        try {

            CallableStatement call = (CallableStatement) objetoconexion.EstablecerConexion().prepareCall(consulta);
            call.setString(1, TCedula.getText());
            call.setString(2, TNombre1.getText());
            call.setString(3, TNombre2.getText());
            call.setString(4, TApellido1.getText());
            call.setString(5, TApellido2.getText());
            call.setString(6, TUsuario.getText());
            call.setString(7, TContraseña.getText());
            call.execute();

            JOptionPane.showMessageDialog(null, "Usuario agregado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar usuario" + e.toString());
        }
}// fin del método AgregarUsuario



    public void Seleccionar( JTable tabla, JTextField TCedula, JTextField TNombre1, JTextField TNombre2, JTextField TApellido1, JTextField TApellido2, JTextField TUsuario, JTextField TContraseña) {

        int selectedRow = tabla.getSelectedRow();

        if (selectedRow>=0){
            TCedula.setText(tabla.getValueAt(selectedRow, 0).toString());
            TNombre1.setText(tabla.getValueAt(selectedRow, 1).toString());
            TNombre2.setText(tabla.getValueAt(selectedRow, 2).toString());
            TApellido1.setText(tabla.getValueAt(selectedRow, 3).toString());
            TApellido2.setText(tabla.getValueAt(selectedRow, 4).toString());
            TUsuario.setText(tabla.getValueAt(selectedRow, 5).toString());
            TContraseña.setText(tabla.getValueAt(selectedRow, 6).toString());

        }
    }// fin del método Seleccionar



    public void ActualizarUsuario(JTextField TCedula, JTextField TNombre1, JTextField TNombre2, JTextField TApellido1, JTextField TApellido2, JTextField TUsuario, JTextField TContraseña) {
        
        POO.Conexion objetoconexion = new POO.Conexion();
        String consulta = "UPDATE usuarios SET cedula=?, nombre1=?, nombre2=?, apellido1=?, apellido2=?, login=?, clave=? WHERE cedula=?;";

        try {

            CallableStatement call = (CallableStatement) objetoconexion.EstablecerConexion().prepareCall(consulta);
            call.setString(1, TCedula.getText());
            call.setString(2, TNombre1.getText());
            call.setString(3, TNombre2.getText());
            call.setString(4, TApellido1.getText());
            call.setString(5, TApellido2.getText());
            call.setString(6, TUsuario.getText());
            call.setString(7, TContraseña.getText());
            call.setString(8, TCedula.getText());
            call.execute();

            JOptionPane.showMessageDialog(null, "Usuario actualizado");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario" + e.toString());
        }
    }// fin del método ActualizarUsuario

    public void EliminarUsuario(JTextField TCedula) {
        
        POO.Conexion objetoconexion = new POO.Conexion();
        String consulta = "DELETE FROM usuarios WHERE cedula=?;";

        try {
            CallableStatement cs = (CallableStatement) objetoconexion.EstablecerConexion().prepareCall(consulta); 

            cs.setString(1, TCedula.getText());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Usuario eliminado");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar usuario" + e.toString());
        }
    }// fin del método EliminarUsuario

}// fin de la clase AdminPanel