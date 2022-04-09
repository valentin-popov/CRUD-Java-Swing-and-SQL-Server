import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class signUp implements ActionListener {
    public JTextField nume = new JTextField();
    public JTextField prenume = new JTextField();
    public JTextField email = new JTextField();
    public JTextField username = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JFrame frame = new JFrame("");
    JPanel panel = new JPanel();
    JLabel label1 = new JLabel("Sign Up", SwingConstants.CENTER);
    JLabel label2 = new JLabel("Nume", SwingConstants.CENTER);
    JLabel label3 = new JLabel("Prenume", SwingConstants.CENTER);
    JLabel label4 = new JLabel("E-mail", SwingConstants.CENTER);
    JLabel label5 = new JLabel("Username", SwingConstants.CENTER);
    JLabel label6 = new JLabel("Password", SwingConstants.CENTER);
    JButton button = new JButton("Sign Up");

    signUp(){
        button.addActionListener(this);
        button.setFocusable(false);
        button.setBackground(Color.green);

        frame.setSize(300, 530);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane();
        frame.setResizable(false);

        label1.setBounds(50, 20, 200, 30);
        label2.setBounds(100, 70, 100, 30);
        nume.setBounds(75, 100, 150, 30);
        label3.setBounds(100, 140, 100, 30);
        prenume.setBounds(75, 170, 150, 30);
        label4.setBounds(100, 210, 100, 30);
        email.setBounds(75, 240, 150, 30);
        label5.setBounds(100, 280, 100, 30);
        username.setBounds(75, 310, 150, 30);
        label6.setBounds(100, 350, 100, 30);
        passwordField.setBounds(75, 380, 150, 30);
        button.setBounds(75, 430, 150, 30);

        label1.setFont(new Font("Serif", Font.BOLD, 20));
        panel.setLayout(null);

        panel.add(label1);
        panel.add(label2);
        panel.add(nume);
        panel.add(label3);
        panel.add(prenume);
        panel.add(label4);
        panel.add(email);
        panel.add(label5);
        panel.add(username);
        panel.add(label6);
        panel.add(passwordField);
        panel.add(button);
        frame.add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button){
            try {
                String numeUtilizator = nume.getText();
                String prenumeUtilizator = prenume.getText();
                String emailUtilizator = email.getText();
                String userName = username.getText();
                String password = String.valueOf(passwordField.getPassword());
                int passwordHash = password.hashCode();

                PreparedStatement pst = Main.con.prepareStatement("insert into Utilizatori(Nume, Prenume, Username, Parola, Email) values(?, ?, ?, ?, ?);");
                pst.setString(1, numeUtilizator);
                pst.setString(2, prenumeUtilizator);
                pst.setString(3, userName);
                pst.setInt(4, passwordHash);
                pst.setString(5, emailUtilizator);
                pst.executeUpdate();
                UIManager.put("Button.background", Color.lightGray);
                JOptionPane.showMessageDialog(null, "Cont creat cu succes!");
                frame.dispose();
            }
            catch (SQLException e) {
                e.printStackTrace();
                UIManager.put("Button.background", Color.lightGray);
                JOptionPane.showMessageDialog(null, "Cont existent!");
            }
        }
    }
}




