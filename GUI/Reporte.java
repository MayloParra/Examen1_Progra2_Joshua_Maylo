package GUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import POO.Conexion;

public class Reporte extends JPanel {
    Color nuevo = new Color(21, 85, 223);
    Color Boton = new Color(15, 60, 157);
    JLabel Titulo;
    JTextArea Dato;
    JButton Salir;
    private String nombreUsuario;

    // declaración de atributos
    public Reporte(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        Titulo = new JLabel();
        Dato = new JTextArea();
        Salir = new JButton();
        // Inicialización de atributos

        setSize(1000, 700);
        setLayout(null);
        setBackground(nuevo);
        add(Titulo);
        add(Dato);
        add(Salir);
        // detalles del JPanel

        detalles();
        eventos();
        cargarDatosDesdeBaseDeDatos();
        // llamado de métodos
    }

    public void detalles() {
        Titulo.setBounds(380, 10, 600, 50);
        Titulo.setText("Reporte Final del día");
        Titulo.setFont(new Font("Arial", Font.PLAIN, 25)); // este método sirve para poner fuente y tamaño de letra
        Titulo.setForeground(Color.WHITE);
        Dato.setBounds(10, 100, 970, 200);
        Dato.setEditable(false);
        Dato.setBackground(Boton);
        Dato.setForeground(Color.WHITE);
        Salir.setBounds(400, 400, 200, 70);
        Salir.setText("Salir del sistema");
        Salir.setFocusPainted(false);
        Salir.setBorderPainted(false);
        Salir.setBackground(Boton);
        Salir.setForeground(Color.WHITE);
    }// Este método sirve para asignar detalles a los botones o cualquier objeto swing

    public void eventos() {
        Salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }); // Esta acción es del botón Salir del sistema

    }// Método para dar funcionalidad a los botones

    public void cargarDatosDesdeBaseDeDatos() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder reporte = new StringBuilder();

        try {
            Conexion objetoconexion = new Conexion();
            con = objetoconexion.EstablecerConexion();

            String consulta = "SELECT UsuarioChofer, monto_efectivo, monto_tarjeta, total FROM totales WHERE UsuarioChofer = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                String chofer = rs.getString("UsuarioChofer");
                double totalEfectivo = rs.getDouble("monto_efectivo");
                double totalTarjeta = rs.getDouble("monto_tarjeta");
                double totalDia = rs.getDouble("total");

                reporte.append("Chofer: ").append(chofer)
                        .append("\nTotal en efectivo: ").append(totalEfectivo)
                        .append("\nTotal en tarjeta: ").append(totalTarjeta)
                        .append("\nTotal del día: ").append(totalDia)
                        .append("\n\n");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos de la base de datos: " + e.toString());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
            }
        }

        Dato.setText(reporte.toString());
    }
}// Clase Reporte