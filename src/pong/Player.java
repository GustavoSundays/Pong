package pong;

import java.awt.*;

public class Player {

    public boolean right, left;
    public int x, y, width, height;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        width = 40;
        height = 5;
    }

    public void tick() {
        if (right) {
            x++;
        } else if (left) {
            x--;
        }

        if (x+width > Game.WIDTH) {
            x = Game.WIDTH - width;
        } else if (x < 0) {
            x = 0;
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect(x, y, width, height);
    }

}
