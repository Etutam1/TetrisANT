/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaqueteModelo;

import PaqueteIU.VentanaPrincipal;
//import static PaqueteIU.VentanaPrincipal.cliper;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author matut
 */
public class Xogo {

    //ATRIBUTOS
    private static int LADO_CADRADO = 40;
    private int MAX_X;
    private int MIN_Y;
    private int MAX_Y;
    private int MIN_X;
    private boolean pausa;
    private VentanaPrincipal ventanaPrincipal;
    private Ficha fichaActual;
    private ArrayList<Cadrado> cadradosChan = new ArrayList<>();
    private ArrayList<Xogador> xogadores = new ArrayList<>();
    private Timer timerComprobarLineas;
    private int level;
    private int contadorScore;
    private int numeroLineas;

    //CONSTRUCTOR
    public Xogo(int level, boolean pausa, VentanaPrincipal ventanaPrincipal) {

        this.MAX_X = 400;
        this.MIN_X = 0;
        this.MAX_Y = 800;
        this.MIN_Y = 0;
        this.pausa = pausa;
        this.contadorScore = 0;
        this.numeroLineas = 0;
        this.ventanaPrincipal = ventanaPrincipal;
        this.level = level;
        reproducirMusicaPartida();

    }

    public boolean ePosicionValida(int x, int y) {
        boolean posicionValida = false;
        posicionValida = comprobarMargenes(x, y, posicionValida);
        posicionValida = comprobarChocaFicha(x, y, posicionValida);
        return posicionValida;
    }

    private boolean comprobarChocaFicha(int x, int y, boolean posicionValida) {
        Iterator<Cadrado> iteratorChan5 = this.cadradosChan.iterator();
        while (iteratorChan5.hasNext()) {
            Cadrado cadradoComprobado = iteratorChan5.next();
            if (cadradoComprobado.getLblCadrado().getX() == x && cadradoComprobado.getLblCadrado().getY() == y) {
                posicionValida = false;
            }
        }
        return posicionValida;
    }

    private boolean comprobarMargenes(int x, int y, boolean posicionValida) {
        if (x < this.MAX_X && x >= this.MIN_X && y < this.MAX_Y && y >= this.MIN_Y) {
            posicionValida = true;
        }
        return posicionValida;
    }

    public void moverFichaAbaixo() {
        if (!this.comprobarFinalPartida()) {
            if (this.chocaFichaCoChan()) {
                this.engadirFichaAoChan();
                this.reproducirSonidoChocaChan();
                this.xenerarNovaFicha();
            } else {
                this.fichaActual.moverAbaixo();
            }
        }
    }

    public void moverFichaDereita() {
        boolean podeMover = true;
        podeMover = comprobarCadradoDereita(podeMover);
        if (podeMover) {
            this.fichaActual.moverDereita();
        }
    }

    private boolean comprobarCadradoDereita(boolean podeMover) {
        Iterator<Cadrado> iterator = this.fichaActual.getCadrados().iterator();
        while (iterator.hasNext()) {
            Cadrado actual = iterator.next();

            if (!this.ePosicionValida(actual.getLblCadrado().getX() + Xogo.LADO_CADRADO, actual.getLblCadrado().getY())) {
                podeMover = false;
            }
        }
        return podeMover;
    }

    public void moverFichaEsquerda() { 
        boolean podeMover = true;
        
        podeMover = comprobarCadradoEsquerda(podeMover);
        if (podeMover) {
            this.fichaActual.moverEsquerda();
        }
    }

    private boolean comprobarCadradoEsquerda(boolean podeMover) {
        Iterator<Cadrado> iterator2 = this.fichaActual.getCadrados().iterator();
        while (iterator2.hasNext()) {
            Cadrado actual = iterator2.next();

            if (!this.ePosicionValida(actual.getLblCadrado().getX() - Xogo.LADO_CADRADO, actual.getLblCadrado().getY())) {
                podeMover = false;
            }
        }
        return podeMover;
    }

    public void RotarFicha() {
        this.fichaActual.rotar();
    }

    public void xenerarNovaFicha() {
        int comprobante = 0;
        int numAleatorio = (int) (Math.random() * 7 + 1);

        if (comprobante == numAleatorio) {
            numAleatorio = (int) (Math.random() * 7 + 1);
        }
        if (numAleatorio == 1) {
            this.fichaActual = new FichaCadrada(this);
            comprobante = numAleatorio;
        }
        if (numAleatorio == 2) {
            this.fichaActual = new FichaBarra(this);
            comprobante = numAleatorio;
        }
        if (numAleatorio == 3) {
            this.fichaActual = new FichaZ(this);
            comprobante = numAleatorio;
        }
        if (numAleatorio == 4) {
            this.fichaActual = new FichaZInversa(this);
            comprobante = numAleatorio;
        }
        if (numAleatorio == 5) {
            this.fichaActual = new FichaT(this);
            comprobante = numAleatorio;
        }
        if (numAleatorio == 6) {
            this.fichaActual = new FichaL(this);
            comprobante = numAleatorio;
        }
        if (numAleatorio == 7) {
            this.fichaActual = new FichaLInversa(this);
            comprobante = numAleatorio;
        }

        this.pintarFicha();
    }

    public void pintarFicha() {
        Iterator<Cadrado> iterator3 = this.fichaActual.getCadrados().iterator();

        while (iterator3.hasNext()) {
            this.ventanaPrincipal.pintarCadrado(iterator3.next().getLblCadrado());
        }
    }

    public boolean chocaFichaCoChan() {
        boolean tocaChan = false;
        tocaChan = comprobarFichaAbaixo(tocaChan);
        return tocaChan;
    }

    private boolean comprobarFichaAbaixo(boolean tocaChan) {
        Iterator<Cadrado> iterator4 = this.fichaActual.getCadrados().iterator();
        while (iterator4.hasNext()) {
            Cadrado cadradoActual = iterator4.next();
            if (!this.ePosicionValida(cadradoActual.getLblCadrado().getX(), cadradoActual.getLblCadrado().getY() + LADO_CADRADO)) {
                tocaChan = true;
            }
        }
        return tocaChan;
    }

    public void engadirFichaAoChan() {
        this.cadradosChan.addAll(this.fichaActual.getCadrados());
    }

    public void borrarLinasCompletas() throws ConcurrentModificationException {
        int ultimaLinea = this.MAX_Y - Xogo.LADO_CADRADO;
        int primeraLinea = this.MIN_Y;
        ArrayList<Integer> coordsYLineas = new ArrayList<>();

        for (int y = ultimaLinea; y >= primeraLinea; y -= Xogo.LADO_CADRADO) {
            Iterator<Cadrado> iteratorChan = this.cadradosChan.listIterator();
            int contadorCadrados = 0;
            System.out.println("y: " + y);

            while (iteratorChan.hasNext()) {
                Cadrado cadradoChan = iteratorChan.next();

                if (cadradoChan.getLblCadrado().getY() == y) {
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
            this.moverCadradosChan(linea);
        }

    }

    public void borrarLina(int linea) {
        // PENDIENTE DE REVISAR Y LIMPIAR 
        ArrayList<Cadrado> cadradosBorrar = new ArrayList<>();
        Iterator<Cadrado> iteratorChan2 = this.cadradosChan.listIterator();

        while (iteratorChan2.hasNext()) {
            Cadrado cadradoABorrar = iteratorChan2.next();
            if (cadradoABorrar.getLblCadrado().getY() == linea) {
                cadradosBorrar.add(cadradoABorrar);
                this.ventanaPrincipal.borrarCadrado(cadradoABorrar.getLblCadrado());
            }
        }
        this.cadradosChan.removeAll(cadradosBorrar);
        this.sumarNumeroLineas();
        this.ventanaPrincipal.mostrarNumeroLineas(this.numeroLineas);
        this.sumarScorePorLineaCompleta();
        this.comprobarCambioLevel();
        this.reproducirSonidoBorrarLinea();
    }

    private void moverCadradosChan(int linea) {

        Iterator<Cadrado> iteratorChan3 = getCadradosChan().iterator();
        while (iteratorChan3.hasNext()) {

            Cadrado cadradoABaixar = iteratorChan3.next();
            if (cadradoABaixar.getY() < linea) {
                cadradoABaixar.getLblCadrado().setLocation(cadradoABaixar.getLblCadrado().getX(), cadradoABaixar.getLblCadrado().getY() + Xogo.LADO_CADRADO);
            }
        }
    }

    private void aumentarLevel() {
        this.level++;
    }

    private void comprobarCambioLevel() {
        int delayActual = this.ventanaPrincipal.getTimer().getDelay();
        double incrementoDelay = 0.75;

        if (this.getNumeroLineas() % 5 == 0) {
            this.aumentarLevel();
            this.ventanaPrincipal.mostrarLevel(this.level);
            this.actualizarDelays((int) (delayActual * incrementoDelay));
        }
    }

    private void sumarNumeroLineas() {
        this.numeroLineas++;

    }

    public void incrementarContadorScore() {
        this.contadorScore++;
    }

    private void sumarScorePorLineaCompleta() {

        if (this.level == 0 || this.level == 1) {
            contadorScore += 100;
        }
        if (this.level == 2 || this.level == 3) {
            contadorScore += 300;
        }
        if (this.level == 4 || this.level == 5) {
            contadorScore += 500;
        }
        if (this.level == 6 || this.level == 7) {
            contadorScore += 700;
        }
        if (this.level == 8 || this.level == 9 || this.level == 10) {
            contadorScore += 1000;
        }
        if (this.level > 10) {
            contadorScore += 2000;
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
        this.timerComprobarLineas.start();
    }

    private boolean comprobarFinalPartida() {
        boolean gameOver = false;
        Iterator<Cadrado> iteratorChan4 = this.cadradosChan.listIterator();
        while (iteratorChan4.hasNext()) {
            Cadrado cadradoChan = iteratorChan4.next();

            if (cadradoChan.getLblCadrado().getY() == 0) {
                gameOver = true;

            }
            if (gameOver) {
                this.ventanaPrincipal.mostrarFinDoXogo();
                this.reproducirMusicaGameOver();
            }
        }
        return gameOver;
    }

    private void actualizarDelays(int delay) {

        this.timerComprobarLineas.setDelay(delay);
        this.ventanaPrincipal.getTimer().setDelay(delay);
    }

    public void gestionarResultados() {
            this.guardarResultados();
            this.leerResultados();
            this.ordenarJugadoresPorScore();
            this.agregarDatosTabla();
            this.ajustarTamañoTabla();
        }
    
    
    private void guardarResultados() {
        PrintWriter salida = null;
//        
        String jugadorScore = this.ventanaPrincipal.getNombreJugadorLabel().getText() + "-" + this.contadorScore + "\n";
        try {
            salida = new PrintWriter(new FileWriter("PlayerScore.txt", true));
            salida.write(jugadorScore);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO GUARDAR LOS RESULTADOS");
        } finally {

            if (salida != null) {
                salida.close();
            }
        }
    }

    private void leerResultados() {
        FileReader entrada = null;
        Scanner scanner = null;
        try {
            entrada = new FileReader("PlayerScore.txt");
            scanner = new Scanner(entrada);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Xogador player = new Xogador(linea);
                this.agregarJugador(player);
            }

//            scanner.close();
//            entrada.close();
        } catch (IOException e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO LEER LOS RESULTADOS");
        } finally {
            if (entrada != null) {
                scanner.close();
                try {
                    entrada.close();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    private void agregarJugador(Xogador jugador) {
        this.xogadores.add(jugador);
    }

    private void ordenarJugadoresPorScore() {
        Collections.sort(this.xogadores, new Comparator<Xogador>() {
            @Override
            public int compare(Xogador j1, Xogador j2) {
                return j2.getScore() - j1.getScore();
            }
        });
    }

    private void agregarDatosTabla() {
        DefaultTableModel model = crearFilaEnBlanco();
        
        Iterator<Xogador> iteratorJugadores = this.xogadores.listIterator();
        while (iteratorJugadores.hasNext()) {
            Xogador jugadorActual = iteratorJugadores.next();
            Object[] row = {jugadorActual.getNombre(), jugadorActual.getScore()};
            model.addRow(row);

        }
    }

    private DefaultTableModel crearFilaEnBlanco() {
        DefaultTableModel model = (DefaultTableModel) this.ventanaPrincipal.getScoresTable().getModel();
        model.setRowCount(1);
        return model;
    }

    private void ajustarTamañoTabla() {
        Dimension dim = this.ventanaPrincipal.getScoresTable().getPreferredSize();
        int alturaFilas = this.ventanaPrincipal.getScoresTable().getRowHeight();
        int totalFilas = this.ventanaPrincipal.getScoresTable().getRowCount();
        dim.height = alturaFilas * (totalFilas + 1);
        this.ventanaPrincipal.getScoresTable().setPreferredScrollableViewportSize(dim);

    }

    private void reproducirSonido(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                this.ventanaPrincipal.setCliper(AudioSystem.getClip());
                this.ventanaPrincipal.getCliper().open(audioInput);
                this.ventanaPrincipal.getCliper().start();
            } else {
                System.out.println("No se encontró el archivo");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void reproducirMusicaGameOver() {
        String sonidoGameOverPath = "src\\Resources\\Musica\\gameover.wav";
        this.reproducirSonido(sonidoGameOverPath);
    }

    private void reproducirSonidoBorrarLinea() {
        String sonidoLineaPath = "src\\\\Resources\\\\Musica\\\\poom.wav";
        this.reproducirSonido(sonidoLineaPath);
    }

    private void reproducirSonidoChocaChan() {
        String sonidoChanPath = "src\\\\Resources\\\\Musica\\\\pop.wav";
        this.reproducirSonido(sonidoChanPath);
    }

    private void reproducirMusicaPartida() {
        String sonidoPartidaPath = "src\\Resources\\Musica\\juego.wav";
        this.reproducirSonido(sonidoPartidaPath);
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

    public void setfichaActual(Ficha fichaActual) {
        this.fichaActual = fichaActual;
    }

    /**
     * @return the LADO_CADRADO
     */
    public static int getLADO_CADRADO() {
        return LADO_CADRADO;
    }

    /**
     * @param aLADO_CADRADO the LADO_CADRADO to set
     */
    public static void setLADO_CADRADO(int aLADO_CADRADO) {
        LADO_CADRADO = aLADO_CADRADO;
    }

    /**
     * @return the MAX_X
     */
    public int getMAX_X() {
        return MAX_X;
    }

    /**
     * @param MAX_X the MAX_X to set
     */
    public void setMAX_X(int MAX_X) {
        this.MAX_X = MAX_X;
    }

    /**
     * @return the MIN_Y
     */
    public int getMIN_Y() {
        return MIN_Y;
    }

    /**
     * @param MIN_Y the MIN_Y to set
     */
    public void setMIN_Y(int MIN_Y) {
        this.MIN_Y = MIN_Y;
    }

    /**
     * @return the MAX_Y
     */
    public int getMAX_Y() {
        return MAX_Y;
    }

    /**
     * @param MAX_Y the MAX_Y to set
     */
    public void setMAX_Y(int MAX_Y) {
        this.MAX_Y = MAX_Y;
    }

    /**
     * @return the MIN_X
     */
    public int getMIN_X() {
        return MIN_X;
    }

    /**
     * @param MIN_X the MIN_X to set
     */
    public void setMIN_X(int MIN_X) {
        this.MIN_X = MIN_X;
    }

    /**
     * @return the comprobante
     */
    /**
     * @return the cadradosChan
     */
    public ArrayList<Cadrado> getCadradosChan() {
        return cadradosChan;
    }

    /**
     * @param cadradosChan the cadradosChan to set
     */
    public void setCadradosChan(ArrayList<Cadrado> cadradosChan) {
        this.cadradosChan = cadradosChan;
    }

    /**
     * @return the xogadores
     */
    public ArrayList<Xogador> getXogadores() {
        return xogadores;
    }

    /**
     * @param xogadores the xogadores to set
     */
    public void setXogadores(ArrayList<Xogador> xogadores) {
        this.xogadores = xogadores;
    }

    /**
     * @return the timerComprobarLineas
     */
    public Timer getTimerComprobarLineas() {
        return timerComprobarLineas;
    }

    /**
     * @param timerComprobarLineas the timerComprobarLineas to set
     */
    public void setTimerComprobarLineas(Timer timerComprobarLineas) {
        this.timerComprobarLineas = timerComprobarLineas;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the contadorScore
     */
    public int getContadorScore() {
        return contadorScore;
    }

    /**
     * @param contadorScore the contadorScore to set
     */
    public void setContadorScore(int contadorScore) {
        this.contadorScore = contadorScore;
    }

}
