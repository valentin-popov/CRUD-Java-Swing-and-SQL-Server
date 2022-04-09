import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Login implements ActionListener{
    public static boolean administrator = false;
    JTextField username = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JFrame frame = new JFrame("");
    JPanel panel = new JPanel();
    JLabel label = new JLabel("User Log in", SwingConstants.CENTER);
    JLabel label2 = new JLabel("Username", SwingConstants.CENTER);
    JLabel label3 = new JLabel("Password", SwingConstants.CENTER);
    JButton button = new JButton("LOGIN");
    JButton button2 = new JButton("Sign Up");

    Login() {
        button.addActionListener(this);
        button.setFocusable(false);
        button.setBackground(Color.green);

        button2.addActionListener(this);
        button2.setFocusable(false);
        button2.setBackground(Color.cyan);

        frame.setSize(300, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane();
        frame.setResizable(false);

        label.setBounds(50, 10, 200, 30);
        label2.setBounds(75, 50, 150, 30);
        username.setBounds(75, 80, 150, 30);
        label3.setBounds(75, 120, 150, 30);
        passwordField.setBounds(75, 150, 150, 30);
        button.setBounds(75, 200, 150, 30);
        button2.setBounds(75, 240, 150, 30);

        label.setFont(new Font("Serif", Font.BOLD, 20));
        panel.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.add(username);
        panel.add(passwordField);
        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        panel.add(button);
        panel.add(button2);
        frame.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button)
        {
            try {
                String usernameIncercat = username.getText();
                int parolaIncercata = String.valueOf(passwordField.getPassword()).hashCode();
                PreparedStatement pst = Main.con.prepareStatement("select Username, Parola, Nivel from Utilizatori where Username = ? and Parola = ?;");
                pst.setString(1, usernameIncercat);
                pst.setInt(2, parolaIncercata);

                ResultSet rs = pst.executeQuery();
                rs.next();
                String username = rs.getString("Username");
                int password = rs.getInt("Parola");
                String nivelAcces = rs.getString("Nivel");
                System.out.println(username + "\n" + password + "\n" +nivelAcces);

                if (nivelAcces.equals("a")) {
                    administrator = true;
                }
                if (nivelAcces.equals("c")) {
                    administrator = false;
                }
                Home home = new Home();
                frame.dispose();

            } catch (Exception e) {
                e.printStackTrace();
                UIManager.put("Button.background", Color.lightGray);
                JOptionPane.showMessageDialog(null, "Combinatie invalida!");
            }

        }
        if (actionEvent.getSource() == button2) {
            signUp signup = new signUp();
        }
    }
}
