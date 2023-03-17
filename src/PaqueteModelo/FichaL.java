/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaqueteModelo;

import java.awt.Color;

/**
 *
 * @author matut
 */
public class FichaL extends Ficha {

    private Cadrado cadrado1 = new Cadrado(getXogo().getMAX_X() / 2, getXogo().getMIN_Y(), Color.ORANGE);
    private Cadrado cadrado2 = new Cadrado(cadrado1.getX() + getXogo().getLADO_CADRADO(), cadrado1.getY(), Color.ORANGE);
    private Cadrado cadrado3 = new Cadrado(cadrado2.getX() + getXogo().getLADO_CADRADO(), cadrado2.getY(), Color.ORANGE);
    private Cadrado cadrado4 = new Cadrado(cadrado3.getX(), cadrado3.getY() - getXogo().getLADO_CADRADO(), Color.ORANGE);

    //CONSTRUCTOR
    public FichaL(Xogo xogo) {
        super(xogo);
        this.agregarCadradosArrayCadrados();
    }

    //METODOS
    @Override
    public boolean rotar() {

        int cadradoFixo_X = getCadrado2().getX();
        int cadradoFixo_Y = getCadrado2().getY();
        boolean cambioPosicion = false;

        if (getPosicion() > 3) {
            setPosicion(0);
        }
        if (getPosicion() == 0) {
            if (comprobarPosicion1(cadradoFixo_X, cadradoFixo_Y)) {
                rotarAPosicion1();
                cambioPosicion = true;
            }

        } else if (getPosicion() == 1) {
            if (comprobarPosicion2(cadradoFixo_X, cadradoFixo_Y)) {
                rotarAPosicion2();
                cambioPosicion = true;
            }

        } else if (getPosicion() == 2) {
            if (comprobarPosicion3(cadradoFixo_X, cadradoFixo_Y)) {
                rotarAPosicion3();
                cambioPosicion = true;
            }

        } else if (getPosicion() == 3) {
            if (comprobarPosicion0(cadradoFixo_X, cadradoFixo_Y)) {
                rotarAPosicion0();
                cambioPosicion = true;
            }
        } 
        return cambioPosicion;
    }

    private void rotarAPosicion0() {
        getCadrado1().getLblCadrado().setLocation(getCadrado2().getX(), getCadrado2().getY() - Xogo.getLADO_CADRADO());
        getCadrado3().getLblCadrado().setLocation(getCadrado2().getX(), getCadrado2().getY() + Xogo.getLADO_CADRADO());
        getCadrado4().getLblCadrado().setLocation(getCadrado2().getX() - Xogo.getLADO_CADRADO(), getCadrado2().getY() - Xogo.getLADO_CADRADO());
    }

    private boolean comprobarPosicion0(int cadradoFixo_X, int cadradoFixo_Y) {
        boolean podeRotar = false;
        if (getXogo().ePosicionValida(cadradoFixo_X, cadradoFixo_Y - Xogo.getLADO_CADRADO()) && getXogo().ePosicionValida(cadradoFixo_X, cadradoFixo_Y + Xogo.getLADO_CADRADO()) && getXogo().ePosicionValida(cadradoFixo_X - Xogo.getLADO_CADRADO(), cadradoFixo_Y - Xogo.getLADO_CADRADO())) {
            podeRotar = true;
        }
        return podeRotar;
    }

    private void rotarAPosicion3() {
        getCadrado1().getLblCadrado().setLocation(getCadrado2().getX() - Xogo.getLADO_CADRADO(), getCadrado2().getY());
        getCadrado3().getLblCadrado().setLocation(getCadrado2().getX() + Xogo.getLADO_CADRADO(), getCadrado2().getY());
        getCadrado4().getLblCadrado().setLocation(getCadrado2().getX() - Xogo.getLADO_CADRADO(), getCadrado2().getY() + Xogo.getLADO_CADRADO());
    }

    private boolean comprobarPosicion3(int cadradoFixo_X, int cadradoFixo_Y) {
        boolean podeRotar = false;
        if (getXogo().ePosicionValida(cadradoFixo_X - Xogo.getLADO_CADRADO(), cadradoFixo_Y) && getXogo().ePosicionValida(cadradoFixo_X + Xogo.getLADO_CADRADO(), cadradoFixo_Y) && getXogo().ePosicionValida(cadradoFixo_X - Xogo.getLADO_CADRADO(), cadradoFixo_Y + Xogo.getLADO_CADRADO())) {
            podeRotar = true;
        }
        return podeRotar;
    }

    private void rotarAPosicion2() {
        getCadrado1().getLblCadrado().setLocation(getCadrado2().getX(), getCadrado2().getY() - Xogo.getLADO_CADRADO());
        getCadrado3().getLblCadrado().setLocation(getCadrado2().getX(), getCadrado2().getY() + Xogo.getLADO_CADRADO());
        getCadrado4().getLblCadrado().setLocation(getCadrado2().getX() + Xogo.getLADO_CADRADO(), getCadrado2().getY() + Xogo.getLADO_CADRADO());
    }

    private boolean comprobarPosicion2(int cadradoFixo_X, int cadradoFixo_Y) {
        boolean podeRotar = false;
        if (getXogo().ePosicionValida(cadradoFixo_X - Xogo.getLADO_CADRADO(), cadradoFixo_Y) && getXogo().ePosicionValida(cadradoFixo_X + Xogo.getLADO_CADRADO(), cadradoFixo_Y) && getXogo().ePosicionValida(cadradoFixo_X + Xogo.getLADO_CADRADO(), cadradoFixo_Y + Xogo.getLADO_CADRADO())) {
            podeRotar = true;
        }
        return podeRotar;
    }

    private void rotarAPosicion1() {
        getCadrado1().getLblCadrado().setLocation(getCadrado2().getX() - Xogo.getLADO_CADRADO(), getCadrado2().getY());
        getCadrado3().getLblCadrado().setLocation(getCadrado2().getX() + Xogo.getLADO_CADRADO(), getCadrado2().getY());
        getCadrado4().getLblCadrado().setLocation(getCadrado2().getX() + Xogo.getLADO_CADRADO(), getCadrado2().getY() - Xogo.getLADO_CADRADO());
    }

    private boolean comprobarPosicion1(int cadradoFixo_X, int cadradoFixo_Y) {
        boolean podeRotar = false;
        if (getXogo().ePosicionValida(cadradoFixo_X - Xogo.getLADO_CADRADO(), cadradoFixo_Y) && getXogo().ePosicionValida(cadradoFixo_X + Xogo.getLADO_CADRADO(), cadradoFixo_Y) && getXogo().ePosicionValida(cadradoFixo_X + Xogo.getLADO_CADRADO(), cadradoFixo_Y - Xogo.getLADO_CADRADO())) {
            podeRotar = true;
        }
        return podeRotar;
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
