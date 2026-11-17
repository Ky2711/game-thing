import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {

    public void launch() {

        JFrame frame = new JFrame("Wizard Tower Game");

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // so you don't have to manually exit every time

        // referenced https://www.geeksforgeeks.org/java/java-awt-cardlayout-class/

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout); // in using cardLayout, cardPanel holds all the different panels / "screens"
                                                    // that represent different rooms.

        JPanel startScreen = new JPanel(new GridBagLayout()); // needs a layout so it
        LevelPanel floorScreen1 = new LevelPanel(new GridBagLayout()); // won't just ignore setBounds()
        // GridBagLayout (+constraints) is one that is pretty simple to use because you just have to change the insets
        // or x and y position for restraints for each object you draw and attach those constraints as an argument
        // also I had to make a custom class that extends JPanel for levels to draw to the panel instead of the screen
        // so it would display properly lol

        cardPanel.add(startScreen, "StartScreen");
        cardPanel.add(floorScreen1, "Floor1");

        GridBagConstraints position = new GridBagConstraints();

        // Start Screen Setup

        JButton startButton = new JButton("Start");
        position.gridx = 0;
        position.gridy = 0;
        startScreen.add(startButton, position);

        ActionListener click = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Floor1");
                cardPanel.revalidate(); // makes sure elements are laid out correctly with GridBagLayout
                cardPanel.repaint(); // redraws elements
            }

        };

        startButton.addActionListener(click);

        // Floor 1 Screen Setup
        // TODO add NPC player elements + temp projectile element to floorScreen1
        // referenced https://mkyong.com/swing/java-swing-draw-shapes-dynamically-example/

        JButton exitButton = new JButton("Exit to Menu");
        position.insets = new Insets(0, 0, 400, 0);

        floorScreen1.add(exitButton, position);

        position.insets = new Insets(0, 0, 0, 0);
        floorScreen1.drawPlayer(position);

        ActionListener click2 = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "StartScreen");
                cardPanel.revalidate();
                cardPanel.repaint();
            }
            
        };

        exitButton.addActionListener(click2);

        frame.add(cardPanel);

        frame.setVisible(true);

        cardLayout.show(cardPanel, "StartScreen");
        
    }
    
}