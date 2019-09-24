package egg.atcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import javax.imageio.ImageIO;
import sun.audio.*;

public class Eggatcher {

    
    public static void main(String[] args) { //Main of the Program. Shows our GUI frame
        Gameframe f = new Gameframe();
        f.setVisible(true);
        
    }
    
}

class Gameframe extends JFrame{ //Game frame where all the main game components are added
    
    private Menupnl menu = new Menupnl();
    private Playpnl play = new Playpnl();
    private Helppnl help = new Helppnl();
    private Easypnl easy = new Easypnl();
    private Mediumpnl medium = new Mediumpnl();
    private HardPnl hard = new HardPnl();
    Container c = this.getContentPane(); //Container to use in changing between our panels
    
    public Gameframe(){ //Game frame constructor to intialize locations, size, which panel to display, ect.
        this.setSize(700, 600);
        this.setTitle("Egg Catcher");
        this.setLocation(500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        c.add(menu);//Intially show Menu panel
        c.setVisible(true);
        PlayMusic();
  
        
        menu.playbtn.addActionListener(new ActionListener() { //Move from Menu panel to Play panel

            @Override
            public void actionPerformed(ActionEvent e) {
                c.remove(menu);
                c.add(play);
                c.revalidate();
                c.repaint();
                c.setVisible(true);
            }
        });
        
        menu.helpbtn.addActionListener(new ActionListener() { //Move from Menu panel to Help panel

            @Override
            public void actionPerformed(ActionEvent e) {
                c.remove(menu);
                c.add(help);
                c.repaint();
                c.revalidate();
            }
        });
        
        play.backbtn.addActionListener(new ActionListener() { //Move from Play panel back to Menu panel

            @Override
            public void actionPerformed(ActionEvent e) {
                c.remove(play);
                c.add(menu);
                c.repaint();
                c.revalidate();
            }
        });
        
        play.easybtn.addActionListener(new ActionListener() { //Move from Play panel to Easy panel (1st Game Panel) 

            @Override
            public void actionPerformed(ActionEvent e) {
                c.remove(play);
                c.add(easy);
                c.repaint();
                c.revalidate();
            }
        });
        
        play.mediumbtn.addActionListener(new ActionListener() { //Move from Play panel to Medium panel (2nd Game Panel)

            @Override
            public void actionPerformed(ActionEvent e) {
                c.remove(play);
                c.add(medium);
                c.repaint();
                c.revalidate();
            }
        });
        
        play.hardbtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                c.remove(play);
                c.add(hard);
                c.revalidate();
                c.repaint();
            }
            
        });
        
         help.backbtn.addActionListener(new ActionListener() { //Move from help panel back to Menu panel

            @Override
            public void actionPerformed(ActionEvent e) {
                c.removeAll();
                c.add(menu);
                c.repaint();
                c.revalidate();
            }
        });
        
    }
       public void PlayMusic(){
             InputStream music;
               try{
                 music=new FileInputStream(new File("chicken.wav"));
                 AudioStream audios=new AudioStream(music);
                  AudioPlayer.player.start(audios);
}
              catch(Exception e){
                System.out.println("ERROR!");
}   
             
         }
    public void goback(){ //Used to go back to Play panel after Game over
        c.removeAll();
        c.add(play);
        c.revalidate();
        c.repaint();
    }

    class Menupnl extends JPanel{ //Start of Menu panel
    
        protected JButton playbtn= new JButton("Play");
        protected JButton helpbtn = new JButton("Help");
        private JButton exitbtn = new JButton("Exit");
    
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            try{
                BufferedImage img = ImageIO.read(new File("ProjectBg.jpg"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight() , this);
            }
            catch(Exception e){}
    }
    
        public Menupnl(){
            JButton[] menuarr = {playbtn, helpbtn, exitbtn};
            for(int i = 0; i<menuarr.length; i++){
                menuarr[i].setSize(200, 50);
                int j = 70;
                menuarr[i].setLocation(300, j*i+100);
                Font gameFont = new Font("Serif",Font.ITALIC,25);
                menuarr[i].setFont(gameFont);
                menuarr[i].setBackground(Color.ORANGE);
                menuarr[i].setVisible(true);
                this.add(menuarr[i]);
                
            }
            this.setLayout(null);
        
            
            exitbtn.addActionListener(new ActionListener () {
            
                @Override
                public void actionPerformed(ActionEvent e) {
                    int choice = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?" );
                    if(choice==JOptionPane.YES_OPTION)
                        System.exit(0);
                }
            });
            this.setVisible(true);
            
        }
    } //End of Menu Panel


    class Playpnl extends JPanel{ //Start of Play panel
        
        protected JButton easybtn = new JButton("Easy");
        protected JButton mediumbtn = new JButton("Medium");
        protected JButton hardbtn = new JButton("Hard");
        protected JButton backbtn = new JButton("Back");
    
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            try{
                BufferedImage img = ImageIO.read(new File("ProjectBg.jpg"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight() , this);
            }
            catch(Exception e){}
        }
    
        public Playpnl(){
        
            JButton playarr[] = {easybtn, mediumbtn, hardbtn, backbtn};
            for(int i = 0; i<playarr.length; i++){
                playarr[i].setSize(200,50);
                int j = 70;
                playarr[i].setLocation(300, j*i+100);
                Font gameFont = new Font("Serif",Font.ITALIC,25);
                playarr[i].setFont(gameFont);
                playarr[i].setBackground(Color.ORANGE);
                this.add(playarr[i]);
            }
            this.setLayout(null);
       
        }   
    } //End of Play Panel
       
    class Helppnl extends JPanel{ //Start of Help panel
        
        private JButton backbtn = new JButton("Back");
    
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            try{
                BufferedImage img = ImageIO.read(new File("Help.jpg"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight() , this);
            }
            catch(Exception e){}
        }
    
        public Helppnl(){
            backbtn.setSize(200, 50);
            backbtn.setLocation(0, 0);
            Font myFont = new Font("Serif",Font.ITALIC,25);
            backbtn.setFont(myFont);
            backbtn.setBackground(Color.ORANGE);       
            this.add(backbtn);
            this.setLayout(null);
         
        }
    } //End of Help panel

    class Easypnl extends JPanel{ //Start of Easy panel
        
        private JButton backbtn = new JButton("Back");
        private JLabel scorelbl = new JLabel();
        private JLabel liveslbl = new JLabel();
        private Random rand = new Random();
        private int egg_width , egg_height, basket_width, lives=3, score=0;
        private javax.swing.Timer timer;
    
        public Easypnl(){
            egg_width = rand.nextInt(500);
            basket_width = 0;
            egg_height = 0;
            scorelbl.setText("Score: "+score);
            scorelbl.setBounds(50, 0, 70, 30);
            liveslbl.setBounds(200,0,70,30);
            liveslbl.setText("Lives: " +lives);
            this.setLayout(null);
            this.add(scorelbl);
            this.add(liveslbl);
      
            this.addKeyListener(new KeyAdapter(){ //Key listener to handel movement of basket
                @Override
                public void keyPressed(KeyEvent e){
                    if(e.getKeyCode()==KeyEvent.VK_RIGHT){ //Right arrow add the X bound of basket by 20
                        basket_width+=20;
                        repaint();
                    }
                    if(e.getKeyCode()==KeyEvent.VK_LEFT){ //Left arrow decrements X bounds by 20
                        basket_width-=20; 
                        repaint();
                    }
                } 
            });
            this.setFocusable(true); //Set focus to make key action listener work
        }
  
        public int eggfall(){ //Function to move egg 5 across the Y axis (Y bound) between each delay time
            if(egg_height<450){
                egg_height+=5;
                return egg_height;
            }
            return 0;
        }
    

        public boolean catchegg(){ //Dectect collision between egg and basket
            Rectangle basket = new Rectangle(); //Create rectangl for basket
            basket.setBounds(basket_width, 450, 200, 100); //Set bounds using X & Y bounds of basket and its intialized size to draw the rectangele around it
	    Rectangle egg = new Rectangle(); //Create rectangle for egg
            egg.setBounds(egg_width, egg_height, 50, 50); //Set bounds using X & Y bounds of egg and its intialized size to draw the rectangele around it
	    if(egg.intersects(basket)){ //Check collision
                return true;
            }
            return false;
        }
        
       
        public void paintComponent(Graphics g){ //Controls egg timer, basket movement, redrawing componints in case of collision, and when to end the game
            super.paintComponent(g);
            grabFocus(); //Key listener
            int delay = 300;
            eggfall(); //Egg movement for timer (MUST be caled here to word parallel with timer
            timer = new javax.swing.Timer(delay, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if(catchegg()){ //When egg and basket collides redraw the egg in a new random X bound
                        score += 10;
                        scorelbl.setText("Score: "+score);
                        egg_height = 0;
	                egg_width = rand.nextInt(500); 
                    }
                   
                    else if(egg_height==450 && !catchegg() && lives!=0){ //When egg falls on ground and lives doesn't reduce lives and redraw 
                        lives--;
                        liveslbl.setText("Lives: " +lives);
                        egg_height = 0;
                        egg_width = rand.nextInt(500);
                    }
                    timer.stop();
                    if(lives==0){ //End game
                        lives=3;
                        int choice = JOptionPane.showConfirmDialog(null,"Game Over!","Game over!",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
                        if(choice==JOptionPane.OK_OPTION)
                            goback();
                        
                    }
                    repaint();
                }
            });
            timer.start(); 
            try{
                BufferedImage bkg = ImageIO.read(new File("gamebkg.jpg"));
                g.drawImage(bkg, 0, 0, this.getWidth(), this.getHeight() , this);
                BufferedImage egg = ImageIO.read(new File("egg.png"));
                g.drawImage(egg,  egg_width, egg_height, 50, 50 , this);
                BufferedImage basket = ImageIO.read(new File("basket.png"));
                g.drawImage(basket, basket_width, 450, 200, 100 , this); 
            }
            catch(Exception e){}
        }
    } //End of Easy panel

    class Mediumpnl extends JPanel{ //Start of Medium panel (same as Easy panel except we draw 2 eggs, timer is faster, and eggs fall is faster
       
        private Random rand = new Random();
        private int egg1_width , egg1_height, egg2_width, egg2_height, basket_width, lives=3, score=0;
        private javax.swing.Timer timer;
        private JLabel scorelbl = new JLabel();
        private JLabel liveslbl = new JLabel();
        
        
        
    
        public Mediumpnl(){
            egg1_width = rand.nextInt(500);
            egg2_width = rand.nextInt(500);
            basket_width = 0;
            egg1_height = 0;
            egg2_height = 0;
            scorelbl.setText("Score: "+score);
            scorelbl.setBounds(50, 0, 70, 30);
            liveslbl.setBounds(200,0,70,30);
            liveslbl.setText("Lives: " +lives);
            this.setLayout(null);
            this.add(scorelbl);
            this.add(liveslbl);
      
            this.addKeyListener(new KeyAdapter(){
                
                @Override
                public void keyPressed(KeyEvent e){
                    if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                        basket_width+=20;
                        repaint();
                    }
                    if(e.getKeyCode()==KeyEvent.VK_LEFT){
                        basket_width-=20; 
                        repaint();
                    }
                } 
            });
            this.setFocusable(true);
        }
  
        public int eggfall(){ 
            if(egg1_height<450 && egg2_height<450){
                egg1_height+=10;
                egg2_height+=5;
                return egg1_height;
            }
            return 0;
        }
   
        public boolean catchegg1(){
            Rectangle basket = new Rectangle();
            basket.setBounds(basket_width, 450, 200, 100);
	    Rectangle egg1 = new Rectangle();
            egg1.setBounds(egg1_width, egg1_height, 50, 50); 
            
	    if(egg1.intersects(basket)){
                return true;
            }
            return false;
        }
        
        public boolean catchegg2(){
            Rectangle basket = new Rectangle();
            basket.setBounds(basket_width, 450, 200, 100);
            Rectangle egg2 = new Rectangle();
            egg2.setBounds(egg2_width, egg2_height, 50, 50);
            if(egg2.intersects(basket)) return true;
            return false;
        }
    
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            grabFocus();
            int delay = 250;
            eggfall();
            timer = new javax.swing.Timer(delay, new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e){
                    if(catchegg1()){
                        score += 10;
                        scorelbl.setText("Score: "+score);
                        egg1_height = 0;
	                egg1_width = rand.nextInt(500);
                    }
                    if(catchegg2()){
                        score += 10;
                        scorelbl.setText("Score: "+score);
                        egg2_height = 0;
	                egg2_width = rand.nextInt(400);
                    }
                    if(egg1_height==450 && !catchegg1() && lives!=0 ){
                        egg1_height = 0;
                        egg1_width = rand.nextInt(500);
                        lives--;
                        liveslbl.setText("Lives: " +lives);
                    }
                    if(egg2_height==450 && !catchegg2() && lives!=0 ){
                        egg2_height = 0;
                        egg2_width = rand.nextInt(400);
                        lives--;
                        liveslbl.setText("Lives: " +lives);
                    }
                    timer.stop();
                    if(lives==0){
                        lives=3;
                       int choice = JOptionPane.showConfirmDialog(null,"Game Over!","Game over!",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
                        if(choice==JOptionPane.OK_OPTION)
                            goback();
                    }
                    repaint();
                }
            });
            timer.start(); 
        
            try{
                BufferedImage bkg = ImageIO.read(new File("gamebkg.jpg"));
                g.drawImage(bkg, 0, 0, this.getWidth(), this.getHeight() , this);
                BufferedImage egg = ImageIO.read(new File("egg.png"));
                g.drawImage(egg,  egg1_width, egg1_height, 50, 50 , this);
                BufferedImage egg2 = ImageIO.read(new File("egg.png"));
                g.drawImage(egg2,  egg2_width, egg2_height, 50, 50 , this);
                BufferedImage basket = ImageIO.read(new File("basket.png"));
                g.drawImage(basket, basket_width, 450, 200, 100 , this); 
            }
            catch(Exception e){}
        }
    } //End of Medium panel
    
     class HardPnl extends JPanel{ //Start of Hard panel (same as Medium panel except we draw 3 eggs, timer is faster, and eggs fall is faster
       
        private Random rand = new Random();
        private int egg1_width , egg1_height, egg2_width, egg2_height,egg3_width, egg3_height, basket_width, lives=3, score=0;
        private javax.swing.Timer timer;
        private JLabel scorelbl = new JLabel();
        private JLabel liveslbl = new JLabel();
    
        public HardPnl(){
            egg1_width = rand.nextInt(500);
            egg2_width = rand.nextInt(500);
            egg3_width = rand.nextInt(500);
            basket_width = 0;
            egg1_height = 0;
            egg2_height = 0;
            egg3_height = 0;
            scorelbl.setText("Score: "+score);
            scorelbl.setBounds(50, 0, 70, 30);
            liveslbl.setBounds(200,0,70,30);
            liveslbl.setText("Lives: " +lives);
            this.setLayout(null);
            this.add(scorelbl);
            this.add(liveslbl);
      
            this.addKeyListener(new KeyAdapter(){
                
                @Override
                public void keyPressed(KeyEvent e){
                    if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                        basket_width+=30;
                        repaint();
                    }
                    if(e.getKeyCode()==KeyEvent.VK_LEFT){
                        basket_width-=30; 
                        repaint();
                    }
                } 
            });
            this.setFocusable(true);
        }
  
        public int eggfall(){ 
            if(egg1_height<450 && egg2_height<450 && egg3_height<450){
                egg1_height+=20;
                egg2_height+=10;
                egg3_height+=5;
                return egg1_height;
            }
            return 0;
        }
   
        public boolean catchegg1(){
            Rectangle basket = new Rectangle();
            basket.setBounds(basket_width, 450, 200, 100);
	    Rectangle egg1 = new Rectangle();
            egg1.setBounds(egg1_width, egg1_height, 50, 50); 
            
            
	    if(egg1.intersects(basket)){
                return true;
            }
            return false;
        }
        
        public boolean catchegg2(){
            Rectangle egg2 = new Rectangle();
            egg2.setBounds(egg2_width, egg2_height, 50, 50);
            Rectangle basket = new Rectangle();
            basket.setBounds(basket_width, 450, 200, 100);
            if(egg2.intersects(basket)) return true;
            return false;
        }
        
        public boolean catchegg3(){
            Rectangle egg3 = new Rectangle();
            egg3.setBounds(egg3_width, egg3_height, 50, 50);
            Rectangle basket = new Rectangle();
            basket.setBounds(basket_width, 450, 200, 100);
            if(egg3.intersects(basket)) return true;
            return false;
        }
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            grabFocus();
            int delay = 230;
            eggfall();
           
            timer = new javax.swing.Timer(delay, new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e){
                    if(catchegg1()){
                        score += 10;
                        scorelbl.setText("Score: "+score);
                        egg1_height = 0;
	                egg1_width = rand.nextInt(500);
                    }
                    else if(catchegg2()){
                        score += 10;
                        scorelbl.setText("Score: "+score);
                        egg2_height = 0;
	                egg2_width = rand.nextInt(400);
                    }
                    else if(egg1_height==450 && !catchegg1() && lives!=0){
                        lives--;
                        liveslbl.setText("Lives: " +lives);
                        egg1_height = 0;
                        egg1_width = rand.nextInt(500);     
                    }
                    else if(egg2_height==450 && !catchegg2() && lives!=0){
                        lives--;
                        liveslbl.setText("Lives: " +lives);
                        egg2_height = 0;
                        egg2_width = rand.nextInt(400);
                    }
                    else if(catchegg3()){
                        score += 10;
                        scorelbl.setText("Score: "+score);
                        egg3_height = 0;
	                egg3_width = rand.nextInt(300);
                    }
                    else if(egg3_height==450 && !catchegg3() && lives!=0){
                        lives--;
                        liveslbl.setText("Lives: " +lives);
                        egg3_height = 0;
                        egg3_width = rand.nextInt(300);
                    }
                    timer.stop();
                    if(lives==0){
                        lives=3;
                    int choice = JOptionPane.showConfirmDialog(null,"Game Over!","Game over!",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
                        if(choice==JOptionPane.OK_OPTION)
                            goback();                    
                    }
                    repaint();
                }
            });
            timer.start(); 
                               
  try{
                BufferedImage bkg = ImageIO.read(new File("gamebkg.jpg"));
                g.drawImage(bkg, 0, 0, this.getWidth(), this.getHeight() , this);
                BufferedImage egg = ImageIO.read(new File("egg.png"));
                g.drawImage(egg,  egg1_width, egg1_height, 50, 50 , this);
                BufferedImage egg2 = ImageIO.read(new File("egg.png"));
                g.drawImage(egg2,  egg2_width, egg2_height, 50, 50 , this);
                BufferedImage egg3 = ImageIO.read(new File("egg.png"));
                g.drawImage(egg3,  egg3_width, egg3_height, 50, 50 , this);
                BufferedImage basket = ImageIO.read(new File("basket.png"));
                g.drawImage(basket, basket_width, 450, 200, 100 , this); 
            }
            catch(Exception e){}
        }
    }

    
} //End of Game frame
