/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PaqueteIU;

import PaqueteModelo.Xogo;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.Timer;

/**
 *
 * @author a22lucastf
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    //ATRIBUTOS
    public Timer timer;
    public Timer timerScore;
    public Xogo xogo;
    public static Clip cliper;
    public int contadorMusica = 0;
    long clipTimePosition;

    /**
     * Creates new form VentanaPrincipalFrame
     */
    public VentanaPrincipal() {

        initComponents();
        this.setLocationRelativeTo(null);
        this.panelGameOver.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameLevels = new javax.swing.JFrame();
        labelTituloLevel = new javax.swing.JLabel();
        easyButton = new javax.swing.JButton();
        hardButton = new javax.swing.JButton();
        normalButton = new javax.swing.JButton();
        frameJuego = new javax.swing.JFrame();
        panelGameOver = new javax.swing.JPanel();
        gameOverLabel = new javax.swing.JLabel();
        nombreJugadorLabel = new javax.swing.JTextField();
        jugadorLabel = new javax.swing.JLabel();
        gameOverOKButton = new javax.swing.JButton();
        panelJuego = new javax.swing.JPanel();
        scoreLabel = new javax.swing.JLabel();
        scoreTextLabel = new javax.swing.JLabel();
        LineasLabel = new javax.swing.JLabel();
        lineasTextLabel = new javax.swing.JLabel();
        pauseButton = new javax.swing.JToggleButton();
        levelLabel = new javax.swing.JLabel();
        levelTextLabel = new javax.swing.JLabel();
        panelfondo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        exitButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        labelTituloTetris = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        settingsButton = new javax.swing.JButton();
        botonSonido = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        labelTituloLevel.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        labelTituloLevel.setText("SELECT DIFFICULTY");

        easyButton.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        easyButton.setContentAreaFilled(false);
        easyButton.setSelected(true);
        easyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyButtonActionPerformed(evt);
            }
        });

        hardButton.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        hardButton.setContentAreaFilled(false);
        hardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hardButtonActionPerformed(evt);
            }
        });

        normalButton.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        normalButton.setContentAreaFilled(false);
        normalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                normalButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frameLevelsLayout = new javax.swing.GroupLayout(frameLevels.getContentPane());
        frameLevels.getContentPane().setLayout(frameLevelsLayout);
        frameLevelsLayout.setHorizontalGroup(
            frameLevelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLevelsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(frameLevelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hardButton)
                    .addComponent(easyButton)
                    .addComponent(normalButton))
                .addGap(70, 70, 70))
            .addGroup(frameLevelsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTituloLevel)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        frameLevelsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {easyButton, hardButton, normalButton});

        frameLevelsLayout.setVerticalGroup(
            frameLevelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLevelsLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(labelTituloLevel)
                .addGap(30, 30, 30)
                .addComponent(easyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(normalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        frameJuego.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frameJuego.setTitle("Tetris");
        frameJuego.setBackground(new java.awt.Color(204, 204, 204));
        frameJuego.setForeground(java.awt.Color.gray);
        frameJuego.setMinimumSize(new java.awt.Dimension(700, 850));
        frameJuego.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                frameJuegoKeyPressed(evt);
            }
        });
        frameJuego.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelGameOver.setBackground(new java.awt.Color(0, 0, 0));
        panelGameOver.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        gameOverLabel.setBackground(new java.awt.Color(255, 255, 255));
        gameOverLabel.setFont(new java.awt.Font("Monospaced", 0, 48)); // NOI18N
        gameOverLabel.setForeground(new java.awt.Color(255, 255, 255));
        gameOverLabel.setText("GAME OVER");
        panelGameOver.add(gameOverLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 60, 290, 91));
        panelGameOver.add(nombreJugadorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 180, 30));

        jugadorLabel.setBackground(new java.awt.Color(255, 255, 255));
        jugadorLabel.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jugadorLabel.setForeground(new java.awt.Color(255, 255, 255));
        jugadorLabel.setText("JUGADOR ->");
        panelGameOver.add(jugadorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 120, 30));

        gameOverOKButton.setText("OK");
        gameOverOKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameOverOKButtonActionPerformed(evt);
            }
        });
        panelGameOver.add(gameOverOKButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, -1, 30));

        frameJuego.getContentPane().add(panelGameOver, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 810));

        panelJuego.setBackground(new java.awt.Color(0, 0, 0));
        panelJuego.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelJuego.setNextFocusableComponent(playButton);
        panelJuego.setLayout(null);
        frameJuego.getContentPane().add(panelJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 400, 800));

        scoreLabel.setBackground(new java.awt.Color(255, 255, 255));
        scoreLabel.setFont(new java.awt.Font("Monospaced", 1, 48)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        scoreLabel.setText("Score:");
        frameJuego.getContentPane().add(scoreLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 24, -1, -1));

        scoreTextLabel.setBackground(new java.awt.Color(255, 255, 255));
        scoreTextLabel.setFont(new java.awt.Font("Monospaced", 1, 48)); // NOI18N
        scoreTextLabel.setForeground(new java.awt.Color(255, 255, 255));
        scoreTextLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreTextLabel.setText("0");
        frameJuego.getContentPane().add(scoreTextLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 94, 150, -1));

        LineasLabel.setBackground(new java.awt.Color(255, 255, 255));
        LineasLabel.setFont(new java.awt.Font("Monospaced", 1, 48)); // NOI18N
        LineasLabel.setForeground(new java.awt.Color(255, 255, 255));
        LineasLabel.setText("Lineas:");
        frameJuego.getContentPane().add(LineasLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 191, -1, -1));

        lineasTextLabel.setFont(new java.awt.Font("Monospaced", 1, 48)); // NOI18N
        lineasTextLabel.setForeground(new java.awt.Color(255, 255, 255));
        lineasTextLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lineasTextLabel.setText("0");
        frameJuego.getContentPane().add(lineasTextLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 261, 150, -1));

        pauseButton.setFont(new java.awt.Font("Monospaced", 1, 48)); // NOI18N
        pauseButton.setText("PAUSE");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });
        frameJuego.getContentPane().add(pauseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 601, -1, -1));

        levelLabel.setFont(new java.awt.Font("Monospaced", 1, 48)); // NOI18N
        levelLabel.setBackground(new java.awt.Color(255, 255, 255));
        levelLabel.setForeground(new java.awt.Color(255, 255, 255));
        levelLabel.setText("Level");
        frameJuego.getContentPane().add(levelLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 190, 70));

        levelTextLabel.setFont(new java.awt.Font("Monospaced", 1, 48)); // NOI18N
        levelTextLabel.setBackground(new java.awt.Color(255, 255, 255));
        levelTextLabel.setForeground(new java.awt.Color(255, 255, 255));
        frameJuego.getContentPane().add(levelTextLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 130, 80));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Imagenes/fondo.jpg"))); // NOI18N

        javax.swing.GroupLayout panelfondoLayout = new javax.swing.GroupLayout(panelfondo);
        panelfondo.setLayout(panelfondoLayout);
        panelfondoLayout.setHorizontalGroup(
            panelfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 630, Short.MAX_VALUE)
        );
        panelfondoLayout.setVerticalGroup(
            panelfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelfondoLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        frameJuego.getContentPane().add(panelfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 810));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tetris");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(102, 102, 102));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Imagenes/EXIT_STOPPED.png"))); // NOI18N
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Imagenes/EXIT.gif"))); // NOI18N
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
        });
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        jPanel1.add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 406, 151, -1));

        playButton.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Imagenes/PLAY_STOPPED.png"))); // NOI18N
        playButton.setContentAreaFilled(false);
        playButton.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Imagenes/PLAY.gif"))); // NOI18N
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        jPanel1.add(playButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 148, 151, 107));

        labelTituloTetris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Imagenes/The_Tetris_Company_logo.png"))); // NOI18N
        jPanel1.add(labelTituloTetris, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 14, 380, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 261, 50, 10));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 390, 50, 10));

        settingsButton.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        settingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Imagenes/Levels-1.png.png"))); // NOI18N
        settingsButton.setContentAreaFilled(false);
        settingsButton.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Imagenes/Levels.gif"))); // NOI18N
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });
        jPanel1.add(settingsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 277, 151, 107));

        botonSonido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Imagenes/sound.png"))); // NOI18N
        botonSonido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSonidoActionPerformed(evt);
            }
        });
        jPanel1.add(botonSonido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 60, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Imagenes/fondo.jpg"))); // NOI18N
        jLabel1.setToolTipText("");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 1080));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exitButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed

        this.iniciarPartida();

    }//GEN-LAST:event_playButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        frameLevels.setVisible(rootPaneCheckingEnabled);
        frameLevels.setSize(260, 340);
    }//GEN-LAST:event_settingsButtonActionPerformed

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        this.dispose();
    }//GEN-LAST:event_exitButtonMouseClicked

    private void easyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_easyButtonActionPerformed

    private void hardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hardButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hardButtonActionPerformed

    private void normalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_normalButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_normalButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed

        if (pauseButton.isSelected()) {
            clipTimePosition = cliper.getMicrosecondPosition();
            cliper.stop();
            timer.stop();
            timerScore.stop();
            xogo.timerComprobarLineas.stop();
            xogo.pausa = true;
        } else {
            cliper.setMicrosecondPosition(clipTimePosition);
            cliper.start();
            pauseButton.setFocusable(false);
            timer.start();
            timerScore.start();
            xogo.timerComprobarLineas.start();
            xogo.pausa = false;
        }

    }//GEN-LAST:event_pauseButtonActionPerformed

    private void frameJuegoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_frameJuegoKeyPressed

        if (!xogo.pausa) {

            if (KeyEvent.getKeyText(evt.getKeyCode()).equals("A")) {
                xogo.moverFichaEsquerda();
            }
            if (KeyEvent.getKeyText(evt.getKeyCode()).equals("D")) {
                xogo.moverFichaDereita();
            }
            if (KeyEvent.getKeyText(evt.getKeyCode()).equals("S")) {
                xogo.moverFichaAbaixo();
                this.actualizarPanel();
            }
            if (KeyEvent.getKeyText(evt.getKeyCode()).equals("W")) {
                xogo.fichaActual.posicion++;
                xogo.RotarFicha();
            }
        }
    }//GEN-LAST:event_frameJuegoKeyPressed

    private void gameOverOKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameOverOKButtonActionPerformed

        PrintWriter salida = null;
        File archivotxt = new File("PlayerScore.txt");
        String jugadorScore = this.getNombreJugadorLabel().getText() + "-" + xogo.contadorScore + "\n";
        try {
            salida = new PrintWriter(new FileWriter(archivotxt));
            if (archivotxt.exists()) {
                salida.write(jugadorScore);
            }
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (salida != null) {
                salida.close();
            }
        }


    }//GEN-LAST:event_gameOverOKButtonActionPerformed

    private void botonSonidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSonidoActionPerformed
        contadorMusica++;
        if (contadorMusica == 1) {
            botonSonido.setIcon(new ImageIcon(("src\\Resources\\Imagenes\\mute.png")));
            clipTimePosition = cliper.getMicrosecondPosition();
            cliper.stop();
            System.out.println(clipTimePosition);
        } else {
            contadorMusica = 0;
            cliper.setMicrosecondPosition(clipTimePosition);
            botonSonido.setIcon(new ImageIcon(("src\\Resources\\Imagenes\\sound.png")));
            cliper.start();
        }
    }//GEN-LAST:event_botonSonidoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String musicPath = "src\\Resources\\Musica\\menu.wav";
                new VentanaPrincipal().setVisible(true);
                playMenuMusic(musicPath);
            }
        });

    }

    public static void playMenuMusic(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                cliper = AudioSystem.getClip();
                cliper.open(audioInput);
                cliper.start();
                cliper.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("No se encontr?? el archivo");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void iniciarPartida() {

        cliper.stop();
        String musicPath = "src\\Resources\\Musica\\juego.wav";
        playMenuMusic(musicPath);
        ocultarVentanaPrincipal();
        mostrarVentanaJuego();
        xogo = new Xogo(false, this);
        this.mostrarLevel();
        xogo.xenerarNovaFicha();
        this.movimientoCaida();
        timer.start();
        xogo.comprobarLineasCompletas();
        xogo.timerComprobarLineas.start();
        this.aumentarScore();
        timerScore.start();

    }

    public void ocultarVentanaPrincipal() {
        this.setVisible(false);

    }

    public void mostrarVentanaJuego() {
        frameJuego.setVisible(true);
        frameJuego.setFocusable(true);
        panelJuego.setFocusable(true);
        frameJuego.setLocationRelativeTo(this.rootPane);
    }
    
    public void mostrarPanelGameOver() {
       getPanelGameOver().setVisible(true);
    }

    public void movimientoCaida() {

        this.timer = new Timer(1000, (ActionEvent e) -> {
            xogo.moverFichaAbaixo();
            this.actualizarPanel();

        });
    }

    public void aumentarScore() {
        this.timerScore = new Timer(1000, (ActionEvent e) -> {
            mostrarScore();
        });
    }

    public void actualizarPanel() {
        panelJuego.updateUI();
    }

    public void pintarCadrado(JLabel lblCadrado) {
        this.panelJuego.add(lblCadrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(lblCadrado.getX(), lblCadrado.getY(), -1, -1));

    }

    public void borrarCadrado(JLabel lblCadrado) {
        this.panelJuego.remove(lblCadrado);
    }

    public void mostrarNumeroLineas(int numeroLineas) {
        this.getLineasTextLabel().setText(String.valueOf(numeroLineas));
    }

    public void mostrarScore() {
        this.scoreTextLabel.setText(String.valueOf(xogo.contadorScore++));
    }

    public void mostrarLevel() {
        this.getLevelTextLabel().setText(String.valueOf(xogo.level));
    }
    
    

    public JPanel getPanelJuego() {
        return panelJuego;
    }

    public void setPanelJuego(JPanel panelJuego) {
        this.panelJuego = panelJuego;
    }

    public static Clip getCliper() {
        return cliper;
    }

    public static void setCliper(Clip cliper) {
        VentanaPrincipal.cliper = cliper;
    }

    public JLabel getLineasTextLabel() {
        return lineasTextLabel;
    }

    public void setLineasTextLabel(JLabel lineasTextLabel) {
        this.lineasTextLabel = lineasTextLabel;
    }

    public JLabel getLevelTextLabel() {
        return levelTextLabel;
    }

    public void setLevelTextLabel(JLabel levelTextLabel) {
        this.levelTextLabel = levelTextLabel;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Timer getTimerScore() {
        return timerScore;
    }

    public void setTimerScore(Timer timerScore) {
        this.timerScore = timerScore;
    }

    public Xogo getXogo() {
        return xogo;
    }

    public void setXogo(Xogo xogo) {
        this.xogo = xogo;
    }

    public JLabel getLineasLabel() {
        return LineasLabel;
    }

    public void setLineasLabel(JLabel LineasLabel) {
        this.LineasLabel = LineasLabel;
    }

    public JButton getEasyButton() {
        return easyButton;
    }

    public void setEasyButton(JButton easyButton) {
        this.easyButton = easyButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public JFrame getFrameJuego() {
        return frameJuego;
    }

    public void setFrameJuego(JFrame frameJuego) {
        this.frameJuego = frameJuego;
    }

    public JFrame getFrameLevels() {
        return frameLevels;
    }

    public void setFrameLevels(JFrame frameLevels) {
        this.frameLevels = frameLevels;
    }

    public JLabel getGameOverLabel() {
        return gameOverLabel;
    }

    public void setGameOverLabel(JLabel gameOverLabel) {
        this.gameOverLabel = gameOverLabel;
    }

    public JButton getHardButton() {
        return hardButton;
    }

    public void setHardButton(JButton hardButton) {
        this.hardButton = hardButton;
    }

    public JButton getjButton1() {
        return gameOverOKButton;
    }

    public void setjButton1(JButton jButton1) {
        this.gameOverOKButton = jButton1;
    }

    public JSeparator getjSeparator1() {
        return jSeparator1;
    }

    public void setjSeparator1(JSeparator jSeparator1) {
        this.jSeparator1 = jSeparator1;
    }

    public JSeparator getjSeparator2() {
        return jSeparator2;
    }

    public void setjSeparator2(JSeparator jSeparator2) {
        this.jSeparator2 = jSeparator2;
    }

    public JLabel getJugadorLabel() {
        return jugadorLabel;
    }

    public void setJugadorLabel(JLabel jugadorLabel) {
        this.jugadorLabel = jugadorLabel;
    }

    public JLabel getLabelTituloLevel() {
        return labelTituloLevel;
    }

    public void setLabelTituloLevel(JLabel labelTituloLevel) {
        this.labelTituloLevel = labelTituloLevel;
    }

    public JLabel getLabelTituloTetris() {
        return labelTituloTetris;
    }

    public void setLabelTituloTetris(JLabel labelTituloTetris) {
        this.labelTituloTetris = labelTituloTetris;
    }

    public JLabel getLevelLabel() {
        return levelLabel;
    }

    public void setLevelLabel(JLabel levelLabel) {
        this.levelLabel = levelLabel;
    }

    public JTextField getNombreJugadorLabel() {
        return nombreJugadorLabel;
    }

    public void setNombreJugadorLabel(JTextField nombreJugadorLabel) {
        this.nombreJugadorLabel = nombreJugadorLabel;
    }

    public JButton getNormalButton() {
        return normalButton;
    }

    public void setNormalButton(JButton normalButton) {
        this.normalButton = normalButton;
    }

    public JPanel getPanelGameOver() {
        return panelGameOver;
    }

    public void setPanelGameOver(JPanel panelGameOver) {
        this.panelGameOver = panelGameOver;
    }

    public JToggleButton getPauseButton() {
        return pauseButton;
    }

    public void setPauseButton(JToggleButton pauseButton) {
        this.pauseButton = pauseButton;
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public void setPlayButton(JButton playButton) {
        this.playButton = playButton;
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public JLabel getScoreTextLabel() {
        return scoreTextLabel;
    }

    public void setScoreTextLabel(JLabel scoreTextLabel) {
        this.scoreTextLabel = scoreTextLabel;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }

    public void setSettingsButton(JButton settingsButton) {
        this.settingsButton = settingsButton;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LineasLabel;
    private javax.swing.JButton botonSonido;
    private javax.swing.JButton easyButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JFrame frameJuego;
    private javax.swing.JFrame frameLevels;
    private javax.swing.JLabel gameOverLabel;
    private javax.swing.JButton gameOverOKButton;
    private javax.swing.JButton hardButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jugadorLabel;
    private javax.swing.JLabel labelTituloLevel;
    private javax.swing.JLabel labelTituloTetris;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JLabel levelTextLabel;
    private javax.swing.JLabel lineasTextLabel;
    private javax.swing.JTextField nombreJugadorLabel;
    private javax.swing.JButton normalButton;
    private javax.swing.JPanel panelGameOver;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JPanel panelfondo;
    private javax.swing.JToggleButton pauseButton;
    private javax.swing.JButton playButton;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JLabel scoreTextLabel;
    private javax.swing.JButton settingsButton;
    // End of variables declaration//GEN-END:variables
}
