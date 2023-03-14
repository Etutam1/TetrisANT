package PaqueteModelo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author matut
 */
public class Xogador {
    
    private String nombre;
    private int score;

    public Xogador(String linea) {
        String[] datos = linea.split("-");
        this.nombre = datos[0];
        this.score = Integer.parseInt(datos[1]);
    }

    
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
    
    
}
