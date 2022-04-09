import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class editeazaProducatori implements ActionListener {
    JFrame frame = new JFrame("Editeaza Producatori");
    JPanel panel = new JPanel();
    JButton button1 = new JButton("Adauga");
    JButton button2 = new JButton("Update");
    JButton button3 = new JButton("Sterge");

    JTextField textField1 = new JTextField();
    public JTextField textField2 = new JTextField();
    public JTextField textField3 = new JTextField();
    public JTextField textField4 = new JTextField();

    JLabel label1 = new JLabel("ID producator", SwingConstants.CENTER);
    JLabel label2 = new JLabel("Denumire producator", SwingConstants.CENTER);
    JLabel label3 = new JLabel("Tara de origine", SwingConstants.CENTER);
    JLabel label4 = new JLabel("Adresa producator", SwingConstants.CENTER);
    editeazaProducatori() {
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.getContentPane();
        frame.setResizable(false);
        plaseazaComponente();
        panel.setLayout(null);
        adaugaComponente();
    }

    public void plaseazaComponente() {
        button1.setBounds(150, 300, 200, 40);
        button2.setBounds(150, 350, 200, 40);
        button3.setBounds(150, 400, 200, 40);

        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);

        label1.setBounds(150, 50, 200, 20);
        label2.setBounds(150, 110, 200, 20);
        label3.setBounds(150, 170, 200, 20);
        label4.setBounds(150, 230, 200, 20);

        textField1.setBounds(150, 70, 200, 20);
        textField2.setBounds(150, 130, 200, 20);
        textField3.setBounds(150, 190, 200, 20);
        textField4.setBounds(150, 250, 200, 20);
    }
    public void adaugaComponente(){
        if (Login.administrator) {
            panel.add(label1);
            panel.add(textField1);
            panel.add(button2);
            panel.add(button3);
        }

        panel.add(button1);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        button1.setBackground(Color.green);
        button2.setBackground(Color.yellow);
        button3.setBackground(Color.red);

        //panel.add(textField1);
        panel.add(textField2);
        panel.add(textField3);
        panel.add(textField4);
        //panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        frame.add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button1) {
            try {
                String denumire = textField2.getText();
                String taraOrigine = textField3.getText();
                String adresa = textField4.getText();

                PreparedStatement pst = Main.con.prepareStatement("insert into Producatori(Denumire, TaraOrigine, Adresa) values(?, ?, ?);");
                pst.setString(1, denumire);
                pst.setString(2, taraOrigine);
                pst.setString(3, adresa);
                pst.executeUpdate();

                UIManager.put("Button.background", Color.lightGray);
                JOptionPane.showMessageDialog(null, "Date adaugate cu succes!");
                //JOptionPane.showMessageDialog(null, "Date adaugate cu succes!");
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
            }

            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if (actionEvent.getSource() == button2){
            try {
                int id = Integer.parseInt(textField1.getText());
                String denumire = textField2.getText();
                String taraOrigine = textField3.getText();
                String adresa = textField4.getText();
                PreparedStatement pst = Main.con.prepareStatement("UPDATE Producatori SET Denumire = ?, TaraOrigine = ?, Adresa = ? WHERE ProducatorID = ?;");
                pst.setString(1, denumire);
                pst.setString(2, taraOrigine);
                pst.setString(3, adresa);
                pst.setInt(4, id);
                pst.executeUpdate();

                UIManager.put("Button.background", Color.lightGray);
                JOptionPane.showMessageDialog(null, "Date actualizate cu succes!");
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
            }

            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if (actionEvent.getSource() == button3){
            try {
                int id = Integer.parseInt(textField1.getText());
                PreparedStatement pst = Main.con.prepareStatement("delete from Producatori where ProducatorID = ?;");
                pst.setInt(1, id);
                pst.executeUpdate();

                UIManager.put("Button.background", Color.lightGray);
                JOptionPane.showMessageDialog(null, "Date sterse cu succes!");
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
