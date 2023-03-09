/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaqueteModelo;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author matut
 */
public class Cadrado {

    //ATRIBUTOS 
    public int x;
    public int y;
    public Color corRecheo;
    public JLabel lblCadrado;

    //CONSTRUCTOR
    public Cadrado(int x, int y, Color corRecheo) {
        this.x = x;
        this.y = y;
        this.corRecheo = corRecheo;
        lblCadrado = new JLabel();
        lblCadrado.setBackground(corRecheo);
        lblCadrado.setForeground(new java.awt.Color(204, 0, 0));
        lblCadrado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblCadrado.setMaximumSize(new java.awt.Dimension(Xogo.LADO_CADRADO, Xogo.LADO_CADRADO));
        lblCadrado.setMinimumSize(new java.awt.Dimension(Xogo.LADO_CADRADO, Xogo.LADO_CADRADO));
        lblCadrado.setOpaque(true);
        lblCadrado.setPreferredSize(new java.awt.Dimension(Xogo.LADO_CADRADO, Xogo.LADO_CADRADO));
        lblCadrado.setBounds(x, y, Xogo.LADO_CADRADO, Xogo.LADO_CADRADO);
    }

    //SETTERs AND GETTERs
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getCorRecheo() {
        return corRecheo;
    }

    public void setCorRecheo(Color corRecheo) {
        this.corRecheo = corRecheo;
    }

    public JLabel getLblCadrado() {
        return lblCadrado;
    }

    public void setLblCadrado(JLabel lblCadrado) {
        this.lblCadrado = lblCadrado;
    }

    //METODOS
    public String getCoordenadas() {
        int coorX = this.getX();
        int coorY = this.getY();
        String coordenadas = String.valueOf(coorX) +" " +String.valueOf(coorY);
        
               
                
        
        return coordenadas;
    }

}
