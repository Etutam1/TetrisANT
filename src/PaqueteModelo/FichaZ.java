/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaqueteModelo;

import java.awt.Color;

/**
 *
 * @author maste
 */
public class FichaZ extends Ficha {

    public Cadrado cadrado1 = new Cadrado(xogo.MAX_X / 2, xogo.MIN_Y, Color.RED);
    public Cadrado cadrado2 = new Cadrado(cadrado1.getX() + xogo.LADO_CADRADO, cadrado1.getY(), Color.RED);
    public Cadrado cadrado3 = new Cadrado(cadrado2.getX(), cadrado2.getY() + xogo.LADO_CADRADO, Color.RED);
    public Cadrado cadrado4 = new Cadrado(cadrado3.getX() + xogo.LADO_CADRADO, cadrado3.getY(), Color.RED);

    //CONSTRUCTOR
    public FichaZ(Xogo xogo) {
        super(xogo);

        cadrados.add(cadrado1);
        cadrados.add(cadrado2);
        cadrados.add(cadrado3);
        cadrados.add(cadrado4);
    }

    //METODOS
    @Override
    public boolean rotar() {

        int cadradoFixo_X = cadrado2.getX();
        int cadradoFixo_Y = cadrado2.getY();
        int eValido = 0;

        if (posicion > 1) {
            posicion = 0;
        }
        if (posicion == 0) {
            if (xogo.ePosicionValida(cadradoFixo_X - Xogo.LADO_CADRADO, cadradoFixo_Y)) {
                eValido++;
            }
            if (xogo.ePosicionValida(cadradoFixo_X, cadradoFixo_Y + Xogo.LADO_CADRADO)) {
                eValido++;
            }
            if (xogo.ePosicionValida(cadradoFixo_X + Xogo.LADO_CADRADO, cadradoFixo_Y + Xogo.LADO_CADRADO)) {
                eValido++;
            }
            if (eValido == 3) {
                cadrado1.getLblCadrado().setLocation(cadradoFixo_X - Xogo.LADO_CADRADO, cadradoFixo_Y);
                cadrado3.getLblCadrado().setLocation(cadradoFixo_X, cadradoFixo_Y + Xogo.LADO_CADRADO);
                cadrado4.getLblCadrado().setLocation(cadradoFixo_X + Xogo.LADO_CADRADO, cadradoFixo_Y + Xogo.LADO_CADRADO);
            }
        }
        if (posicion == 1) {
            if (xogo.ePosicionValida(cadradoFixo_X, cadradoFixo_Y - Xogo.LADO_CADRADO)) {
                eValido++;
            }
            if (xogo.ePosicionValida(cadradoFixo_X - Xogo.LADO_CADRADO, cadradoFixo_Y)) {
                eValido++;
            }
            if (xogo.ePosicionValida(cadradoFixo_X - Xogo.LADO_CADRADO, cadradoFixo_Y + Xogo.LADO_CADRADO)) {
                eValido++;
            }

            if (eValido == 3) {
                cadrado1.getLblCadrado().setLocation(cadradoFixo_X, cadradoFixo_Y - Xogo.LADO_CADRADO);
                cadrado3.getLblCadrado().setLocation(cadradoFixo_X - Xogo.LADO_CADRADO, cadradoFixo_Y);
                cadrado4.getLblCadrado().setLocation(cadradoFixo_X - Xogo.LADO_CADRADO, cadradoFixo_Y + Xogo.LADO_CADRADO);
            }

        }
        return true;
    }
}
