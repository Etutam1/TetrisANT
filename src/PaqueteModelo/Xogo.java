/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaqueteModelo;

import PaqueteIU.VentanaPrincipal;
import static PaqueteIU.VentanaPrincipal.cliper;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

/**
 *
 * @author matut
 */
public class Xogo {

    //ATRIBUTOS
    public final static int LADO_CADRADO = 40;
    public final int MAX_X;
    public final int MIN_Y;
    public final int MAX_Y;
    public final int MIN_X;
    public int comprobante = 0;
    public boolean pausa;
    public VentanaPrincipal ventanaPrincipal;
    public Ficha fichaActual;
    public ArrayList<Cadrado> cadradosChan = new ArrayList<>();

    public Timer timerComprobarLineas;
    public int level = 0;
    public int contadorScore = 0;
    public int numeroLineas = 0;

    //CONSTRUCTOR
    public Xogo(boolean pausa, VentanaPrincipal ventanaPrincipal) {

        this.MAX_X = 400;
        this.MIN_X = 0;
        this.MAX_Y = 800;
        this.MIN_Y = 0;
        this.pausa = pausa;
        this.ventanaPrincipal = ventanaPrincipal;

    }

    public boolean ePosicionValida(int x, int y) {
        boolean posicionValida = false;
        if (x < MAX_X && x >= MIN_X && y < MAX_Y && y >= MIN_Y) {
            posicionValida = true;
        }
        Iterator<Cadrado> iteratorChan5 = cadradosChan.iterator();
        while (iteratorChan5.hasNext()) {
            Cadrado cadradoComprobado = iteratorChan5.next();
            if (cadradoComprobado.getLblCadrado().getX() == x && cadradoComprobado.getLblCadrado().getY() == y) {
                posicionValida = false;
            }
        }
        return posicionValida;
    }

    public void moverFichaAbaixo() {

        if (chocaFichaCoChan()) {
            this.engadirFichaAoChan();

            if (!comprobarFinalPartida()) {
                this.xenerarNovaFicha();
            }
        } else {
            fichaActual.moverAbaixo();
        }
    }

    public void moverFichaDereita() {
        Iterator<Cadrado> iterator = fichaActual.cadrados.iterator();
        boolean podeMover = true;

        while (iterator.hasNext()) {
            Cadrado actual = iterator.next();

            if (!ePosicionValida(actual.getLblCadrado().getX() + Xogo.LADO_CADRADO, actual.getLblCadrado().getY())) {
                podeMover = false;
            }
        }
        if (podeMover) {
            fichaActual.moverDereita();
        }
    }

    public void moverFichaEsquerda() {
        Iterator<Cadrado> iterator2 = fichaActual.cadrados.iterator();
        boolean podeMover = true;

        while (iterator2.hasNext()) {
            Cadrado actual = iterator2.next();

            if (!ePosicionValida(actual.getLblCadrado().getX() - Xogo.LADO_CADRADO, actual.getLblCadrado().getY())) {
                podeMover = false;
            }
        }
        if (podeMover) {
            fichaActual.moverEsquerda();
        }
    }

    public void RotarFicha() {
        fichaActual.rotar();
    }

    public void xenerarNovaFicha() {

        int numAleatorio = (int) (Math.random() * 7 + 1);
        
        if (comprobante == numAleatorio) {
            numAleatorio = (int) (Math.random() * 7 + 1);
        }
        if (numAleatorio == 1) {
            fichaActual = new FichaCadrada(this);
            comprobante = numAleatorio;
        }
        if (numAleatorio == 2) {
            fichaActual = new FichaBarra(this);
            comprobante = numAleatorio;
        }
        if (numAleatorio == 3) {
            fichaActual = new FichaZ(this);
            comprobante = numAleatorio;
        }
        if (numAleatorio == 4) {
            fichaActual = new FichaZInversa(this);
            comprobante = numAleatorio;
        }
        if (numAleatorio == 5) {
            fichaActual = new FichaT(this);
            comprobante = numAleatorio;
        }
        if (numAleatorio == 6) {
            fichaActual = new FichaL(this);
            comprobante = numAleatorio;
        }
        if (numAleatorio == 7) {
            fichaActual = new FichaLInversa(this);
            comprobante = numAleatorio;
        }

        this.pintarFicha();
    }

    public void pintarFicha() {
        Iterator<Cadrado> iterator3 = fichaActual.getCadrados().iterator();

        while (iterator3.hasNext()) {
            ventanaPrincipal.pintarCadrado(iterator3.next().getLblCadrado());
        }
    }

    public boolean chocaFichaCoChan() {
        boolean tocaChan = false;
        Iterator<Cadrado> iterator4 = fichaActual.cadrados.iterator();

        while (iterator4.hasNext()) {
            Cadrado cadradoActual = iterator4.next();
            if (!ePosicionValida(cadradoActual.getLblCadrado().getX(), cadradoActual.getLblCadrado().getY() + LADO_CADRADO)) {
                tocaChan = true;
            }
        }
        return tocaChan;
    }

    public void engadirFichaAoChan() {

        String musicPath = "src\\\\Resources\\\\Musica\\\\pop.wav";
        playSound(musicPath);
        cadradosChan.addAll(fichaActual.cadrados);

    }

    public void borrarLinasCompletas() throws ConcurrentModificationException {
        int ultimaLinea = this.MAX_Y - Xogo.LADO_CADRADO;
        int primeraLinea = this.MIN_Y;
        ArrayList<Integer> coordsYLineas = new ArrayList<>();

        for (int y = ultimaLinea; y >= primeraLinea; y -= LADO_CADRADO) {
            Iterator<Cadrado> iteratorChan = cadradosChan.listIterator();
            int contadorCadrados = 0;
            System.out.println("y: " + y);

            while (iteratorChan.hasNext()) {
                Cadrado cadradoChan = iteratorChan.next();

                if (cadradoChan.lblCadrado.getY() == y) {
                    contadorCadrados++;
                    System.out.println("contadorCadrados: " + contadorCadrados);

                    if (contadorCadrados == 10) {
                        coordsYLineas.add(y);

                    }
                }
            }
        }
        Iterator iteratorYs = coordsYLineas.iterator();
        while (iteratorYs.hasNext()) {
            int linea = (int) iteratorYs.next();
            this.borrarLina(linea);
        }
    }

    public void borrarLina(int linea) {

        String musicPath = "src\\\\Resources\\\\Musica\\\\poom.wav";
        playSound(musicPath);
        ArrayList<Cadrado> cadradosBorrar = new ArrayList<>();
        Iterator<Cadrado> iteratorChan2 = cadradosChan.listIterator();

        while (iteratorChan2.hasNext()) {
            Cadrado cadradoABorrar = iteratorChan2.next();
            if (cadradoABorrar.getLblCadrado().getY() == linea) {
                cadradosBorrar.add(cadradoABorrar);
                ventanaPrincipal.borrarCadrado(cadradoABorrar.lblCadrado);
            }
        }
        cadradosChan.removeAll(cadradosBorrar);
        this.sumarNumeroLineas();
        this.moverCadradosChan(linea);
        ventanaPrincipal.mostrarNumeroLineas(this.numeroLineas);
        sumarScorePorLineaCompleta();
        comprobarLevel();
    }

   
   

   

    public void aumentarLevel() {
        this.level++;
    }

    public void comprobarLevel() {
        if (this.numeroLineas % 5 == 0) {
            aumentarLevel();
            if (this.level > 0) {
                ventanaPrincipal.mostrarLevel();
                actualizarDelays(ventanaPrincipal.timer.getDelay() / 2);
            }
        }
    }

    public void sumarNumeroLineas() {
        numeroLineas++;

    }

    public void sumarScorePorLineaCompleta() {
        if (this.level == 0) {
            contadorScore += 100;
        }
        if (this.level == 1) {
            contadorScore += 200;
        }
        if (this.level == 2) {
            contadorScore += 400;
        }
        if (this.level == 3) {
            contadorScore += 800;
        }
        if (this.level == 4) {
            contadorScore += 1000;
        }
        if (this.level > 4) {
            contadorScore += 1500;
        }

    }

    public void moverCadradosChan(int linea) {

        Iterator<Cadrado> iteratorChan3 = cadradosChan.iterator();
        while (iteratorChan3.hasNext()) {

            Cadrado cadradoABaixar = iteratorChan3.next();
            if (cadradoABaixar.getY() < linea) {
                cadradoABaixar.getLblCadrado().setLocation(cadradoABaixar.getLblCadrado().getX(), cadradoABaixar.getLblCadrado().getY() + Xogo.LADO_CADRADO);
            }
        }
    }

    public void comprobarLineasCompletas() {
        this.timerComprobarLineas = new Timer(1000, (ActionEvent e) -> {
            try {
                this.borrarLinasCompletas();

            } catch (ConcurrentModificationException ex) {
                System.out.println("SE ESTÁ MODIFICANDO EL ARRAYLIST MIENTRAS SE ITERA ");
            }

        });
    }

    public boolean comprobarFinalPartida() {
        boolean gameOver = false;
        Iterator<Cadrado> iteratorChan4 = cadradosChan.listIterator();
        while (iteratorChan4.hasNext()) {
            Cadrado cadradoChan = iteratorChan4.next();

            if (cadradoChan.getLblCadrado().getY() == 0) {
                gameOver = true;
                pararTimers();
                gameOver();
            }
        }
        return gameOver;
    }

    public void pararTimers() {
        ventanaPrincipal.timerScore.stop();
        ventanaPrincipal.timer.stop();
//        this.timerComprobarLineas.stop();
    }

    public static void playGameOverMusic(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                cliper = AudioSystem.getClip();
                cliper.open(audioInput);
                cliper.start();
            } else {
                System.out.println("No se encontró el archivo");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void playSound(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                cliper = AudioSystem.getClip();
                cliper.open(audioInput);
                cliper.start();
            } else {
                System.out.println("No se encontró el archivo");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void actualizarDelays(int delay) {
        ventanaPrincipal.timerScore.setDelay(delay);
//        this.timerComprobarLineas.setDelay(delay);
        ventanaPrincipal.timer.setDelay(delay);
    }

    public void gameOver() {
        ventanaPrincipal.getPanelGameOver().setVisible(true);

    }

    //SETTERs AND GETTERs 
    public boolean isPausa() {
        return pausa;
    }

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    public int getNumeroLineas() {
        return numeroLineas;
    }

    public void setNumeroLineas(int numeroLineas) {
        this.numeroLineas = numeroLineas;
    }

    public VentanaPrincipal getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public Ficha getFichaActual() {
        return fichaActual;
    }

    public void setFichaActual(Ficha fichaActual) {
        this.fichaActual = fichaActual;
    }

}
