/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaqueteModelo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author matut
 */
public class ReportException {

    public ReportException() {
    }

    public void reportarException(Exception exception) {
        PrintWriter salida = null;

        try {
            salida = new PrintWriter(new FileWriter("Exceptions.txt", true));
            salida.write("Se ha producido la excepcion" + exception.toString() + "en la fecha " + new Date().toString() + "debido a " + exception.getCause().toString() + "\n");

        } catch (FileNotFoundException ex2) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO REPORTAR UN PROBLEMA");
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, "NO SE HA PODIDO REPORTAR UN PROBLEMA");
        } finally {

            if (salida != null) {
                salida.close();
            }
        }
    }

}
