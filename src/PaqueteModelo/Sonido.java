/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaqueteModelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author maste
 */
public class Sonido {

    private Clip musica;
    private long clipTimePosition;
    private HashMap<String, String> hashMapSonidos = new HashMap();
    private ReportException report = new ReportException();

    public Sonido() {
        this.hashMapSonidos.put("menu", "src\\Resources\\Musica\\menu.wav");
        this.hashMapSonidos.put("partida", "src\\Resources\\Musica\\juego.wav");
        this.hashMapSonidos.put("gameOver", "src\\Resources\\Musica\\gameover.wav");
        this.hashMapSonidos.put("borrar", "src\\Resources\\Musica\\poom.wav");
        this.hashMapSonidos.put("chocar", "src\\Resources\\Musica\\pop.wav");
        this.hashMapSonidos.put("cadradoAleatorio", "src\\Resources\\Musica\\bubble.wav");
    }

    public void reproducirMusica(String claveHashmap) {
        try {
            String ruta = this.hashMapSonidos.get(claveHashmap);
            File musicPath = new File(ruta);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                musica = AudioSystem.getClip();
                musica.open(audioInput);
                musica.start();
                FloatControl volume = (FloatControl) musica.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(-1 * 20);

                if (claveHashmap == "menu" || claveHashmap == "partida") {
                    musica.loop(Clip.LOOP_CONTINUOUSLY);
                }
            }
        } catch (FileNotFoundException ex1) {
            this.report.reportarException(ex1);
        } catch (UnsupportedAudioFileException ex2) {
            this.report.reportarException(ex2);
        } catch (LineUnavailableException ex3) {
            this.report.reportarException(ex3);
        } catch (IOException ex4) {
            this.report.reportarException(ex4);
        }
    }

    public void pararMusica() {
        this.musica.stop();
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

    public long getClipTimePosition() {
        return clipTimePosition;
    }

    public void setClipTimePosition(long clipTimePosition) {
        this.clipTimePosition = clipTimePosition;
    }

    public HashMap<String, String> getHashMapSonidos() {
        return hashMapSonidos;
    }

    public void setHashMapSonidos(HashMap<String, String> hashMapSonidos) {
        this.hashMapSonidos = hashMapSonidos;
    }

}
