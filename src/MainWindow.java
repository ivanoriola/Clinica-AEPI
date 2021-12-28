package clinica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {

    public MainWindow() {

        initUI();
    }

    private void initUI() {
        setSize(250, 250);
        setTitle("Clínica AEPI");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelContenedor = new JPanel();

        JMenuBar menuBar = new JMenuBar();

        JMenu mainMenu = new JMenu("Menú");
        JMenu logsMenu = new JMenu("Logs");

        JMenuItem aciertosMenuItem = new JMenuItem("Aciertos");
        aciertosMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                VentanaLogsAciertos ventanaLogsAciertos = new VentanaLogsAciertos();
            }
        });
        JMenuItem erroresMenuItem = new JMenuItem("Errores");
        erroresMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                VentanaLogsErrores ventanaLogsErrores = new VentanaLogsErrores();
            }
        });

        logsMenu.add(aciertosMenuItem);
        logsMenu.add(erroresMenuItem);

        JMenuItem insertarMenuItem = new JMenuItem("Insertar");
        insertarMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                VentanaInsertar ventanaInsertar = new VentanaInsertar();
            }
        });
        JMenuItem visualizarMenuItem = new JMenuItem("Visualizar");
        visualizarMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                VentanaVisualizar ventanaVisualizar = new VentanaVisualizar();
            }
        });
        JMenuItem editarMenuItem = new JMenuItem("Editar");
        editarMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               VentanaEditar ventanaEditar = new VentanaEditar();
            }
        });
        JMenuItem eliminarMenuItem = new JMenuItem("Eliminar");
        eliminarMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                VentanaEliminar ventanaEliminar = new VentanaEliminar();
            }
        });

        JMenuItem facturasMenuItem = new JMenuItem("Facturas");
        facturasMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                VentanaFacturas ventanaFacturas = new VentanaFacturas();
            }
        });

        mainMenu.add(insertarMenuItem);
        mainMenu.add(visualizarMenuItem);
        mainMenu.add(editarMenuItem);
        mainMenu.add(eliminarMenuItem);
        mainMenu.addSeparator();
        mainMenu.add(logsMenu);
        mainMenu.addSeparator();
        mainMenu.add(facturasMenuItem);

        menuBar.add(mainMenu);
        setJMenuBar(menuBar);
        panelContenedor.add(menuBar);
        setContentPane(panelContenedor);
        setVisible(true);
    }
}
