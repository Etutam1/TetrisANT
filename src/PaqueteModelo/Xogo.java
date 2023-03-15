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
    private int comprobante = 0;
    private boolean pausa;
    private VentanaPrincipal ventanaPrincipal;
    private Ficha fichaActual;
    private ArrayList<Cadrado> cadradosChan = new ArrayList<>();
    private ArrayList<Xogador> xogadores = new ArrayList<>();
    private Timer timerComprobarLineas;
    private int level;
    private int contadorScore = 0;
    private int numeroLineas = 0;

    //CONSTRUCTOR
    public Xogo(int level, boolean pausa, VentanaPrincipal ventanaPrincipal) {

        this.MAX_X = 400;
        this.MIN_X = 0;
        this.MAX_Y = 800;
        this.MIN_Y = 0;
        this.pausa = pausa;
        this.ventanaPrincipal = ventanaPrincipal;
        this.level=level;
        reproducirMusicaPartida();

    }

    public boolean ePosicionValida(int x, int y) {
        boolean posicionValida = false;
        if (x < this.MAX_X && x >= this.MIN_X && y < this.MAX_Y && y >= this.MIN_Y) {
            posicionValida = true;
        }
        Iterator<Cadrado> iteratorChan5 = this.cadradosChan.iterator();
        while (iteratorChan5.hasNext()) {
            Cadrado cadradoComprobado = iteratorChan5.next();
            if (cadradoComprobado.getLblCadrado().getX() == x && cadradoComprobado.getLblCadrado().getY() == y) {
                posicionValida = false;
            }
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
        Iterator<Cadrado> iterator = this.fichaActual.getCadrados().iterator();
        boolean podeMover = true;

        while (iterator.hasNext()) {
            Cadrado actual = iterator.next();

            if (!this.ePosicionValida(actual.getLblCadrado().getX() + Xogo.LADO_CADRADO, actual.getLblCadrado().getY())) {
                podeMover = false;
            }
        }
        if (podeMover) {
            this.fichaActual.moverDereita();
        }
    }

    public void moverFichaEsquerda() {
        Iterator<Cadrado> iterator2 = this.fichaActual.getCadrados().iterator();
        boolean podeMover = true;

        while (iterator2.hasNext()) {
            Cadrado actual = iterator2.next();

            if (!this.ePosicionValida(actual.getLblCadrado().getX() - Xogo.LADO_CADRADO, actual.getLblCadrado().getY())) {
                podeMover = false;
            }
        }
        if (podeMover) {
            this.fichaActual.moverEsquerda();
        }
    }

    public void RotarFicha() {
        this.fichaActual.rotar();
    }

    public void xenerarNovaFicha() {

        int numAleatorio = (int) (Math.random() * 7 + 1);

        if (getComprobante() == numAleatorio) {
            numAleatorio = (int) (Math.random() * 7 + 1);
        }
        if (numAleatorio == 1) {
            setFichaActual(new FichaCadrada(this));
            setComprobante(numAleatorio);
        }
        if (numAleatorio == 2) {
            setFichaActual(new FichaBarra(this));
            setComprobante(numAleatorio);
        }
        if (numAleatorio == 3) {
            setFichaActual(new FichaZ(this));
            setComprobante(numAleatorio);
        }
        if (numAleatorio == 4) {
            setFichaActual(new FichaZInversa(this));
            setComprobante(numAleatorio);
        }
        if (numAleatorio == 5) {
            setFichaActual(new FichaT(this));
            setComprobante(numAleatorio);
        }
        if (numAleatorio == 6) {
            setFichaActual(new FichaL(this));
            setComprobante(numAleatorio);
        }
        if (numAleatorio == 7) {
            setFichaActual(new FichaLInversa(this));
            setComprobante(numAleatorio);
        }

        this.pintarFicha();
    }

    public void pintarFicha() {
        Iterator<Cadrado> iterator3 = this.fichaActual.getCadrados().iterator();

        while (iterator3.hasNext()) {
            getVentanaPrincipal().pintarCadrado(iterator3.next().getLblCadrado());
        }
    }

    public boolean chocaFichaCoChan() {
        boolean tocaChan = false;
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
        int ultimaLinea = this.MAX_Y - Xogo.getLADO_CADRADO();
        int primeraLinea = this.MIN_Y;
        ArrayList<Integer> coordsYLineas = new ArrayList<>();

        for (int y = ultimaLinea; y >= primeraLinea; y -= getLADO_CADRADO()) {
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

        ArrayList<Cadrado> cadradosBorrar = new ArrayList<>();
        Iterator<Cadrado> iteratorChan2 = this.cadradosChan.listIterator();

        while (iteratorChan2.hasNext()) {
            Cadrado cadradoABorrar = iteratorChan2.next();
            if (cadradoABorrar.getLblCadrado().getY() == linea) {
                cadradosBorrar.add(cadradoABorrar);
                getVentanaPrincipal().borrarCadrado(cadradoABorrar.getLblCadrado());
            }
        }
        this.cadradosChan.removeAll(cadradosBorrar);
        this.sumarNumeroLineas();

        this.ventanaPrincipal.mostrarNumeroLineas(this.numeroLineas);
        sumarScorePorLineaCompleta();
        comprobarCambioLevel();
        reproducirSonidoBorrarLinea();
    }

    public void moverCadradosChan(int linea) {

        Iterator<Cadrado> iteratorChan3 = this.cadradosChan.iterator();
        while (iteratorChan3.hasNext()) {

            Cadrado cadradoABaixar = iteratorChan3.next();
            if (cadradoABaixar.getY() < linea) {
                cadradoABaixar.getLblCadrado().setLocation(cadradoABaixar.getLblCadrado().getX(), cadradoABaixar.getLblCadrado().getY() + Xogo.getLADO_CADRADO());
            }
        }
    }

    private void aumentarLevel() {
        this.setLevel(this.getLevel() + 1);
    }

    private void comprobarCambioLevel() {
        if (this.getNumeroLineas() % 5 == 0) {
            aumentarLevel();
            this.ventanaPrincipal.mostrarLevel(this.level);
            actualizarDelays((int) (getVentanaPrincipal().getTimer().getDelay() * 0.75));
        }
    }

    private void sumarNumeroLineas() {
        setNumeroLineas(getNumeroLineas() + 1);

    }
   public void incrementarContadorScore(){
       contadorScore++;
   }

    private void sumarScorePorLineaCompleta() {
        if (this.getLevel() == 0) {
            setContadorScore(getContadorScore() + 100);
        }
        if (this.getLevel() == 1) {
            setContadorScore(getContadorScore() + 200);
        }
        if (this.getLevel() == 2) {
            setContadorScore(getContadorScore() + 400);
        }
        if (this.getLevel() == 3) {
            setContadorScore(getContadorScore() + 800);
        }
        if (this.getLevel() == 4) {
            setContadorScore(getContadorScore() + 1000);
        }
        if (this.getLevel() > 4) {
            setContadorScore(getContadorScore() + 1500);
        }

    }

    public void comprobarLineasCompletas() {
        this.setTimerComprobarLineas(new Timer(1000, (ActionEvent e) -> {
            try {
                this.borrarLinasCompletas();
            } catch (ConcurrentModificationException ex) {
                System.out.println("SE ESTÁ MODIFICANDO EL ARRAYLIST MIENTRAS SE ITERA ");
            }
        }));
        getTimerComprobarLineas().start();
    }

    private boolean comprobarFinalPartida() {
        boolean gameOver = false;
        Iterator<Cadrado> iteratorChan4 = this.cadradosChan.listIterator();
        while (iteratorChan4.hasNext()) {
            Cadrado cadradoChan = iteratorChan4.next();

            if (cadradoChan.getLblCadrado().getY() == 0) {
                gameOver = true;
                ventanaPrincipal.mostrarFinDoXogo();
                reproducirMusicaGameOver();
            }
        }
        return gameOver;
    }

    

    

    private void actualizarDelays(int delay) {
        
        this.getTimerComprobarLineas().setDelay(delay);
        getVentanaPrincipal().getTimer().setDelay(delay);
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

        String jugadorScore = ventanaPrincipal.getNombreJugadorLabel().getText() + "-" + this.getContadorScore() + "\n";
        try {
            salida = new PrintWriter(new FileWriter("PlayerScore.txt", true));
            salida.write(jugadorScore);

        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
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
                Xogador xogador = new Xogador(linea);
                this.agregarJugador(xogador);
            }

            scanner.close();
            entrada.close();
        } catch (IOException e) {
            e.printStackTrace();
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
    private void agregarJugador(Xogador player) {
        getJugadores().add(player);
    }

    private void ordenarJugadoresPorScore() {
        Collections.sort(getXogadores(), new Comparator<Xogador>() {
            @Override
            public int compare(Xogador j1, Xogador j2) {
                return j2.getScore() - j1.getScore();
            }
        });
    }

    private void agregarDatosTabla() {
        Iterator<Xogador> iteratorJugadores = getJugadores().listIterator();
        DefaultTableModel model = (DefaultTableModel) getVentanaPrincipal().getScoresTable().getModel();
        model.setRowCount(1);
        while (iteratorJugadores.hasNext()) {
            Xogador jugadorActual = iteratorJugadores.next();

            Object[] row = {jugadorActual.getNombre(), jugadorActual.getScore()};
            model.addRow(row);
            
        }
        
    }

    private void ajustarTamañoTabla() {
        Dimension dim = getVentanaPrincipal().getScoresTable().getPreferredSize();
        int alturaFilas = getVentanaPrincipal().getScoresTable().getRowHeight();
        int totalFilas = getVentanaPrincipal().getScoresTable().getRowCount();
        dim.height = alturaFilas * (totalFilas + 1);
        getVentanaPrincipal().getScoresTable().setPreferredScrollableViewportSize(dim);
        
    }
    
    private  void reproducirSonido(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                ventanaPrincipal.setCliper(AudioSystem.getClip());
                ventanaPrincipal.getCliper().open(audioInput);
                ventanaPrincipal.getCliper().start();
            } else {
                System.out.println("No se encontró el archivo");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    

    private void reproducirMusicaGameOver() {
        String sonidoGameOverPath = "src\\Resources\\Musica\\gameover.wav";
        reproducirSonido(sonidoGameOverPath);
    }

    private void reproducirSonidoBorrarLinea() {
        String sonidoLineaPath = "src\\\\Resources\\\\Musica\\\\poom.wav";
        reproducirSonido(sonidoLineaPath);
    }

    private void reproducirSonidoChocaChan() {
        String sonidoChanPath = "src\\\\Resources\\\\Musica\\\\pop.wav";
        reproducirSonido(sonidoChanPath);
    }

    private void reproducirMusicaPartida() {
        String sonidoPartidaPath = "src\\Resources\\Musica\\juego.wav";
        reproducirSonido(sonidoPartidaPath);
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

    public ArrayList<Xogador> getJugadores() {
        return getXogadores();
    }

    public void setJugadores(ArrayList<Xogador> jugadores) {
        this.setXogadores(jugadores);
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
    public int getComprobante() {
        return comprobante;
    }

    /**
     * @param comprobante the comprobante to set
     */
    public void setComprobante(int comprobante) {
        this.comprobante = comprobante;
    }

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
