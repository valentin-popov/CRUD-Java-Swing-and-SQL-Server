import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cumparaturi implements ActionListener {
    JFrame frame = new JFrame("Cumparaturi");
    JPanel panel = new JPanel();
    JButton button = new JButton("Editeaza cumparaturi");
    JButton refresh = new JButton("Refresh");
    JLabel label = new JLabel("Tabel Cumparaturi");

    Cumparaturi(){
        button.addActionListener(this);
        refresh.addActionListener(this);
        label.setFont(new Font("Serif", Font.BOLD, 28));
        try {
            String query1 = "select Cumparaturi.CumparaturaID, Cumparaturi.idClient, Clienti.Nume, Clienti.Prenume, Cumparaturi.idProdus, ProdusAlimentar.Denumire, ProdusAlimentar.DataExpirare from Cumparaturi inner join Clienti on (Cumparaturi.idClient = Clienti.ClientID) inner join ProdusAlimentar on(Cumparaturi.idProdus = ProdusAlimentar.ProdusID);";
            String query2 = "select count(*) as numarLinii from Cumparaturi;";

            Statement stmt1 = Main.con.createStatement();
            Statement stmt2 = Main.con.createStatement();

            ResultSet rs1= stmt1.executeQuery(query1);
            ResultSet rs2= stmt2.executeQuery(query2);

            rs2.next();
            int numarLinii = rs2.getInt("numarLinii");
            String columns[] = { "<html><center>ID<br> </html>", "<html><center>ID <br>client<html>", "Prenume", "Nume", "<html><center>ID<br>produs</html>", "Denumire", "<html><center>Data <br>expirare<html>" };
            String data[][] = new String[numarLinii][7];

            int i = 0;
            while (rs1.next()) {

                int idCumparatura = rs1.getInt("CumparaturaID");
                int idClient = rs1.getInt("idClient");
                int idProdus = rs1.getInt("idProdus");
                String nume = rs1.getString("Nume");
                String prenume = rs1.getString("Prenume");
                String denumire = rs1.getString("Denumire");
                String dataExpirare = rs1.getString("DataExpirare");
                data[i][0] = idCumparatura + "";
                data[i][1] = idClient + "";
                data[i][2] = prenume;
                data[i][3] = nume;
                data[i][4] = idProdus + "";
                data[i][5] = denumire;
                data[i][6] = dataExpirare;
                i++;
            }

            DefaultTableModel model = new DefaultTableModel(data, columns);
            JTable jt = new JTable(model);
            jt.setRowHeight(25);
            jt.setShowGrid(true);
            jt.setShowVerticalLines(true);
            jt.setFont(new Font("", Font.BOLD, 13));

            TableColumn idCumparatura = jt.getColumnModel().getColumn(0);
            TableColumn idClient = jt.getColumnModel().getColumn(1);
            TableColumn idProdus = jt.getColumnModel().getColumn(4);
            idCumparatura.setPreferredWidth(60);
            idCumparatura.setMaxWidth(70);
            idClient.setPreferredWidth(60);
            idClient.setMaxWidth(70);
            idProdus.setPreferredWidth(60);
            idProdus.setMaxWidth(70);

            jt.getTableHeader().setFont(new Font("", Font.BOLD, 13));
            jt.getTableHeader().setPreferredSize(new Dimension(700, 50));
            //jt.getTableHeader().setOpaque(false);
            //jt.getTableHeader().setBackground(Color.green);

            JScrollPane scrollPane = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            label.setBounds(252, 25, 400, 50);
            button.setBounds(300, 520, 200, 40);
            button.setFocusable(false);
            button.setBackground(Color.lightGray);

            refresh.setBounds(350, 80, 100, 20);
            refresh.setFocusable(false);
            refresh.setBackground(Color.lightGray);

            scrollPane.setBounds(50, 110, 700, 390);
            panel.add(label);
            panel.add(button);
            panel.add(refresh);
            panel.add(scrollPane);
            panel.setLayout(null);
            frame.add(panel);
            frame.setSize(800, 620);
            frame.setVisible(true);
            frame.setResizable(false);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button)
        {
            editeazaCumparaturi newWindow = new editeazaCumparaturi();
        }
        if (actionEvent.getSource() == refresh) {
            frame.dispose();
            Cumparaturi cumparaturi = new Cumparaturi();
        }
    }
}