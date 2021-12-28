package clinica;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VentanaLogsAciertos extends JFrame {

    public VentanaLogsAciertos() {
        setSize(300, 350);
        setTitle("LOGS (Aciertos)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        JPanel aciertosPanel = new JPanel();
        JTextArea aciertosTextArea = new JTextArea();
        aciertosTextArea.setSize(300, 300);
        aciertosPanel.add(aciertosTextArea);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        botonesPanel.add(btnAceptar);
        botonesPanel.add(btnCancelar);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                /////

                String texto = new String(), path = "aciertos.txt";
                File archivo = new File(path);

                FileReader fr = null;
                BufferedReader entrada = null;
                try {
                    fr = new FileReader(path);
                    entrada = new BufferedReader(fr);

                    while (entrada.ready()) {
                        texto += entrada.readLine();
                    }

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Algo ha fallado", "AVISO", JOptionPane.WARNING_MESSAGE);
                } finally {
                    try {
                        if (null != fr) {
                            fr.close();
                        }
                    } catch (Exception e2) {
                        JOptionPane.showMessageDialog(null, "Algo ha fallado", "AVISO", JOptionPane.WARNING_MESSAGE);
                    }
                }
                aciertosTextArea.setText(texto);

                /////
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });

        panelContenedor.add(aciertosPanel);
        panelContenedor.add(botonesPanel);
        setContentPane(panelContenedor);
        setVisible(true);
    }
}
