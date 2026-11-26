import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class LevelPanel extends JPanel {

    private JLabel wizardChar;
    private JLabel npcChar;
    private Ellipse2D.Double oval;
    private boolean moveRight;
    private boolean moveLeft;
    private boolean moveUp;
    private boolean moveDown;
    private Timer movementTimer;

    public LevelPanel(GridBagLayout layout) {
        super(layout);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = (this.getWidth() - 300) / 2;
        int y = (this.getHeight() - 200) / 2;

        drawTowerFloor(g, x, y);
    }

    public void drawTowerFloor(Graphics g, int x, int y) {

        Graphics2D g2d = (Graphics2D) g;
        this.oval = new Ellipse2D.Double(x, y, 300, 200);

        g2d.setColor(Color.GRAY);
        g2d.fill(oval);

    }

    public void drawNPCPlayer(GridBagConstraints position) {

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/Wizard.png"));
        // Getting the image this way prevents the debugger from not being able to find it...
        Image wizard = icon.getImage();
        Image scaledImage = wizard.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon smallWizard = new ImageIcon(scaledImage);
        this.npcChar = new JLabel(smallWizard);

        add(npcChar, position);

    }

    public void drawPlayer(GridBagConstraints position) {

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/Idle_2.png"));
        Image wizard = icon.getImage();
        Image scaledImage = wizard.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon smallWizard = new ImageIcon(scaledImage);
        this.wizardChar = new JLabel(smallWizard);

        add(wizardChar, position);

        setUpControls();
    }

    public void drawProjectile(Graphics g) {
        // TODO
    }

    public void setUpControls() {

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed RIGHT"), "rightPressed");

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), "rightReleased");

        getActionMap().put("rightPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveRight = true;
            }
        });

        getActionMap().put("rightReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveRight = false;
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed LEFT"), "leftPressed");

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), "leftReleased");

        getActionMap().put("leftPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveLeft = true;
            }
        });

        getActionMap().put("leftReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveLeft = false;
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed UP"), "upPressed");

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "upReleased");

        getActionMap().put("upPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveUp = true;
            }
        });

        getActionMap().put("upReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveUp = false;
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed DOWN"), "downPressed");

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "downReleased");

        getActionMap().put("downPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveDown = true;
            }
        });

        getActionMap().put("downReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveDown = false;
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "shoot");

        getActionMap().put("shoot", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                shootProjectile();
            }
        });

        movementTimer = new Timer(50, e -> {
            int newX = wizardChar.getX();
            int newY = wizardChar.getY();

            if (moveRight) {
                newX += 7;
            }

            if (moveLeft) {
                newX -= 7;
            }

            if (moveUp) {
                newY -= 7;
            }

            if (moveDown) {
                newY += 7;
            }

            if (canMoveTo(newX, newY)) {
                wizardChar.setLocation(newX, newY);
            }
        });

        movementTimer.start();
    }

    private boolean canMoveTo(int newX, int newY) {
        if (oval == null) {
            return false;
        }

        int hitboxWidth = 20;
        int hitboxHeight = 30;

        int hitboxX = newX + 15;
        int hitboxY = newY + 10;
        return oval.contains(hitboxX, hitboxY, hitboxWidth, hitboxHeight);
    }

    private void shootProjectile() {
        JLabel projectile = new JLabel();

        projectile.setOpaque(true);
        projectile.setBackground(Color.RED);
        projectile.setSize(5, 5);

        int startx = wizardChar.getX() + wizardChar.getWidth();
        int starty = wizardChar.getY() + wizardChar.getHeight() / 2;

        projectile.setLocation(startx, starty);

        add(projectile);
        repaint();

        Timer projectileTimer = new Timer(20, e -> {
            projectile.setLocation(projectile.getX() + 5, projectile.getY());
        });

        projectileTimer.start();
    }

}