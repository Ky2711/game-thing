import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class LevelPanel extends JPanel {

    private JLabel wizardChar;
    private Ellipse2D.Double oval = new Ellipse2D.Double(100, 200, 300, 200);

    public LevelPanel(GridBagLayout layout) { // fixes how it wasn't letting me use layout for this because its a custom panel
        super(layout);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTowerFloor(g);
    }

    public void drawTowerFloor(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.GRAY);
        g2d.fill(oval);
        
    }

    public void drawNPCPlayer(Graphics g) {
        // TODO
    }

    public void drawPlayer(GridBagConstraints position) {

        ImageIcon icon = new ImageIcon("src/images/Idle_2.png");
        Image wizard = icon.getImage();
        Image scaledImage = wizard.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon smallWizard = new ImageIcon(scaledImage);
        this.wizardChar = new JLabel(smallWizard);
        wizardChar.setBounds(100, 100, 50, 50);

        add(wizardChar, position);

        setUpControls();

    }

    public void drawProjectile(Graphics g) {
        // TODO
    }

    public void setUpControls() {

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");

        getActionMap().put("moveRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
               int newX = wizardChar.getX() + 7;
               int newY = wizardChar.getY();

               if (canMoveTo(newX, newY)) {
                   wizardChar.setLocation(newX, newY);
               }
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");

        getActionMap().put("moveLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int newX = wizardChar.getX() - 7;
                int newY = wizardChar.getY();

                if (canMoveTo(newX, newY)) {
                    wizardChar.setLocation(newX, newY);
                }
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "moveUp");

        getActionMap().put("moveUp", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int newX = wizardChar.getX();
                int newY = wizardChar.getY() - 7;

                if (canMoveTo(newX, newY)) {
                    wizardChar.setLocation(newX, newY);
                }
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "moveDown");

        getActionMap().put("moveDown", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int newX = wizardChar.getX();
                int newY = wizardChar.getY() + 7;

                if (canMoveTo(newX, newY)) {
                    wizardChar.setLocation(newX, newY);
                }
            }
        });
    }

    private boolean canMoveTo(int newX, int newY) {
        int hitboxWidth = 20;
        int hitboxHeight = 30;

        int hitboxX = newX + 15;
        int hitboxY = newY + 10;
        return oval.contains(hitboxX, hitboxY, hitboxWidth, hitboxHeight);
    }
}
