/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoria;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.util.Random;
import javax.swing.*;
import java.awt.Image;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author casa
 */
public class Ventana extends JFrame {
    
    ///Componentes de la pantalla
    private JButton btnJugar;
    private JButton cleanlbl;
    private JComboBox cmbTema;
    private JLabel ganadorLbl;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel16;
    private JLabel jLabel17;
    private JLabel jLabel18;
    private JLabel jLabel19;
    private JLabel jLabel2;
    private JLabel jLabel20;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JLabel lbltimer;
    
    ///Variables para usar en los metodos y extras 
    private int img,gan;
    private int[] num = new int[10];
    private int[] cot = new int[20];
    private int[] aux = new int[2];
    private boolean[] est = new boolean[20];
    private AudioClip audio;
    private AudioClip audioPlay;
    private AudioClip Menu;
    private int volt;
    private int carp;
    private boolean run = false;    
    private String jugador;
    private Timer tiempo ;
    private TimerTask task;
    private int speed = 1000;
    private int seg=0;
    private int min=0;
    
    public Ventana() {
        iniciarComponentes();
        Menu = java.applet.Applet.newAudioClip(getClass().getResource("/memoria/menu.wav"));
        Menu.play();
        jugador=JOptionPane.showInputDialog(null, "Nombre Jugador");
      //  setTitle("Nombre Jugador",jugador);
        setTitle(jugador);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
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
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarAplicacion();
            }

        });
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
    
     public void cerrarAplicacion() {
        if (JOptionPane.showConfirmDialog(this,
                "¿ DESEA CERRAR EL JUEGO ?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    
    
    ///evento para boton jugar
   private void btnJugarActionPerformed( ActionEvent evt) {                                         
           iniciar();
          // Menu.stop();
           limpiar();
           carp = cmbTema.getSelectedIndex();
           //btnJugar.setText("¿Jugar otra vez?");
    }                                        

    ///evento para ventana ->abrir
   private void formWindowOpened( WindowEvent evt) {                                  
        carp=0;
    }                  
    
    ///evento para boton limpiar
   private void cleanlblActionPerformed( ActionEvent evt) {                                         
        audioPlay.stop();
        limpiar();
        audio = java.applet.Applet.newAudioClip(getClass().getResource("/memoria/remove.wav"));   
        audio.play();
        jugador=JOptionPane.showInputDialog(null, "Nombre del otro Jugador");
        setTitle(jugador);
    }
    
    ///todos estos para abajo son para los eventos de las celdas del 1 al 20
   private void jLabel1MouseClicked( MouseEvent evt) {                                     
         if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=1;
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[0]+".jpg")));
        ganar();
    } 
    
   private void jLabel2MouseClicked( MouseEvent evt) {                                     
          if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=2;
        jLabel2.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[1]+".jpg")));
        ganar();
    }     
    
   private void jLabel3MouseClicked( MouseEvent evt) {                                     
           if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=3;
        jLabel3.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[2]+".jpg")));
        ganar();
    }  
    
   private void jLabel4MouseClicked( MouseEvent evt) {                                     
       if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=4;
        jLabel4.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[3]+".jpg")));
        ganar();
    }
    
   private void jLabel5MouseClicked( MouseEvent evt) {                                     
          if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=5;
        jLabel5.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[4]+".jpg")));
        ganar();
    } 
    
   private void jLabel6MouseClicked( MouseEvent evt) {                                     
        if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=6;
        jLabel6.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[5]+".jpg")));
        ganar();
    }
     
   private void jLabel7MouseClicked( MouseEvent evt) {                                     
           if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=7;
        jLabel7.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[6]+".jpg")));
        ganar();
    }
    
   private void jLabel8MouseClicked( MouseEvent evt) {                                     
          if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=8;
        jLabel8.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[7]+".jpg")));
        ganar();
    }   
   
   private void jLabel9MouseClicked( MouseEvent evt) {                                     
           if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=9;
        jLabel9.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[8]+".jpg")));
        ganar();
    }
     
   private void jLabel10MouseClicked( MouseEvent evt) {                                      
          if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=10;
        jLabel10.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[9]+".jpg")));
         ganar();
    }

   private void jLabel11MouseClicked( MouseEvent evt) {                                      
           if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=11; 
        jLabel11.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[10]+".jpg")));
         ganar();
    }                                     

   private void jLabel12MouseClicked( MouseEvent evt) {                                      
           if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=12; 
        jLabel12.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[11]+".jpg")));
        ganar();
    }                                     

   private void jLabel13MouseClicked( MouseEvent evt) {                                      
            if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=13; 
        jLabel13.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[12]+".jpg")));
        ganar();
    }                                     

   private void jLabel14MouseClicked( MouseEvent evt) {                                      
            if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=14; 
        jLabel14.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[13]+".jpg")));
        ganar();
    }                                     

   private void jLabel15MouseClicked( MouseEvent evt) {                                      
               if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=15; 
        jLabel15.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[14]+".jpg")));
        ganar();
    }                                     

   private void jLabel16MouseClicked( MouseEvent evt) {                                      
               if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=16; 
        jLabel16.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[15]+".jpg")));
        ganar();
    }                                     

   private void jLabel17MouseClicked( MouseEvent evt) {                                      
            if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=17; 
        jLabel17.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[16]+".jpg")));
        ganar();
    }                                     

   private void jLabel18MouseClicked( MouseEvent evt) {                                      
            if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=18; 
        jLabel18.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[17]+".jpg")));
        ganar();
    }                                     

   private void jLabel19MouseClicked( MouseEvent evt) {                                      
            if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=19; 
        jLabel19.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[18]+".jpg")));
        ganar();
    }                                     

   private void jLabel20MouseClicked( MouseEvent evt) {                                      
            if (volt==2){
            comprobacion();
        }
        volt++;
        aux[volt-1]=20; 
        jLabel20.setIcon(new ImageIcon(getClass().getResource("/memoria/"+carp+"/"+ cot[19]+".jpg")));
        ganar();
    }                                     


    ///metodo principal en la formacion de la pantalla
    private void iniciarComponentes() {

        btnJugar = new JButton();
        cmbTema = new JComboBox();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        lbltimer = new JLabel();
        ganadorLbl = new JLabel();
        jLabel13 = new JLabel();
        jLabel14 = new JLabel();
        jLabel15 = new JLabel();
        jLabel16 = new JLabel();
        jLabel17 = new JLabel();
        jLabel18 = new JLabel();
        jLabel19 = new JLabel();
        jLabel20 = new JLabel();
        cleanlbl = new JButton();
        
        ///estetica de los labels tiempo y ganador
        lbltimer.setBackground(new Color(255, 255, 255));
        lbltimer.setFont(new Font("Castellar", 1, 14)); 
        lbltimer.setForeground(new Color(255, 255, 255));

        ganadorLbl.setBackground(new Color(255, 255, 255));
        ganadorLbl.setFont(new Font("Castellar", 1, 12)); 
        ganadorLbl.setForeground(new Color(255, 255, 255));
        
///evento para la pantalla
        addWindowListener(new WindowAdapter() {
            public void windowOpened( WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        
        ///listener del boton jugar y aspecto estetico
        btnJugar.setBackground(new Color(255, 255, 255));
        btnJugar.setForeground(new Color(255, 255, 255));
        btnJugar.setIcon(new ImageIcon(getClass().getResource("/memoria/play.png"))); 
        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                btnJugarActionPerformed(evt);
            }
        });

        ///estetica del comboBox
        cmbTema.setBackground(new Color(0, 204, 204));
        cmbTema.setModel(new DefaultComboBoxModel(new String[] { "Futbol", "Lenguajes_Programacion", "Naruto" }));

        
        ///Estetica de los labels celdas 1-20 y sus listeners
        jLabel1.setBackground(new Color(255, 255, 255));
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setBackground(new Color(255, 255, 255));
        jLabel2.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel2.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setBackground(new Color(255, 255, 255));
        jLabel3.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel3.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setBackground(new Color(255, 255, 255));
        jLabel4.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel4.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setBackground(new Color(255, 255, 255));
        jLabel5.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel5.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setBackground(new Color(255, 255, 255));
        jLabel6.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg")));
        jLabel6.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setBackground(new Color(255, 255, 255));
        jLabel7.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel7.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setBackground(new Color(255, 255, 255));
        jLabel8.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel8.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel9.setBackground(new Color(255, 255, 255));
        jLabel9.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel9.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setBackground(new Color(255, 255, 255));
        jLabel10.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel10.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel11.setBackground(new Color(255, 255, 255));
        jLabel11.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel11.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel12.setBackground(new Color(255, 255, 255));
        jLabel12.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel12.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel13.setBackground(new Color(255, 255, 255));
        jLabel13.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel13.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel15.setBackground(new Color(255, 255, 255));
        jLabel15.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel15.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel16.setBackground(new Color(255, 255, 255));
        jLabel16.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel16.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        jLabel17.setBackground(new Color(255, 255, 255));
        jLabel17.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel17.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        jLabel18.setBackground(new Color(255, 255, 255));
        jLabel18.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel18.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        jLabel19.setBackground(new Color(255, 255, 255));
        jLabel19.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel19.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        jLabel20.setBackground(new Color(255, 255, 255));
        jLabel20.setIcon(new ImageIcon(getClass().getResource("/memoria/0/0.jpg"))); 
        jLabel20.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        ///listener del boton limpiar
        cleanlbl.setIcon(new ImageIcon(getClass().getResource("/memoria/clean.png"))); 
        cleanlbl.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                cleanlblActionPerformed(evt);
            }
        });

        
        ///Ubicacion de los componentes de la pantalla
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        ///componentes en horizontal
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(ganadorLbl, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE).addGap(63, 63, 63))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(lbltimer, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE).addGap(222, 222, 222))))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(29, 29, 29)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE).addGap(32, 32, 32)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))))).addGap(40, 40, 40)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)).addGap(39, 39, 39)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)).addGap(35, 35, 35)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)).addContainerGap(49, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)).addGap(36, 36, 36)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup( GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
                    .addComponent(cmbTema, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnJugar, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)
                    .addComponent(cleanlbl, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        ///Componentes en vertical
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addGap(27, 27, 27)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnJugar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cleanlbl, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(cmbTema, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(23, 23, 23)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17,GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)).addGap(11, 11, 11)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18,GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 64,  GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                    .addComponent(lbltimer, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(ganadorLbl, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE).addContainerGap())
        );

        pack();///para ajustar los componentes a la pantalla
    }
    
   
}
