package Main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
        // SCREEN SETTINGS
        final int originalTileSize = 16; //16x16 tiles default title of character, NPCs, map STANDARTEN
        final int scale = 3;//izgl malki zatova 16x3 = 48

        public final int tileSize = originalTileSize * scale; //48x48
        //resolution of the screen
        final int maxScreenCol = 16;//18; the best is 37; default 16
        final int maxScreenRow = 12; //16 x 12 ekran; default 12; the best is 20
        final int screenWidth = tileSize * maxScreenCol; //768 pixels
        final int screenHeight = tileSize * maxScreenRow; //576 pixels
        // I CAN CHANGE THE SCREEN RESOLUTION IF I WANT


        //FPS
        int FPS = 60;

        KeyHandler keyH = new KeyHandler();
        Thread gameThread;

        Player player = new Player(this, keyH);


        //set player default possision
        int playerX = 100;
        int playerY = 100;
        int playerSpeed = 4;// 4 == pixels



        public GamePanel() {
            this.setPreferredSize(new Dimension(screenWidth, screenHeight));
            this.setBackground(Color.black);
            this.setDoubleBuffered(true);
            this.addKeyListener(keyH);
            this.setFocusable(true);
        }

        public void startGameThread(){
            gameThread = new Thread(this);
            gameThread.start();
        }

        @Override
/*   public void run(){

            double drawInterval = 1_000_000_000/FPS; //0.01666 seconds
            double nextDrawTime = System.nanoTime() +  drawInterval;

            while(gameThread != null) {
                long currentTime = System.nanoTime();
                System.out.println("Current time =" + currentTime);

                update();

                repaint();


                try{
                    double remainingTime = nextDrawTime - System.nanoTime();
                    remainingTime = remainingTime/1_000_000;

                    if(remainingTime < 0){
                        remainingTime = 0;
                    }

                    Thread.sleep((long) remainingTime);

                    nextDrawTime += drawInterval;

                } catch (InterruptedException e){

                    e.printStackTrace();

                }
            }
    }*/
    public  void run(){
        double drawInterval = 1_000_000_000/FPS; //0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;



        while(gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1_000_000_000){
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }
    public  void update(){

       player.update();

    }
    public void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            player.draw(g2);

            g2.dispose();
    }
}

//Player Character https://www.youtube.com/watch?v=wT9uNGzMEM4&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=3
