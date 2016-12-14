/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package memoria;
import java.util.Random;
import javax.swing.*;
import java.awt.Image;
import java.applet.AudioClip;
import java.util.Timer;
import java.util.TimerTask;




/**
 *
 * @author casa
 */
public class Pantalla extends javax.swing.JFrame {
    int img,cant,gan;
    int[] num = new int[10];
    int[] cot = new int[20];
    int[] aux = new int[2];
    boolean[] est = new boolean[20];
    private Image imagen;
    private AudioClip audio;
    private AudioClip audioPlay;
    private AudioClip Menu;
    int volt;
    int carp;
    
    private Timer tiempo ;
    private TimerTask task;
    private int speed = 1000;
    private int seg=0;
    private int min=0;
    
    boolean run = false;    
    String jugador;
    String jugadores;

    /** Creates new form Pantalla */
    public Pantalla() {
        initComponents();
        Menu = java.applet.Applet.newAudioClip(getClass().getResource("/memoria/menu.wav"));
        Menu.play();
        jugador=JOptionPane.showInputDialog(null, "Nombre Jugador");
      //  setTitle("Nombre Jugador",jugador);
        setTitle(jugador);
        JOptionPane.showMessageDialog(null, "En un menu tiene varias opciones de visualizacion de las celdas\n"+
                                             "*Debera primero terminar para volver a empezar otro juego\n"+
                                             "*El boton de limpiar es solo cuando termine el juego y quiera ver el tablero limpio,\n"+
                                             "y posteriormente volver a jugar de nuevo\n"+
                                             "Igualmente el boton de limpiar pondra en pantalla una ventana para cambiar el nombre del jugador sino\n"+
                                             "solo oprimir en el boton de jugar por si es el mismo jugador\n"+
                                             "Keylor Cruz Carvajal-Estudiante Ingenieria en Sistemas UNA-CR"   );
        setIconImage(new ImageIcon(getClass().getResource("/memoria/icon.png")).getImage());
        ((JPanel)getContentPane()).setOpaque(false); 
        ImageIcon uno=new ImageIcon(this.getClass().getResource("/memoria/fondo1.jpg")); 
        JLabel fondo= new JLabel(); 
        fondo.setIcon(uno); 
        getLayeredPane().add(fondo,JLayeredPane.FRAME_CONTENT_LAYER); 
        fondo.setBounds(0,0,uno.getIconWidth(),uno.getIconHeight());
        cleanlbl.setVisible(false);
       
        
        
    }

        private void iniciar(){
        btnJugar.setVisible(false);
        cleanlbl.setVisible(false);
        cmbTema.setVisible(false);
       
         Menu.stop();
         audioPlay = java.applet.Applet.newAudioClip(getClass().getResource("/memoria/play.wav"));
         audioPlay.play();
        
        for(int x=0;x<10;x++)
            num[x]=0;
        for(int x=0;x<20;x++)
            est[x]=true;
        aleatorio();
        volt=0;
        gan=0;
        ///////////////////////////////
           run=true;
           tiempo = new Timer();
           task = new TimerTask() {               
               public void run() {
                   seg++;                   
                   if (seg<=60){
                       if(seg<10){
                           lbltimer.setText("Tiempo: "+String.valueOf(min) +":0"+String.valueOf(seg));
                       }else{
                        lbltimer.setText("Tiempo: "+String.valueOf(min) +":"+String.valueOf(seg));}
                   }
                   else{seg=0;min++;}
                    }
           };                                         
           tiempo.schedule(task,0,speed);
        ///////////////////////////////////
           
    }
        
  private void aleatorio(){
        Random r = new Random();
        int x;
        x=0;
        for(int cant=0;cant<20;cant++){
            img = r.nextInt(10);
            if (num[img]<2){
                num[img]++;
                cot[cant]=img+1;
            }else{
                cant--;
            }
        }
    }
    
    private void comprobacion(){
        if(cot[aux[0]-1]==cot[aux[1]-1]){
            est[aux[0]-1]=false;
            est[aux[1]-1]=false;
            volt=0;
            gan++;
        }else{
            volt=0;
            voltear(aux[0]);
            voltear(aux[1]);
        }
    }
    
    private void ganar(){
         if ((volt==2)&&(gan==9)){
            ganadorLbl.setText(jugador+": GANASTE, "+lbltimer.getText());
            audioPlay.stop();
            Menu.play();
            tiempo.cancel();
            task.cancel();
            run=false;
            min=0; 
            seg=0;
            btnJugar.setVisible(true);
             cleanlbl.setVisible(true);
             cmbTema.setVisible(true);
             
         }
    }
    
    private void limpiar(){
                ganadorLbl.setText("");
                lbltimer.setText("");
                jLabel1.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel2.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel3.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel4.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel5.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel6.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel7.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel8.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel9.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel10.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel11.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel12.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel13.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel14.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel15.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                jLabel16.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                 jLabel17.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                  jLabel18.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                   jLabel19.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                    jLabel20.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
    }
    
    private void voltear(int z){
        switch(z){
            case 1:
                jLabel1.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
            case 2:
                jLabel2.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
            case 3:
                jLabel3.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
            case 4:
                jLabel4.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
            case 5:
                jLabel5.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
            case 6:
                jLabel6.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
            case 7:
                jLabel7.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
            case 8:
                jLabel8.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
            case 9:
                jLabel9.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
            case 10:
                jLabel10.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
            case 11:
                jLabel11.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
            case 12:
                jLabel12.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
            case 13:
                jLabel13.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
            case 14:
                jLabel14.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
             case 15:
                jLabel15.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
             case 16:
                jLabel16.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;    
            case 17:
                jLabel17.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;
            case 18:
                jLabel18.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;  
            case 19:
                jLabel19.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;  
            case 20:
                jLabel20.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
                break;    
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnJugar = new javax.swing.JButton();
        cmbTema = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbltimer = new javax.swing.JLabel();
        ganadorLbl = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cleanlbl = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnJugar.setBackground(new java.awt.Color(255, 255, 255));
        btnJugar.setForeground(new java.awt.Color(255, 255, 255));
        btnJugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/play.png"))); // NOI18N
        btnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJugarActionPerformed(evt);
            }
        });

        cmbTema.setBackground(new java.awt.Color(0, 204, 204));
        cmbTema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Futbol", "Lenguajes_Programacion", "Naruto" }));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        lbltimer.setBackground(new java.awt.Color(255, 255, 255));
        lbltimer.setFont(new java.awt.Font("Castellar", 1, 14)); // NOI18N
        lbltimer.setForeground(new java.awt.Color(255, 255, 255));

        ganadorLbl.setBackground(new java.awt.Color(255, 255, 255));
        ganadorLbl.setFont(new java.awt.Font("Castellar", 1, 12)); // NOI18N
        ganadorLbl.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); // NOI18N
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        cleanlbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/clean.png"))); // NOI18N
        cleanlbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanlblActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ganadorLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbltimer, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(222, 222, 222))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(49, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbTema, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cleanlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cleanlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(cmbTema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(lbltimer, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ganadorLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJugarActionPerformed
           iniciar();
          // Menu.stop();
           limpiar();
           carp = cmbTema.getSelectedIndex();
           //btnJugar.setText("¿Jugar otra vez?");
    }//GEN-LAST:event_btnJugarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        carp=0;
    }//GEN-LAST:event_formWindowOpened

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
           if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=12; 
        jLabel12.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[11]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
           if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=11; 
        jLabel11.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[10]+".jpg")));
         ganar();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
          if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=10;
        jLabel10.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[9]+".jpg")));
         ganar();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
           if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=9;
        jLabel9.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[8]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
          if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=8;
        jLabel8.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[7]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
           if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=7;
        jLabel7.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[6]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=6;
        jLabel6.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[5]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
          if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=5;
        jLabel5.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[4]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
       if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=4;
        jLabel4.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[3]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
           if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=3;
        jLabel3.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[2]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
          if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=2;
        jLabel2.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[1]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
         if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=1;
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[0]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
            if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=13; 
        jLabel13.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[12]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
            if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=14; 
        jLabel14.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[13]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
               if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=15; 
        jLabel15.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[14]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
               if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=16; 
        jLabel16.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[15]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
            if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=17; 
        jLabel17.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[16]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
            if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=18; 
        jLabel18.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[17]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
            if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=19; 
        jLabel19.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[18]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
            if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=20; 
        jLabel20.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[19]+".jpg")));
        ganar();
    }//GEN-LAST:event_jLabel20MouseClicked

    private void cleanlblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanlblActionPerformed
        audioPlay.stop();
        limpiar();
        audio = java.applet.Applet.newAudioClip(getClass().getResource("/memoria/remove.wav"));   
        audio.play();
        jugador=JOptionPane.showInputDialog(null, "Nombre del otro Jugador");
        setTitle(jugador);
    }//GEN-LAST:event_cleanlblActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJugar;
    private javax.swing.JButton cleanlbl;
    private javax.swing.JComboBox cmbTema;
    private javax.swing.JLabel ganadorLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lbltimer;
    // End of variables declaration//GEN-END:variables

   

}
