/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaqueteModelo;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author maste
 */
public class Sonido {

    private Clip musica;
    private Clip sonido;
    private long clipTimePosition;

    public void reproducirMusicaMenu() {
        String sonidoMusicaPath = "src\\Resources\\Musica\\menu.wav";
        this.reproducirMusica(sonidoMusicaPath);
    }

    public void reproducirMusicaGameOver() {
        String sonidoGameOverPath = "src\\Resources\\Musica\\gameover.wav";
        this.reproducirSonido(sonidoGameOverPath);
    }

    public void reproducirSonidoBorrarLinea() {
        String sonidoLineaPath = "src\\\\Resources\\\\Musica\\\\poom.wav";
        this.reproducirSonido(sonidoLineaPath);
    }

    public void reproducirSonidoChocaChan() {
        String sonidoChanPath = "src\\\\Resources\\\\Musica\\\\pop.wav";
        this.reproducirSonido(sonidoChanPath);
    }

    public void reproducirMusicaPartida() {
        String sonidoPartidaPath = "src\\Resources\\Musica\\juego.wav";
        this.reproducirMusica(sonidoPartidaPath);
    }

    private void reproducirSonido(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                sonido = AudioSystem.getClip();
                sonido.open(audioInput);
                sonido.start();
            } else {
                System.out.println("No se encontró el archivo");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void reproducirMusica(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                musica = AudioSystem.getClip();
                musica.open(audioInput);
                musica.start();
                FloatControl volume = (FloatControl) musica.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(-1 * 20);
            } else {
                System.out.println("No se encontró el archivo");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void muteMusica() {
        setClipTimePosition(musica.getMicrosecondPosition());
        musica.stop();
    }

    public void unmuteMusica() {
        musica.setMicrosecondPosition(getClipTimePosition());
        musica.start();
    }

    public Clip getMusica() {
        return musica;
    }

    public void setMusica(Clip musica) {
        this.musica = musica;
    }

    public Clip getSonido() {
        return sonido;
    }

    public void setSonido(Clip sonido) {
        this.sonido = sonido;
    }

    public long getClipTimePosition() {
        return clipTimePosition;
    }

    public void setClipTimePosition(long clipTimePosition) {
        this.clipTimePosition = clipTimePosition;
    }

}
