package objects.soldiers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import frameworks.Soldier;

public class GunSoldier extends Soldier {

    public GunSoldier(int x, int y, String side) {
        super(80, 50, x, y, side);

        getImage("gunsolider");
    }

    @Override
    public void update() {

        if (side == "green") {
            positionX -= 1;
        } else if (side == "red") {
            positionX += 1;
        }

        spriteCounter++;
        if (spriteCounter > 15) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }

    @Override
    public void draw(Graphics g) {

        BufferedImage image = null;

        if (spriteNum == 1) {
            image = walk1;
        }
        if (spriteNum == 2) {
            image = walk2;
        }

        g.drawImage(image, positionX, positionY, 120, 120, null);

    }

}
