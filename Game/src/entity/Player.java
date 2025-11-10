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
    
    Entity entity = new Entity();

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
        entity.x = 100;
        entity.y = 100;
        entity.speed = 4;
        entity.direction = "down";
    }

    public void update() {
        if (keyH.upPressed == true) { //може и така
            entity.direction = "up";
            entity.y -= entity.speed;
        } else if (keyH.downPressed == true) {
            entity.direction = "down";
            entity.y += entity.speed;
        } else if (keyH.leftPressed) { // но може и така
            entity.direction = "left";
            entity.x -= entity.speed;
        } else if (keyH.rightPressed) {
            entity.direction = "right";
            entity.x += entity.speed;
        }


        //Бързина за сменяне на рисунки 1 и 2
        entity.spriteCounter++;
        if (entity.spriteCounter > 20) { //20: скорост
            entity.spriteNum = (entity.spriteNum == 1) ? 2 : 1; // Превключване между 1 и 2
            entity.spriteCounter = 0; // Нулиране на счетчика след смяна
        }
        //Във видеото има и начин ако натишкаш клавиша само тогава да се мести, но на мен ми харесва така
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (entity.direction) {
            case "up":
                if(entity.spriteNum == 1){
                    image = up1;
                }
                if(entity.spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(entity.spriteNum == 1){
                    image = down1;
                }
                if(entity.spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(entity.spriteNum == 1){
                    image = left1;
                }
                if(entity.spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(entity.spriteNum == 1){
                    image = right1;
                }
                if(entity.spriteNum == 2){
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, entity.x, entity.y, gp.tileSize, gp.tileSize, null);
    }
}