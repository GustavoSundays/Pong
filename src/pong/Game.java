package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {

    public static int WIDTH = 160;
    public static int HEIGHT = 120;
    public static int SCALE = 3;

    public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public static Player player;
    public static Enemy enemy;
    public static Ball ball;

    public static Integer scorePlayer = 0, scoreEnemy = 0;

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        this.addKeyListener(this);
        player = new Player(60, HEIGHT-5);
        enemy = new Enemy(60, 0);
        ball = new Ball(80, HEIGHT/2 - 1);
    }

    public Game(boolean isScorePlayer) {
        new Game();
        if (isScorePlayer) {
            scorePlayer++;
        } else {
            scoreEnemy++;
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(game).start();
    }

    public void tick() {
        player.tick();
        enemy.tick();
        ball.tick();
    }

    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = layer.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        player.render(graphics);
        enemy.render(graphics);
        ball.render(graphics);

        graphics.setFont(new Font( "Times New Roman", Font.BOLD, 10));
        graphics.setColor(Color.WHITE);
        graphics.drawString(scoreEnemy.toString(), 1,11);
        graphics.drawString(scorePlayer.toString(), 1, HEIGHT - 11);

        graphics = bufferStrategy.getDrawGraphics();
        graphics.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE,null);

        bufferStrategy.show();
    }

    @Override
    public void run() {
        while(true) {
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }
    }
}
