import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Home implements ActionListener {
    JFrame frame = new JFrame("Start");
    JButton button1 = new JButton("Clienti");
    JButton button2 = new JButton("Produse");
    JButton button3 = new JButton("Producatori");
    JButton button4 = new JButton("Cumparaturi");
    JButton button5 = new JButton("Vanzari");

    Home() throws IOException{
        BufferedImage image;
        JLabel label;

        image = ImageIO.read(new File("/home/vpk9x3/IdeaProjects/proj3/src/java.png"));
        label = new JLabel(new ImageIcon(image));
        label.setBounds(150, 20, 200, 200);

        button1.setBounds(100, 250, 300, 40);
        button1.addActionListener(this);

        button2.setBounds(100, 300, 300, 40);
        button2.addActionListener(this);

        button3.setBounds(100, 350, 300, 40);
        button3.addActionListener(this);

        button4.setBounds(100, 400, 150, 40);
        button4.addActionListener(this);

        button5.setBounds(250, 400, 150, 40);
        button5.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 550);
        frame.setLayout(null);
        frame.setVisible(true);

        button1.setBackground(Color.LIGHT_GRAY);
        button2.setBackground(Color.LIGHT_GRAY);
        button3.setBackground(Color.LIGHT_GRAY);
        button4.setBackground(Color.LIGHT_GRAY);
        button5.setBackground(Color.LIGHT_GRAY);

        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);
        button4.setFocusable(false);
        button5.setFocusable(false);

        frame.add(label);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == button1) {
            Clienti clienti = new Clienti();
        }

        if (actionEvent.getSource() == button2) {
            Produse produse = new Produse();
        }
        if (actionEvent.getSource() == button3) {
            //frame.dispose();
            Producatori producatori = new Producatori();
        }
        if (actionEvent.getSource() == button4) {
            Cumparaturi cumparaturi = new Cumparaturi();
        }
        if (actionEvent.getSource() == button5) {
            Vanzari vanzari = new Vanzari();
        }
    }
}
