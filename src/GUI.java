import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class GUI extends JPanel {

    public GUI() {

        setLayout(null);

        ImageIcon icon = new ImageIcon("src/images/idle_2.png");
        Image wizard = icon.getImage();
        Image scaledImage = wizard.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon smallWizard = new ImageIcon(scaledImage);
        JLabel wizardChar = new JLabel(smallWizard);
        wizardChar.setBounds(100, 100, 50, 50);

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

        add(wizardChar);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Wizard Tower");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI panel = new GUI();
        frame.getContentPane().add(panel);


        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}

