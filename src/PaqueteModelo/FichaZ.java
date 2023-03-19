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

    private Cadrado cadrado1 = new Cadrado(getXogo().getMAX_X() / 2, getXogo().getMIN_Y(), Color.RED);
    private Cadrado cadrado2 = new Cadrado(cadrado1.getX() + getXogo().getLADO_CADRADO(), cadrado1.getY(), Color.RED);
    private Cadrado cadrado3 = new Cadrado(cadrado2.getX(), cadrado2.getY() + getXogo().getLADO_CADRADO(), Color.RED);
    private Cadrado cadrado4 = new Cadrado(cadrado3.getX() + getXogo().getLADO_CADRADO(), cadrado3.getY(), Color.RED);
    private int posicion = 1;

    //CONSTRUCTOR
    public FichaZ(Xogo xogo) {
        super(xogo);

        this.agregarCadradosArrayCadrados();
    }

    //METODOS
    @Override
    public boolean rotar() {

        int cadradoFixo_X = this.cadrado2.getX();
        int cadradoFixo_Y = this.cadrado2.getY();

        if (this.posicion == 0) {
            if (this.comprobarPosicion1(cadradoFixo_X, cadradoFixo_Y)) {
                this.rotarAPosicion1(cadradoFixo_X, cadradoFixo_Y);
                this.actualizarCoordsLblCoCadrado();
                this.posicion = 1;
            }
        } else if (this.posicion == 1) {
            if (this.comprobarPosicion0(cadradoFixo_X, cadradoFixo_Y)) {
                this.rotarAPosicion0(cadradoFixo_X, cadradoFixo_Y);
                this.actualizarCoordsLblCoCadrado();
                this.posicion = 0;
            }
        }
        return true;
    }

    private void rotarAPosicion0(int cadradoFixo_X, int cadradoFixo_Y) {
        this.cadrado1.getLblCadrado().setLocation(cadradoFixo_X, cadradoFixo_Y - Xogo.getLADO_CADRADO());
        this.cadrado3.getLblCadrado().setLocation(cadradoFixo_X - Xogo.getLADO_CADRADO(), cadradoFixo_Y);
        this.cadrado4.getLblCadrado().setLocation(cadradoFixo_X - Xogo.getLADO_CADRADO(), cadradoFixo_Y + Xogo.getLADO_CADRADO());
    }

    private boolean comprobarPosicion0(int cadradoFixo_X, int cadradoFixo_Y) {
        boolean podeRotar = false;
        if (getXogo().ePosicionValida(cadradoFixo_X, cadradoFixo_Y - Xogo.getLADO_CADRADO()) && getXogo().ePosicionValida(cadradoFixo_X - Xogo.getLADO_CADRADO(), cadradoFixo_Y) && getXogo().ePosicionValida(cadradoFixo_X - Xogo.getLADO_CADRADO(), cadradoFixo_Y + Xogo.getLADO_CADRADO())) {
            podeRotar = true;
        }
        return podeRotar;
    }

    private void rotarAPosicion1(int cadradoFixo_X, int cadradoFixo_Y) {
        this.cadrado1.getLblCadrado().setLocation(cadradoFixo_X - Xogo.getLADO_CADRADO(), cadradoFixo_Y);
        this.cadrado3.getLblCadrado().setLocation(cadradoFixo_X, cadradoFixo_Y + Xogo.getLADO_CADRADO());
        this.cadrado4.getLblCadrado().setLocation(cadradoFixo_X + Xogo.getLADO_CADRADO(), cadradoFixo_Y + Xogo.getLADO_CADRADO());
    }

    private boolean comprobarPosicion1(int cadradoFixo_X, int cadradoFixo_Y) {
        boolean podeRotar = false;
        if (getXogo().ePosicionValida(cadradoFixo_X - Xogo.getLADO_CADRADO(), cadradoFixo_Y) && getXogo().ePosicionValida(cadradoFixo_X, cadradoFixo_Y + Xogo.getLADO_CADRADO()) && getXogo().ePosicionValida(cadradoFixo_X + Xogo.getLADO_CADRADO(), cadradoFixo_Y + Xogo.getLADO_CADRADO())) {
            podeRotar = true;
        }
        return podeRotar;
    }

    @Override
    public void agregarCadradosArrayCadrados() {
        getCadrados().add(this.cadrado1);
        getCadrados().add(this.cadrado2);
        getCadrados().add(this.cadrado3);
        getCadrados().add(this.cadrado4);
    }
    
     private void actualizarCoordsLblCoCadrado() {
        this.actualizarCoordsCadrado(cadrado1,  this.cadrado1.getLblCadrado().getX(), this.cadrado1.getLblCadrado().getY());
        this.actualizarCoordsCadrado(cadrado2, this.cadrado2.getLblCadrado().getX(), this.cadrado2.getLblCadrado().getY());
        this.actualizarCoordsCadrado(cadrado3, this.cadrado3.getLblCadrado().getX(), this.cadrado3.getLblCadrado().getY());
        this.actualizarCoordsCadrado(cadrado4, this.cadrado4.getLblCadrado().getX(), this.cadrado4.getLblCadrado().getY());
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
