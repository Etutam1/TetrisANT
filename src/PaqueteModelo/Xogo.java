/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaqueteModelo;

import PaqueteIU.VentanaPrincipal;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
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
import javax.swing.JOptionPane;
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
    private int level;
    private int contadorScore;
    private int numeroLineas;
    private int comprobante = 0;
    private Sonido sonido = new Sonido();
    private boolean finXogo;
    private ReportException report = new ReportException();

    //CONSTRUCTOR
    public Xogo(int level, boolean pausa, VentanaPrincipal ventanaPrincipal) {

        this.ventanaPrincipal = ventanaPrincipal;
        this.MAX_X = this.ventanaPrincipal.getPanelJuego().getWidth();
        this.MIN_X = this.ventanaPrincipal.getPanelJuego().getWidth() - this.ventanaPrincipal.getPanelJuego().getWidth();
        this.MAX_Y = this.ventanaPrincipal.getPanelJuego().getHeight();
        this.MIN_Y = this.ventanaPrincipal.getPanelJuego().getHeight() - this.ventanaPrincipal.getPanelJuego().getHeight();
        this.pausa = pausa;
        this.contadorScore = 0;
        this.numeroLineas = 0;
        this.level = level;
        this.finXogo = false;

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
                sonido.reproducirMusica("chocar");
                this.xenerarNovaFicha();
            } else {
                this.fichaActual.moverAbaixo();
            }
        } else {
            this.ventanaPrincipal.mostrarFinDoXogo();
        }
    }

    public void moverFichaDereita() {

        if (comprobarCadradoDereita()) {
            this.fichaActual.moverDereita();
        }
    }

    private boolean comprobarCadradoDereita() {
        boolean podeMover = true;
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

        if (comprobarCadradoEsquerda()) {
            this.fichaActual.moverEsquerda();
        }
    }

    private boolean comprobarCadradoEsquerda() {
        boolean podeMover = true;
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
        int numAleatorio = crearNumeroAleatorioDistinto();
        try {
            this.fichaActual = crearFicha(numAleatorio);
        } catch (IllegalArgumentException ex) {
            this.report.reportarException(ex);
        }
        this.comprobante = numAleatorio; // ACTUALIZA EL COMPROBANTE
        this.pintarFicha();
    }

    private Ficha crearFicha(int numFicha) throws IllegalArgumentException {
        switch (numFicha) {
            case 1:
                return new FichaCadrada(this);
            case 2:
                return new FichaBarra(this);
            case 3:
                return new FichaZ(this);
            case 4:
                return new FichaZInversa(this);
            case 5:
                return new FichaT(this);
            case 6:
                return new FichaL(this);
            case 7:
                return new FichaLInversa(this);
            default:
                throw new IllegalArgumentException("Número de ficha inválido: " + numFicha); //SI RECIBE UN NUMERO INVALIDO LANZA LA EXCEPCION
        }
    }

    private int crearNumeroAleatorioDistinto() {
        int numAleatorio;
        do {
            numAleatorio = (int) (Math.random() * 7 + 1);
        } while (this.comprobante == numAleatorio); // REPITE HASTA QUE EL NUMERO ALEATORIO SEA DIFERENTE AL ANTERIOR
        return numAleatorio;
    }

    public void pintarFicha() {
        Iterator<Cadrado> iterator3 = this.fichaActual.getCadrados().iterator();

        while (iterator3.hasNext()) {
            this.ventanaPrincipal.pintarCadrado(iterator3.next().getLblCadrado());
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

    public void borrarLineasCompletas() throws ConcurrentModificationException {
        int ultimaLinea = this.MAX_Y - Xogo.LADO_CADRADO;
        int primeraLinea = this.MIN_Y;
        boolean borraLinea = false;
        ArrayList<Integer> coordsYLineas = new ArrayList<>();

        ComprobarLineasCompletas(ultimaLinea, primeraLinea, coordsYLineas);
        borraLinea = borrarLineasArrayYs(coordsYLineas, borraLinea);
        gestionarBorradoLinea(borraLinea);

    }

    public void gestionarBorradoLinea(boolean borraLinea) {
        if (borraLinea) {
            this.sumarNumeroLineas();
            this.ventanaPrincipal.mostrarNumeroLineas(this.numeroLineas);
            this.comprobarCambioLevel();
            this.sumarScorePorLineaCompleta();
            sonido.reproducirMusica("borrar");
        }
    }

    public boolean borrarLineasArrayYs(ArrayList<Integer> coordsYLineas, boolean borraLinea) {
        Iterator iteratorYs = coordsYLineas.iterator();
        while (iteratorYs.hasNext()) {
            int linea = (int) iteratorYs.next();
            if (this.borrarLina(linea)) {
                this.moverCadradosChan(linea);
                borraLinea = true;
            }
            
        }
        return borraLinea;
    }

    public void ComprobarLineasCompletas(int ultimaLinea, int primeraLinea, ArrayList<Integer> coordsYLineas) {
        for (int y = ultimaLinea; y >= primeraLinea; y -= Xogo.LADO_CADRADO) {
            Iterator<Cadrado> iteratorChan = this.cadradosChan.listIterator();
            int contadorCadrados = 0;
            
            while (iteratorChan.hasNext()) {
                Cadrado cadradoChan = iteratorChan.next();
                
                if (cadradoChan.getLblCadrado().getY() == y) {
                    contadorCadrados++;
                    
                    if (contadorCadrados == 10) {
                        coordsYLineas.add(y);
                    }
                }
            }
        }
    }

    public boolean borrarLina(int linea) {

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
        
        return true;
    }

    private void moverCadradosChan(int linea) {
        Iterator<Cadrado> iteratorChan3 = this.cadradosChan.iterator();
        while (iteratorChan3.hasNext()) {

            Cadrado cadradoABaixar = iteratorChan3.next();
            if (cadradoABaixar.getY() < linea) {

                cadradoABaixar.getLblCadrado().setLocation(cadradoABaixar.getLblCadrado().getX(), cadradoABaixar.getLblCadrado().getY() + Xogo.LADO_CADRADO);
                actualizarCoordsCadrado(cadradoABaixar);

                
            }
        }
    }

    private void actualizarCoordsCadrado(Cadrado cadrado) {
        cadrado.setX(cadrado.getLblCadrado().getX());
        cadrado.setY(cadrado.getLblCadrado().getY());
    }

    private void aumentarLevel() {
        this.level++;
    }

    private void comprobarCambioLevel() {

        if (this.getNumeroLineas() % 5 == 0) {
            cambiarDeLevel();
        }
    }

    private void cambiarDeLevel() {
        int delayActual = this.ventanaPrincipal.getTimer().getDelay();
        double decrementoDelay = 0.75;
        this.aumentarLevel();
        this.ventanaPrincipal.mostrarLevel(this.level);
        this.actualizarDelay((int) (delayActual * decrementoDelay));
    }

    private void sumarNumeroLineas() {
        this.numeroLineas++;

    }

    private void sumarScorePorLineaCompleta() {

        if (this.level == 0 || this.level == 1) {
            contadorScore += 100;
        } else if (this.level == 2 || this.level == 3) {
            contadorScore += 300;
        } else if (this.level == 4 || this.level == 5) {
            contadorScore += 500;
        } else if (this.level == 6 || this.level == 7) {
            contadorScore += 700;
        } else if (this.level == 8 || this.level == 9 || this.level == 10) {
            contadorScore += 1000;
        } else if (this.level > 10) {
            contadorScore += 2000;
        }
    }

    private boolean comprobarFinalPartida() {
        Iterator<Cadrado> iteratorChan4 = this.cadradosChan.listIterator();
        while (iteratorChan4.hasNext() && !this.finXogo) {
            Cadrado cadradoChan = iteratorChan4.next();

            if (cadradoChan.getLblCadrado().getY() == 0) {
                this.finXogo = true;
            }
        }

        return finXogo;
    }

    private void actualizarDelay(int delay) {
        this.ventanaPrincipal.getTimer().setDelay(delay);
    }

    private void crearCadradoAleatorio() {

        Cadrado cadradoAleatorio = xenerarCadradoPosicionAleatoria();
        this.ventanaPrincipal.pintarCadrado(cadradoAleatorio.getLblCadrado());
        this.cadradosChan.add(cadradoAleatorio);
    }

    private Cadrado xenerarCadradoPosicionAleatoria() {
        int ultimaLinea = this.getMAX_Y() - LADO_CADRADO;
        Cadrado cadradoAleatorio = new Cadrado(this.xenerarPosicionXAleatoria(), ultimaLinea, Color.GRAY);
        return cadradoAleatorio;
    }

    private int xenerarPosicionXAleatoria() {
        int[] posiblesCoordX = {0, 40, 80, 120, 160, 200, 240, 280, 320, 360};
        int numAleatorio = (int) (Math.random() * 9 + 1);
        return posiblesCoordX[numAleatorio];
    }

    private void subirChan() {
        Iterator<Cadrado> iteratorChan = cadradosChan.iterator();
        while (iteratorChan.hasNext()) {
            Cadrado cadradoSubir = iteratorChan.next();
            cadradoSubir.getLblCadrado().setLocation(cadradoSubir.getLblCadrado().getX(), cadradoSubir.getLblCadrado().getY() - LADO_CADRADO);
            this.actualizarCoordsCadrado(cadradoSubir);
        }
    }

    public void agregarCadradoAleatorio() {
        this.subirChan();
        this.crearCadradoAleatorio();
        this.sonido.reproducirMusica("cadradoAleatorio");
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
        String jugadorScore = this.ventanaPrincipal.getNombreJugadorLabel().getText() + "-" + this.contadorScore + "\n";
        try {
            salida = new PrintWriter(new FileWriter("PlayerScore.txt", true));
            salida.write(jugadorScore);

        } catch (FileNotFoundException ex1) {
            this.report.reportarException(ex1);
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO CONECTAR CON LA BASE DE DATOS");
        } catch (IOException ex2) {
            this.report.reportarException(ex2);
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO CONECTAR CON LA BASE DE DATOS");
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
        } catch (FileNotFoundException ex1) {
            this.report.reportarException(ex1);
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO CONECTAR CON LA BASE DE DATOS");
        } catch (IOException ex2) {
            this.report.reportarException(ex2);
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO CONECTAR CON LA BASE DE DATOS");
        } finally {
            if (entrada != null) {
                scanner.close();
                try {
                    entrada.close();
                } catch (IOException ex3) {
                    this.report.reportarException(ex3);
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
        DefaultTableModel model = this.obtenerTableModel();
        this.crearFilaEnBlancoPorDefecto(model);
        this.comprobarJugadoresRegistrados(model);
    }

    private void comprobarJugadoresRegistrados(DefaultTableModel model) {
        Iterator<Xogador> iteratorJugadores = this.xogadores.listIterator();
        while (iteratorJugadores.hasNext()) {
            Xogador jugadorActual = iteratorJugadores.next();
            Object[] fila = this.crearFilaConDatosJugador(jugadorActual);
            this.agregarFilaConDatosATabla(model, fila);
        }
    }

    private void agregarFilaConDatosATabla(DefaultTableModel model, Object[] fila) {
        model.addRow(fila);
    }

    private Object[] crearFilaConDatosJugador(Xogador jugadorActual) {
        Object[] fila = {jugadorActual.getNombre(), jugadorActual.getScore()};
        return fila;
    }

    private DefaultTableModel obtenerTableModel() {
        DefaultTableModel model = (DefaultTableModel) this.ventanaPrincipal.getScoresTable().getModel();

        return model;
    }

    private void crearFilaEnBlancoPorDefecto(DefaultTableModel model) {
        model.setRowCount(1);
    }

    private void ajustarTamañoTabla() {
        Dimension dim = this.ventanaPrincipal.getScoresTable().getPreferredSize();
        int alturaFilas = this.ventanaPrincipal.getScoresTable().getRowHeight();
        int totalFilas = this.ventanaPrincipal.getScoresTable().getRowCount();
        dim.height = alturaFilas * (totalFilas + 1);
        this.ventanaPrincipal.getScoresTable().setPreferredScrollableViewportSize(dim);

    }

    public int getComprobante() {
        return comprobante;
    }

    public void setComprobante(int comprobante) {
        this.comprobante = comprobante;
    }

    public Sonido getSonido() {
        return sonido;
    }

    //SETTERs AND GETTERs
    public boolean isFinXogo() {
        return finXogo;
    }

    public void setFinXogo(boolean finXogo) {
        this.finXogo = finXogo;
    }

    public void setSonido(Sonido sonido) {
        this.sonido = sonido;
    }

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
