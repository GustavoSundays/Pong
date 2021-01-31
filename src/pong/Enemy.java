package pong;

import java.awt.*;

public class Enemy {

    public double x,y;
    public int width, height;

    public Enemy(double x, double y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 5;
    }

    public void tick() {
        x += (Game.ball.x - x - 6) * 0.05;

        if (x+width > Game.WIDTH) {
            x = Game.WIDTH - width;
        } else if (x < 0) {
            x = 0;
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect((int)x, (int)y, width, height);
    }

}
