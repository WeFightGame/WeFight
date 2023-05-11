package objects.bases;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import frameworks.Base;
import frameworks.Soldier;
import main.GamePanel;
import factory.RedSoldierFactory;

public class RedBase extends Base {

    public int maxHp;
    public static int HP = 1000;

    public static Soldier firstSoldier;
    public static int positionOfFirstObject = 0;
    private int countTime;

    public RedBase(int x, int y) {
        super(x, y, new RedSoldierFactory());

        this.maxHp = HP;
        getImage();
    }


    public void getImage() {
        try {
            base = ImageIO.read(new File("images/base/redbase/base.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void takeDamage(int damage) {
        HP -= damage;
    }

    @Override
    public void update() {

        // increase money ... per second
        countTime++;
        if (countTime >= GamePanel.FPS) {
            setMoney(getLevel());
            countTime = 0;
        }

        // update soldier
        if (!soldiers.isEmpty()) {
            firstSoldier = soldiers.get(0);
            // The position of first soldier
            positionOfFirstObject = firstSoldier.positionX;
            for (Soldier soldier : soldiers) {
                soldier.update(BlueBase.firstSoldier, "blue");
                // check distance with enemy's position
                soldier.checkDistanceToHit(BlueBase.positionOfFirstObject);

                // it will check the base position to hit the base
            }

            // when soldier is dead then remove it
            if (firstSoldier.getHp() <= 0) {
                soldiers.remove(0);
            }
        } else {
            // if there is no soldier it will be the position of base
            firstSoldier = null;
            positionOfFirstObject = 200;
        }

    }

    @Override
    public void draw(Graphics g) {
        g.setFont(g.getFont().deriveFont(Font.BOLD, 50F));
        String text = "$ " + String.valueOf(getMoney());

        g.setColor(Color.black);
        g.drawString(text, positionX + 20, 100);

        g.drawImage(base, positionX, positionY, 480, 480, null);

        for (Soldier soldier : soldiers) {
            soldier.draw(g);
        }

        double oneScale = (double) 350 / maxHp;
        double hpBarValue = oneScale * HP;

        g.setColor(new Color(35, 35, 35));
        g.fillRect(positionX + 20, 20, 352, 22);
        
        g.setColor(new Color(255, 0, 30));
        g.fillRect(positionX + 20, 20, (int) hpBarValue, 20);
    }
}
