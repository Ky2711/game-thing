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
    private JLabel npcChar;

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
        Ellipse2D.Double oval = new Ellipse2D.Double(x, y, 300, 200);

        g2d.setColor(Color.GRAY);
        g2d.fill(oval);

    }

    public void drawNPCPlayer(GridBagConstraints position) {

        ImageIcon icon = new ImageIcon("images/Wizard.png");
        Image wizard = icon.getImage();
        Image scaledImage = wizard.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon smallWizard = new ImageIcon(scaledImage);
        this.npcChar = new JLabel(smallWizard);

        add(npcChar, position);

    }

    public void drawPlayer(GridBagConstraints position) {

        ImageIcon icon = new ImageIcon("images/idle_2.png");
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

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");

        getActionMap().put("moveRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                wizardChar.setLocation(wizardChar.getX() + 7, wizardChar.getY());
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");

        getActionMap().put("moveLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                wizardChar.setLocation(wizardChar.getX() - 7, wizardChar.getY());
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "moveUp");

        getActionMap().put("moveUp", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                wizardChar.setLocation(wizardChar.getX(), wizardChar.getY() - 7);
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "moveDown");

        getActionMap().put("moveDown", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                wizardChar.setLocation(wizardChar.getX(), wizardChar.getY() + 7);
            }
        });

    }
}
