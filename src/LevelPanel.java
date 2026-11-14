import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class LevelPanel extends JPanel {

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
        Ellipse2D.Double oval = new Ellipse2D.Double(100, 200, 300, 200);

        // TODO might have to make oval transparent to show other elements idk
        g2d.setColor(Color.GRAY);
        g2d.fill(oval);
        
    }

    public void drawNPCPlayer(Graphics g) {
        // TODO
    }

    public void drawPlayer(Graphics g) {
        // TODO
    }

    public void drawProjectile(Graphics g) {
        // TODO
    }
}
