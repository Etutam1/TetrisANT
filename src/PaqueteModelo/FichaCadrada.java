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
public class FichaCadrada extends Ficha {

    private Cadrado cadrado1 = new Cadrado(getXogo().getMAX_X()/2, getXogo().getMIN_Y(), Color.YELLOW);
    private Cadrado cadrado2 = new Cadrado(cadrado1.getX() + getXogo().getLADO_CADRADO(), cadrado1.getY(), Color.YELLOW);
    private Cadrado cadrado3 = new Cadrado(cadrado1.getX(), cadrado1.getY() + getXogo().getLADO_CADRADO(), Color.YELLOW);
    private Cadrado cadrado4 = new Cadrado(cadrado3.getX() + getXogo().getLADO_CADRADO(), cadrado2.getY() + getXogo().getLADO_CADRADO(), Color.YELLOW);

    //CONSTRUCTOR
    public FichaCadrada(Xogo xogo) {
        super(xogo);
      this.agregarCadradosArrayCadrados();
    }

    //METODOS
    @Override
    public boolean rotar() {
        return false;
    }

    @Override
    public void agregarCadradosArrayCadrados() {
        getCadrados().add(this.cadrado1);
        getCadrados().add(this.cadrado2);
        getCadrados().add(this.cadrado3);
        getCadrados().add(this.cadrado4);
    }
    
    

    /**
     * @return the cadrado1
     */
    public Cadrado getCadrado1(){
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
    public Cadrado getCadrado2(){
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
    public Cadrado getCadrado3(){
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
    public Cadrado getCadrado4(){
        return cadrado4;
    }

    /**
     * @param cadrado4 the cadrado4 to set
     */
    public void setCadrado4(Cadrado cadrado4) {
        this.cadrado4 = cadrado4;
    }
    
    

}
