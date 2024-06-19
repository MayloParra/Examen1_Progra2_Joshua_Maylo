package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JTextField tfUsuario;
    private JPasswordField pfContraseña;
    private JLabel Lbusuario, LbContraseña;
    private JButton btnIniciar, btnCancelar, Creditos;
    JPanel panel = new PanelConFondo("/Imagenes/Fondo3.jpg");

    public Login() {
        setTitle("Ventana de inicio de seccion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(panel);
        panel.setLayout(null);
        eventos();
    }//fin del constructor

    public void eventos() {
        Lbusuario = new JLabel("Ingrese el nombre usuario:");
        Lbusuario.setBounds(240, 130, 200, 35);
        Lbusuario.setForeground(Color.WHITE);
        panel.add(Lbusuario);

        tfUsuario = new JTextField();
        tfUsuario.setBounds(240, 160, 300, 35);
        panel.add(tfUsuario);

        LbContraseña = new JLabel("Ingrese la contraseña:");
        LbContraseña.setBounds(240, 210, 200, 35);
        LbContraseña.setForeground(Color.WHITE);
        panel.add(LbContraseña);

        pfContraseña = new JPasswordField();
        pfContraseña.setBounds(240, 240, 300, 35);
        panel.add(pfContraseña);

        btnIniciar = new JButton("Iniciar sesión");
        btnIniciar.setBounds(240, 300, 150, 35);
        panel.add(btnIniciar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(390, 300, 150, 35);
        panel.add(btnCancelar);

        Creditos = new JButton("Derechos de autor");
        Creditos.setBounds(610, 500, 150, 35);
        panel.add(Creditos);

        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                POO.CLogin objetLogin = new POO.CLogin();
                objetLogin.Validacion(tfUsuario, pfContraseña);
                dispose();
            }
        });

        Creditos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Derechos ventana = new Derechos();
                ventana.setVisible(true);
                dispose();
            }
        });
    }//fin de eventos

    public static void main(String[] args) {
        Login ventana = new Login();
        ventana.setBounds(0, 0, 800, 600);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
       
    }//fin del main
}//fin de la clase