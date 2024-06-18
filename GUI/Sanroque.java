package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import POO.Arreglo;

public class Sanroque extends JPanel {
    JButton Efectivo,PagoFacil;
    Color nuevo=new Color(21, 85, 223);
    Color Boton=new Color(15, 60, 157);
    //Declaración de atributos


    public Sanroque(){
    Efectivo=new JButton();
    PagoFacil=new JButton();
    //inicialización


        setSize(1000,700);
        setLayout(null);
        add(Efectivo);
        add(PagoFacil);
        setBackground(nuevo);

        //detalles del JPanel

        detales();
        eventos();
        //Llamado de métodos
    }//Constructor

    public void detales(){
    Efectivo.setBounds(150, 300, 300, 50);
    Efectivo.setText("EFECTIVO");
    Efectivo.setFocusPainted(false);
    Efectivo.setBorderPainted(false);
    Efectivo.setBackground(Boton);
    Efectivo.setForeground(Color.WHITE);

    PagoFacil.setBounds(550, 300, 300, 50);
    PagoFacil.setText("PAGO FÁCIL");
    PagoFacil.setFocusPainted(false);
    PagoFacil.setBorderPainted(false);
    PagoFacil.setBackground(Boton);
    PagoFacil.setForeground(Color.WHITE);

    }//Este método sirve para dar detalle a botones y etc
    public void NuevoPanel(JPanel panel){

        panel.setSize(1000,700);
        panel.setLocation(0, 0);
        removeAll();
        add(panel,BorderLayout.CENTER);
        repaint();
        revalidate();
        
        
        
    }//método para intercambiar paneles
    public void eventos(){
        Efectivo.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                              if (interfaz.arreglo[0] != null) {
                    interfaz.arreglo[0].AgregarEfectivo(500);
                } else {
                    
                    interfaz.arreglo[0] = new Arreglo();
                   interfaz.arreglo[0].AgregarEfectivo(500);
                }
              JOptionPane.showMessageDialog(null, "El pasaje es de 500 colones"+"\n"+"Por favor pagar al chofer");
                NuevoPanel(new interfaz().principal);
            }
        });

        PagoFacil.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                if (interfaz.arreglo[0] != null) {
                    interfaz.arreglo[0].AgregarPagoFacil(500);
                } else {
                    
                    interfaz.arreglo[0] = new Arreglo();
                   interfaz.arreglo[0].AgregarPagoFacil(500);
                }
              JOptionPane.showMessageDialog(null, "El Pago se ha realizado correctamente");
                NuevoPanel(new interfaz().principal);
            }
        });
    }//Método para dar funcionalidad a los botones
}
