import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Produse implements ActionListener {
    JFrame frame = new JFrame("Produse");
    JPanel panel = new JPanel();
    JButton button = new JButton("Editeaza tabel produse");
    JButton refresh = new JButton("Refresh");
    JLabel label = new JLabel("Produse alimentare");
    Produse(){
        button.addActionListener(this);
        refresh.addActionListener(this);
        label.setFont(new Font("Serif", Font.BOLD, 28));
        try {
            String query = "select * from ProdusAlimentar;";
            Statement stmt = Main.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            String query1 = "select count(*) as numarLinii from ProdusAlimentar;";
            Statement stmt1 = Main.con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(query1);

            rs1.next();

            int numarLinii = rs1.getInt("numarLinii");
            String columns[] = { "ID", "Denumire", "Data fabricatie", "Data expirare" };
            String data[][] = new String[numarLinii][4];

            int i=0;
            while (rs.next()) {
                int id = rs.getInt("ProdusID");
                String nume = rs.getString("Denumire");
                String prenume = rs.getString("DataProducere");
                String adresa = rs.getString("DataExpirare");
                data[i][0] = id + "";
                data[i][1] = nume;
                data[i][2] = prenume;
                data[i][3] = adresa;
                i++;
            }

            DefaultTableModel model = new DefaultTableModel(data, columns);
            JTable jt = new JTable(model);
            jt.setRowHeight(25);
            jt.setShowGrid(true);
            jt.setShowVerticalLines(true);
            jt.setFont(new Font("", Font.BOLD, 13));
            TableColumn column = jt.getColumnModel().getColumn(0);
            column.setPreferredWidth(50);
            column.setMaxWidth(70);

            jt.getTableHeader().setFont(new Font("", Font.BOLD, 13));
            JScrollPane scrollPane = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            label.setBounds(90, 25, 400, 50);
            button.setBounds(150, 520, 200, 40);
            refresh.setBounds(200, 80, 100, 20);
            button.setFocusable(false);
            button.setBackground(Color.lightGray);
            refresh.setFocusable(false);
            refresh.setBackground(Color.lightGray);
            scrollPane.setBounds(10, 110, 480, 380);
            panel.add(label);
            panel.add(button);
            panel.add(refresh);
            panel.add(scrollPane);
            panel.setLayout(null);

            frame.add(panel);
            frame.setSize(500, 620);
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
            editeazaProduse newWindow = new editeazaProduse();
        }

        if (actionEvent.getSource() == refresh){
            frame.dispose();
            Produse produse = new Produse();
        }
    }
}