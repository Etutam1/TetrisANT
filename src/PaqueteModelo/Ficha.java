/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaqueteModelo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author matut
 */
public abstract class Ficha {

    //ATRIBUTOS
    private ArrayList<Cadrado> cadrados = new ArrayList<>();
    private Xogo xogo;

    //CONSTRUCTOR
    public Ficha(Xogo xogo) {
        this.xogo = xogo;
    }

    public Xogo getXogo() {
        return xogo;
    }

    public void setXogo(Xogo xogo) {
        this.xogo = xogo;
    }

////    //SETTER AND GETTER
    public ArrayList<Cadrado> getCadrados() {
        return cadrados;
    }

    public void setCadrados(ArrayList<Cadrado> cadrados) {
        this.cadrados = cadrados;
    }

    //METODOS 
    public boolean moverDereita() {
        Iterator<Cadrado> iterator5 = getCadrados().iterator();

        while (iterator5.hasNext()) {
            Cadrado cadrado3 = iterator5.next();

            cadrado3.getLblCadrado().setLocation(cadrado3.getLblCadrado().getX() + Xogo.getLADO_CADRADO(), cadrado3.getLblCadrado().getY());
            actualizarCoordsCadrado(cadrado3, cadrado3.getLblCadrado().getX(), cadrado3.getLblCadrado().getY());

        }
        return true;
    }

    public boolean moverEsquerda() {
        Iterator<Cadrado> iterator6 = getCadrados().iterator();

        while (iterator6.hasNext()) {

            Cadrado cadrado2 = iterator6.next();

            cadrado2.getLblCadrado().setLocation(cadrado2.getLblCadrado().getX() - Xogo.getLADO_CADRADO(), cadrado2.getLblCadrado().getY());
            actualizarCoordsCadrado(cadrado2, cadrado2.getLblCadrado().getX(), cadrado2.getLblCadrado().getY());
        }

        return true;
    }

    public boolean moverAbaixo() {
        Iterator<Cadrado> iterator8 = getCadrados().iterator();

        while (iterator8.hasNext()) {
            Cadrado cadrado1 = iterator8.next();

            cadrado1.getLblCadrado().setLocation(cadrado1.getLblCadrado().getX(), cadrado1.getLblCadrado().getY() + Xogo.getLADO_CADRADO());
            actualizarCoordsCadrado(cadrado1, cadrado1.getLblCadrado().getX(), cadrado1.getLblCadrado().getY());
        }
        return true;
    }

    public void actualizarCoordsCadrado(Cadrado cadrado, int x, int y) {
        cadrado.setX(x);
        cadrado.setY(y);
    }

    public abstract boolean rotar();

    public abstract void agregarCadradosArrayCadrados();
    

}
