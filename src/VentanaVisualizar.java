package clinica;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VentanaVisualizar extends JFrame {

    Connection connection = null;
    ResultSet resultSet;

    public VentanaVisualizar() {
        setSize(600, 350);
        setTitle("VISUALIZAR");
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

        JPanel tablaPanel = new JPanel();
        tablaPanel.setLayout(flowLayout);
        String column_names[]= {"Id", "Nombre", "Apellidos", "DNI", "Direccion", "Teléfono", "Especialista"};
        DefaultTableModel model = new DefaultTableModel(column_names, 0);
        JTable tablaTable = new JTable(model);
        tablaPanel.add(tablaTable.getTableHeader());
        tablaPanel.add(tablaTable);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        botonesPanel.add(btnAceptar);
        botonesPanel.add(btnCancelar);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (nombreTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Escriba un nombre", "AVISO", JOptionPane.WARNING_MESSAGE);
                } else {
                    Statement statement;
                    try {
                        statement = connection.createStatement();
                        String sql = "SELECT * FROM datos WHERE Nombre = \"" + nombreTextField.getText() + "\"";
                        resultSet = statement.executeQuery(sql);

                        while (resultSet.next()) {
                            Object[] fila = new Object[7];
                            for (int i = 0; i < 7; i++) {
                                fila[i] = resultSet.getObject(i + 1);
                            }
                            model.addRow(fila);
                        }

                    } catch (SQLException exception) {
                        JOptionPane.showMessageDialog(null, "Algo ha fallado", "AVISO", JOptionPane.WARNING_MESSAGE);
                        Clinica.escribir("errores.txt", exception.getMessage());
                    }
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
        panelContenedor.add(tablaPanel);
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
