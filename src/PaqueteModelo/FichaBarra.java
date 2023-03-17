/*
 * Click nbfs://nbhost/ScadradoFixo_YstemFileScadradoFixo_Ystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/ScadradoFixo_YstemFileScadradoFixo_Ystem/Templates/Classes/Class.java to edit this template
 */
package PaqueteModelo;

import java.awt.Color;

/**
 *
 * @author matut
 */
public class FichaBarra extends Ficha {

    private Cadrado cadrado1 = new Cadrado(getXogo().getMAX_X() / 2, getXogo().getMIN_Y(), Color.CYAN);
    private Cadrado cadrado2 = new Cadrado(cadrado1.getX() + Xogo.getLADO_CADRADO(), cadrado1.getY(), Color.CYAN);
    private Cadrado cadrado3 = new Cadrado(cadrado2.getX() + Xogo.getLADO_CADRADO(), cadrado2.getY(), Color.CYAN);
    private Cadrado cadrado4 = new Cadrado(cadrado3.getX() + Xogo.getLADO_CADRADO(), cadrado3.getY(), Color.CYAN);

    //CONSTRUCTOR
    public FichaBarra(Xogo xogo) {
        super(xogo);

        this.agregarCadradosArrayCadrados();

    }

    //METODOS
    @Override
    public boolean rotar() {

        int cadradoFixo_X = getCadrado2().getX();
        int cadradoFixo_Y = getCadrado2().getY();
        boolean cambioPosicion = false;

        if (getPosicion() > 1) {    //CADA VEZ QUE PULSAMOS LA TECLA W, LLAMA AL METODO ROTAR Y SUMA 1 AL ATRIBUTO POSICION 
            setPosicion(0); //ENTONCES AQUI COMPROBAMOS SI PASA LA MAXIMA POSICION PARA VOLVER A LA 0
        }
        if (getPosicion() == 0) {

            if (comprobarPosicion1(cadradoFixo_X, cadradoFixo_Y)) {
                rotarAPosicion1(cadradoFixo_X, cadradoFixo_Y);
                cambioPosicion = true;
            }
        }
        else if (getPosicion() == 1) {
            if (comprobarPosicion0(cadradoFixo_X, cadradoFixo_Y)) {
                rotarAPosicion0(cadradoFixo_X, cadradoFixo_Y);
                cambioPosicion = true;
            }
        } 
        return cambioPosicion;
    }

    private boolean comprobarPosicion0(int cadradoFixo_X, int cadradoFixo_Y) {

        boolean podeRotar = false;
        if (getXogo().ePosicionValida(cadradoFixo_X, cadradoFixo_Y - Xogo.getLADO_CADRADO()) && getXogo().ePosicionValida(cadradoFixo_X, cadradoFixo_Y + Xogo.getLADO_CADRADO()) && getXogo().ePosicionValida(cadradoFixo_X, cadradoFixo_Y + 2 * Xogo.getLADO_CADRADO())) {
            podeRotar = true;
        }
        return podeRotar;
    }

    private void rotarAPosicion0(int cadradoFixo_X, int cadradoFixo_Y) {
        getCadrado1().getLblCadrado().setLocation(cadradoFixo_X, cadradoFixo_Y - Xogo.getLADO_CADRADO());
        getCadrado3().getLblCadrado().setLocation(cadradoFixo_X, cadradoFixo_Y + Xogo.getLADO_CADRADO());
        getCadrado4().getLblCadrado().setLocation(cadradoFixo_X, cadradoFixo_Y + 2 * Xogo.getLADO_CADRADO());
    }

    private boolean comprobarPosicion1(int cadradoFixo_X, int cadradoFixo_Y) {
        boolean podeRotar = false;
        if (getXogo().ePosicionValida(cadradoFixo_X - Xogo.getLADO_CADRADO(), cadradoFixo_Y) && getXogo().ePosicionValida(cadradoFixo_X + Xogo.getLADO_CADRADO(), cadradoFixo_Y) && getXogo().ePosicionValida(cadradoFixo_X + 2 * Xogo.getLADO_CADRADO(), cadradoFixo_Y)) {
            podeRotar = true;
        }
        return podeRotar;
    }

    private void rotarAPosicion1(int cadradoFixo_X, int cadradoFixo_Y) {
        getCadrado1().getLblCadrado().setLocation(cadradoFixo_X - Xogo.getLADO_CADRADO(), cadradoFixo_Y);
        getCadrado3().getLblCadrado().setLocation(cadradoFixo_X + Xogo.getLADO_CADRADO(), cadradoFixo_Y);
        getCadrado4().getLblCadrado().setLocation(cadradoFixo_X + 2 * Xogo.getLADO_CADRADO(), cadradoFixo_Y);
    }

    @Override
    public void agregarCadradosArrayCadrados() {
        getCadrados().add(getCadrado1());
        getCadrados().add(getCadrado2());
        getCadrados().add(getCadrado3());
        getCadrados().add(getCadrado4());
    }

    /**
     * @return the cadrado1
     */
    public Cadrado getCadrado1() {
        return cadrado1;
    }

    /**
     * @param cadrado1 the cadrado1 to set
     */
    public void setCadrado1(Cadrado cadrado1) {
        this.cadrado1 = cadrado1;
    }

    /**
     * @return the cadrado2
     */
    public Cadrado getCadrado2() {
        return cadrado2;
    }

    /**
     * @param cadrado2 the cadrado2 to set
     */
    public void setCadrado2(Cadrado cadrado2) {
        this.cadrado2 = cadrado2;
    }

    /**
     * @return the cadrado3
     */
    public Cadrado getCadrado3() {
        return cadrado3;
    }

    /**
     * @param cadrado3 the cadrado3 to set
     */
    public void setCadrado3(Cadrado cadrado3) {
        this.cadrado3 = cadrado3;
    }

    /**
     * @return the cadrado4
     */
    public Cadrado getCadrado4() {
        return cadrado4;
    }

    /**
     * @param cadrado4 the cadrado4 to set
     */
    public void setCadrado4(Cadrado cadrado4) {
        this.cadrado4 = cadrado4;
    }
}
