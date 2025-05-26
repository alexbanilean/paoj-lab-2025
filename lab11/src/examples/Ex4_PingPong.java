package examples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ex4_PingPong extends JFrame {

    public Ex4_PingPong() {
        setTitle("Ping Pong - Joc Swing");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel panel = new GamePanel();
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Clasa principală a jocului, extinde JPanel pentru desen
    static class GamePanel extends JPanel implements ActionListener, MouseMotionListener, KeyListener {

        // Dimensiuni panel
        private final int WIDTH = 600;
        private final int HEIGHT = 400;

        // Paleta utilizator
        private final int PADDLE_WIDTH = 10;
        private final int PADDLE_HEIGHT = 80;
        private int playerY = HEIGHT / 2 - PADDLE_HEIGHT / 2;

        // Paleta calculator
        private int aiY = HEIGHT / 2 - PADDLE_HEIGHT / 2;

        // Minge
        private int ballX = WIDTH / 2;
        private int ballY = HEIGHT / 2;
        private int ballSize = 20;
        private int ballXDir = -3;
        private int ballYDir = 3;

        // Scoruri
        private int playerScore = 0;
        private int aiScore = 0;

        // Timer pentru animație
        private Timer timer;

        public GamePanel() {
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setBackground(Color.BLACK);

            // Ascultător pentru mișcarea mouse-ului
            addMouseMotionListener(this);

            // Ascultător tastatură (opțional)
            setFocusable(true);
            addKeyListener(this);

            // Timer: 15ms ~ 60fps
            timer = new Timer(15, this);
            timer.start();
        }

        // Metoda pentru desenare
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Setări grafice
            g.setColor(Color.WHITE);

            // Desenare linie de mijloc (punctată)
            for (int i = 0; i < HEIGHT; i += 15) {
                g.fillRect(WIDTH / 2 - 1, i, 2, 10);
            }

            // Desenare palete
            g.fillRect(10, playerY, PADDLE_WIDTH, PADDLE_HEIGHT);
            g.fillRect(WIDTH - 20, aiY, PADDLE_WIDTH, PADDLE_HEIGHT);

            // Desenare minge
            g.fillOval(ballX, ballY, ballSize, ballSize);

            // Scor
            g.setFont(new Font("Consolas", Font.BOLD, 20));
            g.drawString("Player: " + playerScore, 50, 30);
            g.drawString("AI: " + aiScore, WIDTH - 150, 30);
        }

        // Parametri AI pentru realism
        private final int AI_MAX_SPEED = 4;
        private final int AI_REACTION_DELAY = 10; // număr de frame-uri întârziere
        private int reactionCounter = 0;
        private int targetY = HEIGHT / 2; // poziția țintă cu decalaj

        // Prezicerea poziției mingii când ajunge la marginea AI
        private int predictBallY() {
            int predictedY = ballY;
            int predictedDirY = ballYDir;
            int predictedX = ballX;
            int predictedDirX = ballXDir;

            // Simulăm traiectoria mingii până când ajunge la marginea AI (WIDTH - 20)
            while (predictedX < WIDTH - 20 && predictedX > 0) {
                predictedX += predictedDirX;
                predictedY += predictedDirY;

                if (predictedY <= 0 || predictedY + ballSize >= HEIGHT) {
                    predictedDirY = -predictedDirY; // inversăm direcția verticală la coliziune cu sus/jos
                }
            }
            return predictedY;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Mișcare minge
            ballX += ballXDir;
            ballY += ballYDir;

            // Coliziuni cu sus/jos
            if (ballY <= 0 || ballY + ballSize >= HEIGHT) {
                ballYDir = -ballYDir;
            }

            // Coliziuni cu palete
            if (ballX <= 20 && ballY + ballSize >= playerY && ballY <= playerY + PADDLE_HEIGHT) {
                ballXDir = -ballXDir;
            }
            if (ballX + ballSize >= WIDTH - 20 && ballY + ballSize >= aiY && ballY <= aiY + PADDLE_HEIGHT) {
                ballXDir = -ballXDir;
            }

            // AI reacționează cu întârziere
            if (reactionCounter < AI_REACTION_DELAY) {
                reactionCounter++;
            } else {
                reactionCounter = 0;
                // Prezicem poziția mingii când ajunge la marginea AI
                int predictedY = predictBallY();

                // Introducem un offset aleator pentru a simula erori umane
                int errorMargin = 15; // pixeli decalaj max
                int randomOffset = (int)(Math.random() * 2 * errorMargin) - errorMargin;

                targetY = predictedY - PADDLE_HEIGHT / 2 + randomOffset;

                // Limităm ținta în interiorul ferestrei
                if (targetY < 0) targetY = 0;
                if (targetY + PADDLE_HEIGHT > HEIGHT) targetY = HEIGHT - PADDLE_HEIGHT;
            }

            // Mișcăm paleta AI către ținta calculată cu viteză limitată
            if (aiY < targetY) {
                aiY += AI_MAX_SPEED;
                if (aiY > targetY) aiY = targetY;
            } else {
                aiY -= AI_MAX_SPEED;
                if (aiY < targetY) aiY = targetY;
            }

            // Limităm poziția AI
            if (aiY < 0) aiY = 0;
            if (aiY + PADDLE_HEIGHT > HEIGHT) aiY = HEIGHT - PADDLE_HEIGHT;

            // Punctaj și reset
            if (ballX < 0) {
                aiScore++;
                resetMingea();
            }
            if (ballX > WIDTH) {
                playerScore++;
                resetMingea();
            }

            repaint();
        }

        // Resetează mingea în centru și direcția random
        private void resetMingea() {
            ballX = WIDTH / 2;
            ballY = HEIGHT / 2;
            ballXDir = (Math.random() > 0.5) ? 3 : -3;
            ballYDir = (Math.random() > 0.5) ? 3 : -3;
        }

        // Control paletă cu mouse (mișcare verticală)
        @Override
        public void mouseMoved(MouseEvent e) {
            int mouseY = e.getY();
            playerY = mouseY - PADDLE_HEIGHT / 2;

            if (playerY < 0) playerY = 0;
            if (playerY + PADDLE_HEIGHT > HEIGHT) playerY = HEIGHT - PADDLE_HEIGHT;

            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // Nu folosim
        }

        // Control paletă cu taste sus/jos (optional)
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_UP) {
                playerY -= 10;
                if (playerY < 0) playerY = 0;
            }
            if (key == KeyEvent.VK_DOWN) {
                playerY += 10;
                if (playerY + PADDLE_HEIGHT > HEIGHT) playerY = HEIGHT - PADDLE_HEIGHT;
            }
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) { }
        @Override
        public void keyTyped(KeyEvent e) { }
    }

    public static void main(String[] args) {
        // Pornim GUI pe firul EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> new Ex4_PingPong());
    }
}
