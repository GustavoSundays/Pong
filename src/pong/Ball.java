package pong;

import java.awt.*;
import java.util.Random;

public class Ball {

    public double x,y;
    public int width, height;

    public double wayX, wayY;
    public double speed = 1.2;

    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
        width = 4;
        height = 4;

        int angle = new Random().nextInt(120 - 45) + 45;
        wayX = Math.cos(Math.toRadians(angle));
        wayY = Math.sin(Math.toRadians(angle));
    }

    public void tick() {

        if ((x + (wayX * speed) + width >= Game.WIDTH) || (x + (wayX * speed) < 0)) {
            wayX *= -1;
        }

        if (y >= Game.HEIGHT) {
            //ponto inimigo
            new Game(false);
            return;
        } else if (y < 0) {
            //ponto jogador
            new Game(true);
            return;
        }

        Rectangle bounds = new Rectangle((int)(x+(wayX * speed)), (int)(y+(wayY * speed)), width, height);
        Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
        Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);

        if (bounds.intersects(boundsPlayer)) {
            int angle = new Random().nextInt(120 - 45) + 45;
            wayX = Math.cos(Math.toRadians(angle));
            wayY = Math.sin(Math.toRadians(angle));
            if (wayY > 0) wayY *= -1;
        } else if (bounds.intersects(boundsEnemy)) {
            int angle = new Random().nextInt(120 - 45) + 45;
            wayX = Math.cos(Math.toRadians(angle));
            wayY = Math.sin(Math.toRadians(angle));
            if (wayY < 0) wayY *= -1;
        }

        x += wayX * speed;
        y += wayY * speed;
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        graphics.fillRect((int)x, (int)y, width, height);
    }

}
