package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;

import frameworks.State;
import main.GamePanel;

public class PausedState implements State {

    private GamePanel gp;
    

    public PausedState(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void draw(Graphics g) {
        gp.PLAYING.draw(g);

        // gp.setBackground(Color.pink);

        g.setFont(g.getFont().deriveFont(Font.BOLD, 96F));
        String text = "PAUSED";
        int x = gp.getXforCenteredText(text, g);
        int y = gp.HEIGHT / 2;

        g.setColor(Color.black);
        g.drawString(text, x + 5, y + 5);

        g.setColor(Color.white);
        g.drawString(text, x, y);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.PLAYING;
        }

    }

    @Override
    public void update() {

    }

    
}
