package clinica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Clinica {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            System.err.println(exception.toString());
        }
        MainWindow mainWindow = new MainWindow();
    }

    public static void escribir(String fichero, String texto) {

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            File file = new File(fichero);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            LocalDate fecha = LocalDate.now();
            bw.write(fecha.toString() + ": " + texto + "\n");
        } catch (IOException e) {
            //
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                //
            }
        }
    }
}
