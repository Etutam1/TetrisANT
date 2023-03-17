/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaqueteModelo;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author maste
 */
public class Sonido {

    private Clip musicaMenu;
    private Clip musicaPartida;
    private Clip sonidoGameOver;
    private Clip sonido;
    private long clipTimePosition;

    public void reproducirMusicaMenu() {
        String sonidoMusicaPath = "src\\Resources\\Musica\\menu.wav";
        this.reproducirMusicaMenu(sonidoMusicaPath);
    }

    public void reproducirMusicaGameOver() {
        String sonidoGameOverPath = "src\\Resources\\Musica\\gameover.wav";
        this.reproducirGameOver(sonidoGameOverPath);
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
        this.reproducirMusicaPartida(sonidoPartidaPath);
    }

    private void reproducirSonido(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                sonido.open(audioInput);
                sonido.start();
            } else {
                System.out.println("No se encontró el archivo");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void reproducirMusicaMenu(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                musicaMenu.open(audioInput);
                musicaMenu.start();
            } else {
                System.out.println("No se encontró el archivo");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void reproducirMusicaPartida(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                musicaPartida.open(audioInput);
                musicaPartida.start();
            } else {
                System.out.println("No se encontró el archivo");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void reproducirGameOver(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                sonidoGameOver.open(audioInput);
                sonidoGameOver.start();
            } else {
                System.out.println("No se encontró el archivo");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void muteMusicaMenu() {
        setClipTimePosition(musicaMenu.getMicrosecondPosition());
        musicaMenu.stop();
    }

    public void unmuteMusicaMenu() {
        musicaMenu.setMicrosecondPosition(getClipTimePosition());
        musicaMenu.start();
    }

    public void muteMusicaPartida() {
        setClipTimePosition(musicaPartida.getMicrosecondPosition());
        musicaPartida.stop();
    }

    public void unmuteMusicaPartida() {
        musicaPartida.setMicrosecondPosition(getClipTimePosition());
        musicaPartida.start();
    }

    public Clip getMusicaMenu() {
        return musicaMenu;
    }

    public void setMusicaMenu(Clip musicaMenu) {
        this.musicaMenu = musicaMenu;
    }

    public Clip getMusicaPartida() {
        return musicaPartida;
    }

    public void setMusicaPartida(Clip musicaPartida) {
        this.musicaPartida = musicaPartida;
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

    public Clip getSonidoGameOver() {
        return sonidoGameOver;
    }

    public void setSonidoGameOver(Clip sonidoGameOver) {
        this.sonidoGameOver = sonidoGameOver;
    }

}
