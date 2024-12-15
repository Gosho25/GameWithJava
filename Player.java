package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO; 
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

    public GamePanel gp;
    public KeyHandler keyH;

    public int x, y, speed;
    public String direction; // Добавете променлива за посока
    public int spriteNum;
    public int spriteCounter;

    // Декларация на изображения
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/1_U.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/2_U.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/1_D.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/2_D.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/1_L.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/2_L.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/1_R.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/2_R.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void update() {
        if (keyH.upPressed == true) { //може и така
            direction = "up";
            y -= speed;
        } else if (keyH.downPressed == true) {
            direction = "down";
            y += speed;
        } else if (keyH.leftPressed) { // но може и така
            direction = "left";
            x -= speed;
        } else if (keyH.rightPressed) {
            direction = "right";
            x += speed;
        }


        //Бързина за сменяне на рисунки 1 и 2
        spriteCounter++;
        if (spriteCounter > 20) { //20: скорост
            spriteNum = (spriteNum == 1) ? 2 : 1; // Превключване между 1 и 2
            spriteCounter = 0; // Нулиране на счетчика след смяна
        }
        //Във видеото има и начин ако натишкаш клавиша само тогава да се мести, но на мен ми харесва така
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
