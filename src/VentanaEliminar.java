package clinica;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaEliminar extends JFrame {

    Connection connection = null;
    ResultSet resultSet;

    public VentanaEliminar() {
        setSize(300, 350);
        setTitle("ELIMINAR");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);

        JPanel nombrePanel = new JPanel();
        nombrePanel.setLayout(flowLayout);
        JLabel nombreLabel = new JLabel("Nombre");
        JTextField nombreTextField = new JTextField("");
        nombreTextField.setColumns(10);
        nombrePanel.add(nombreLabel);
        nombrePanel.add(nombreTextField);

        JPanel apellidosPanel = new JPanel();
        apellidosPanel.setLayout(flowLayout);
        JLabel apellidosLabel = new JLabel("Apellidos");
        JTextField apellidosTextField = new JTextField("");
        apellidosTextField.setColumns(10);
        apellidosPanel.add(apellidosLabel);
        apellidosPanel.add(apellidosTextField);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        botonesPanel.add(btnAceptar);
        botonesPanel.add(btnCancelar);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (nombreTextField.getText().equals("")
                        || apellidosTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Rellena todos los campos", "AVISO", JOptionPane.WARNING_MESSAGE);
                } else {
                    PreparedStatement pStatement;
                    try {
                        pStatement = connection.prepareStatement("DELETE FROM datos WHERE Nombre = \"" + nombreTextField.getText() + "\" AND Apellidos = \"" + apellidosTextField.getText() + "\";");
                        pStatement.executeUpdate();
                        resultSet = null;
                    } catch (NumberFormatException | SQLException exception) {
                        JOptionPane.showMessageDialog(null, "Algo ha fallado", "AVISO", JOptionPane.WARNING_MESSAGE);
                        Clinica.escribir("errores.txt", exception.getMessage());
                    }
                    JOptionPane.showMessageDialog(null, "Datos borrados", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                    Clinica.escribir("aciertos.txt", "Datos borrados");
                }

            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });

        panelContenedor.add(nombrePanel);
        panelContenedor.add(apellidosPanel);
        panelContenedor.add(botonesPanel);
        setContentPane(panelContenedor);
        setVisible(true);

        dbConect("datos");
    }

    private void dbConect(String tabla) {

        String dbName = "pacientes";
        String user = "root";
        String password = "1234";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                    + dbName + "?&user=" + user + "&password=" + password
                    + "&useLegacyDatetimeCode=false&serverTimezone=UTC");
            if (!connection.isClosed()) {
                //JOptionPane.showMessageDialog(null, "Conexión correcta", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            }
            Statement statement = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM " + dbName + "." + tabla + ";";
            resultSet = statement.executeQuery(sql);

        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Conexión fallida", "AVISO", JOptionPane.WARNING_MESSAGE);
        }

    }

}
