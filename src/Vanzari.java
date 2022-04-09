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

public class Vanzari implements ActionListener {
    JFrame frame = new JFrame("Vanzari");
    JPanel panel = new JPanel();
    JButton button = new JButton("Editeaza vanzari");
    JButton refresh = new JButton("Refresh");
    JLabel label = new JLabel("<html><center>Tabel Vanzari</html>");

    Vanzari(){
        button.addActionListener(this);
        refresh.addActionListener(this);
        label.setFont(new Font("Serif", Font.BOLD, 28));
        try {
            String query1 = "select Vanzari.VanzareID, Vanzari.idProducator, Producatori.Denumire as Producator, Vanzari.idProdus, ProdusAlimentar.Denumire as Produs, ProdusAlimentar.DataExpirare from Vanzari inner join Producatori on (Vanzari.idProducator = Producatori.ProducatorID) inner join ProdusAlimentar on(Vanzari.idProdus = ProdusAlimentar.ProdusID);";
            String query2 = "select count(*) as numarLinii from Vanzari;";
            Statement stmt1 = Main.con.createStatement();
            Statement stmt2 = Main.con.createStatement();

            ResultSet rs1= stmt1.executeQuery(query1);
            ResultSet rs2= stmt2.executeQuery(query2);

            rs2.next();
            int numarLinii = rs2.getInt("numarLinii");
            String columns[] = { "<html><center>ID<br> </html>", "<html><center>ID <br>producator<html>", "<html><center>Denumire <br>producator<html>", "<html><center>ID<br>produs</html>", "<html><center>Denumire<br>produs</html>", "<html><center>Data <br>expirare<html>" };
            String data[][] = new String[numarLinii][6];

            int i=0;
            while (rs1.next()) {
                int idCumparatura = rs1.getInt("VanzareID");
                int idProducator = rs1.getInt("idProducator");
                int idProdus = rs1.getInt("idProdus");

                String denumireProducator = rs1.getString("Producator");
                String denumireProdus = rs1.getString("Produs");
                String dataExpirare = rs1.getString("DataExpirare");
                data[i][0] = idCumparatura + "";
                data[i][1] = idProducator + "";
                data[i][2] = denumireProducator;
                data[i][3] = idProdus + "";
                data[i][4] = denumireProdus;
                data[i][5] = dataExpirare;
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
            TableColumn idProdus = jt.getColumnModel().getColumn(3);
            idCumparatura.setPreferredWidth(90);
            idCumparatura.setMaxWidth(100);
            idClient.setPreferredWidth(90);
            idClient.setMaxWidth(100);
            idProdus.setPreferredWidth(90);
            idProdus.setMaxWidth(100);

            jt.getTableHeader().setFont(new Font("", Font.BOLD, 13));
            jt.getTableHeader().setPreferredSize(new Dimension(700, 50));
            //jt.getTableHeader().setOpaque(false);
            //jt.getTableHeader().setBackground(Color.green);

            JScrollPane scrollPane = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


            label.setBounds(295, 25, 220, 50);
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
            editeazaVanzari newWindow = new editeazaVanzari();
        }

        if (actionEvent.getSource() == refresh) {
            frame.dispose();
            Vanzari vanzari = new Vanzari();
        }
    }
}



